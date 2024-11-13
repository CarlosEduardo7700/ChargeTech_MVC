package br.com.chargetech.chargetechmvc.controllers;

import br.com.chargetech.chargetechmvc.dtos.ambiente.CadastroDeAmbienteDto;
import br.com.chargetech.chargetechmvc.dtos.ambiente.ListagemDosAmbientesDto;
import br.com.chargetech.chargetechmvc.dtos.usuario.CadastroDeUsuarioDto;
import br.com.chargetech.chargetechmvc.models.Ambiente;
import br.com.chargetech.chargetechmvc.repositories.AmbienteRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String cadastrarAmbiente(@Valid @ModelAttribute("ambiente") CadastroDeAmbienteDto dto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "ambiente/form-cadastrar";
        }

        Ambiente ambiente = new Ambiente();
        ambiente.setNome(dto.nome().toUpperCase());
        ambiente.setDescricao(dto.descricao());
        ambiente.setLabel(dto.nome());

        ambienteRepository.save(ambiente);
        return "redirect:/ambiente/cadastrar";
    }

    @PostMapping("deletar")
    @Transactional
    public String deletarAmbiente(Long id, RedirectAttributes redirectAttributes) {
        ambienteRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("mensagem", "Ambiente Deletado!");
        return "redirect:/ambiente/cadastrar";
    }
}
