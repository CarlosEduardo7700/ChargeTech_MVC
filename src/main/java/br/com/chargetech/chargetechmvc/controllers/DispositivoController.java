package br.com.chargetech.chargetechmvc.controllers;

import br.com.chargetech.chargetechmvc.dtos.ambiente.CadastroDeAmbienteDto;
import br.com.chargetech.chargetechmvc.dtos.dispositivo.CadastroDeDispositivoDto;
import br.com.chargetech.chargetechmvc.dtos.dispositivo.DispositivoDto;
import br.com.chargetech.chargetechmvc.dtos.dispositivo.EdicaoDoDispositivoDto;
import br.com.chargetech.chargetechmvc.dtos.dispositivo.ListagemDosDispositivosDto;
import br.com.chargetech.chargetechmvc.models.Ambiente;
import br.com.chargetech.chargetechmvc.models.Dispositivo;
import br.com.chargetech.chargetechmvc.repositories.AmbienteRepository;
import br.com.chargetech.chargetechmvc.repositories.DispositivoRepository;
import br.com.chargetech.chargetechmvc.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("dispositivo")
public class DispositivoController {

    @Autowired
    private DispositivoRepository dispositivoRepository;

    @Autowired
    private AmbienteRepository ambienteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public String exibirDispositivos(Model model) {
        List<ListagemDosDispositivosDto> dispositivos = dispositivoRepository.findAll()
                .stream().map(ListagemDosDispositivosDto::new).toList();
        model.addAttribute("dispositivos", dispositivos);
        return "dispositivo/list-dispositivos";
    }

    @GetMapping("cadastrar")
    public String exibirFormularioCadastrar(CadastroDeDispositivoDto dto, Model model) {
        model.addAttribute("dispositivo", dto);
        model.addAttribute("ambientes", ambienteRepository.findAll());
        return "dispositivo/form-cadastrar";
    }

    @PostMapping("cadastrar")
    public String cadastrarDispositivo(@Valid @ModelAttribute("dispositivo") CadastroDeDispositivoDto dto, BindingResult result, Model model, Principal principal, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("ambientes", ambienteRepository.findAll());
            return "dispositivo/form-cadastrar";
        }

        Dispositivo dispositivo = new Dispositivo();
        dispositivo.setNome(dto.nome());
        dispositivo.setConsumoMedio(dto.consumoMedio());
        dispositivo.setAmbiente(ambienteRepository.findByNome(dto.ambiente()));
        dispositivo.setUsuario(usuarioRepository.findByEmail(principal.getName()));
        dispositivo.setStatus("LIGADO");

        dispositivoRepository.save(dispositivo);
        redirectAttributes.addFlashAttribute("mensagem", "Dispositivo cadastrado!");
        return "redirect:/dispositivo/cadastrar";
    }

    @PostMapping("deletar")
    @Transactional
    public String deletarDispositivo(Long id, RedirectAttributes redirectAttributes) {
        dispositivoRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("mensagem", "Dispositivo Deletado!");
        return "redirect:/dispositivo";
    }

    @GetMapping("editar/{id}")
    public String exibirFormularioEditar(@PathVariable("id") Long id, Model model) {
        DispositivoDto dto = new DispositivoDto(dispositivoRepository.getReferenceById(id));
        model.addAttribute("ambientes", ambienteRepository.findAll());
        model.addAttribute("dispositivo", dto);
        return "dispositivo/form-editar";
    }

    @PostMapping("editar")
    public String editarDispositivo(@Valid @ModelAttribute("dispositivo") EdicaoDoDispositivoDto dto, BindingResult result, RedirectAttributes redirectAttributes, Model model, Principal principal){
        if (result.hasErrors()) {
            model.addAttribute("ambientes", ambienteRepository.findAll());
            return "dispositivo/form-editar";
        }
        Dispositivo dispositivo = new Dispositivo(dto, ambienteRepository.findByNome(dto.ambiente()), usuarioRepository.findByEmail(principal.getName()));

        dispositivoRepository.save(dispositivo);
        redirectAttributes.addFlashAttribute("mensagem", "O dispositivo foi atualizado!");
        return "redirect:/dispositivo";
    }

}
