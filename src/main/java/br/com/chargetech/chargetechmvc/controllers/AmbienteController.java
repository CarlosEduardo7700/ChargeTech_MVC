package br.com.chargetech.chargetechmvc.controllers;

import br.com.chargetech.chargetechmvc.dtos.ambiente.CadastroDeAmbienteDto;
import br.com.chargetech.chargetechmvc.dtos.usuario.CadastroDeUsuarioDto;
import br.com.chargetech.chargetechmvc.models.Ambiente;
import br.com.chargetech.chargetechmvc.repositories.AmbienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Random;

@Controller
@RequestMapping("ambiente")
public class AmbienteController {

    @Autowired
    private AmbienteRepository ambienteRepository;

    @GetMapping("cadastrar")
    public String exibirCadastroDeAmbiente(CadastroDeAmbienteDto dto, Model model) {
        model.addAttribute("ambiente", dto);
        return "ambiente/form-cadastrar";
    }

    @PostMapping("cadastrar")
    public String cadastrarAmbiente(@Valid @ModelAttribute("ambiente") CadastroDeAmbienteDto dto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "ambiente/form-cadastrar";
        }

        Ambiente ambiente = new Ambiente();
        ambiente.setNome(dto.nome());
        ambiente.setDescricao(dto.descricao());
        ambiente.setLabel(dto.nome().toUpperCase());

        ambienteRepository.save(ambiente);
        return "redirect:/ambiente/cadastrar";
    }
}
