package br.com.chargetech.chargetechmvc.controllers;

import br.com.chargetech.chargetechmvc.dtos.ambiente.CadastroDeAmbienteDto;
import br.com.chargetech.chargetechmvc.dtos.ambiente.ListagemDosAmbientesDto;
import br.com.chargetech.chargetechmvc.dtos.dispositivo.DispositivoDto;
import br.com.chargetech.chargetechmvc.dtos.dispositivo.EdicaoDoDispositivoDto;
import br.com.chargetech.chargetechmvc.dtos.usuario.CadastroDeUsuarioDto;
import br.com.chargetech.chargetechmvc.models.Ambiente;
import br.com.chargetech.chargetechmvc.models.Dispositivo;
import br.com.chargetech.chargetechmvc.repositories.AmbienteRepository;
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
import java.util.Random;

@Controller
@RequestMapping("ambiente")
public class AmbienteController {

    @Autowired
    private AmbienteRepository ambienteRepository;

    @GetMapping("cadastrar")
    public String exibirCadastroDeAmbiente(CadastroDeAmbienteDto dtoDeCadastro, ListagemDosAmbientesDto dtoDeListagem, Model model) {
        model.addAttribute("ambiente", dtoDeCadastro);
        List<ListagemDosAmbientesDto> ambientes = ambienteRepository.findAll()
                .stream().map(ListagemDosAmbientesDto::new).toList();
        model.addAttribute("ambientes", ambientes);
        return "ambiente/form-cadastrar";
    }

    @PostMapping("cadastrar")
    public String cadastrarAmbiente(@Valid @ModelAttribute("ambiente") CadastroDeAmbienteDto dto, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "ambiente/form-cadastrar";
        }

        Ambiente ambiente = new Ambiente();
        ambiente.setNome(dto.nome().toUpperCase());
        ambiente.setDescricao(dto.descricao());
        ambiente.setLabel(dto.nome());

        ambienteRepository.save(ambiente);
        redirectAttributes.addFlashAttribute("mensagem", "Ambiente Cadastrado!");
        return "redirect:/ambiente/cadastrar";
    }

    @PostMapping("deletar")
    @Transactional
    public String deletarAmbiente(Long id, RedirectAttributes redirectAttributes) {
        ambienteRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("mensagem", "Ambiente Deletado!");
        return "redirect:/ambiente/cadastrar";
    }

    @GetMapping("editar/{id}")
    public String exibirFormularioEditar(@PathVariable("id") Long id, Model model) {
        Ambiente ambiente = ambienteRepository.getReferenceById(id);
        model.addAttribute("ambiente", ambiente);
        return "ambiente/form-editar";
    }

    @PostMapping("editar")
    public String editarAmbiente(@Valid @ModelAttribute("ambiente") Ambiente ambiente, BindingResult result, RedirectAttributes redirectAttributes, Model model, Principal principal){
        if (result.hasErrors()) {
            return "ambiente/form-editar";
        }

        ambienteRepository.save(ambiente);
        redirectAttributes.addFlashAttribute("mensagem", "O ambiente foi atualizado!");
        return "redirect:/ambiente/cadastrar";
    }
}
