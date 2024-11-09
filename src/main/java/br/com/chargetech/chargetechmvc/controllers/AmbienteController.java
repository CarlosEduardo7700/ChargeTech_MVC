package br.com.chargetech.chargetechmvc.controllers;

import br.com.chargetech.chargetechmvc.dtos.ambiente.CadastroDeAmbienteDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("ambiente")
public class AmbienteController {

    @GetMapping("cadastrar")
    public String exibirCadastroDeAmbiente(CadastroDeAmbienteDto dto, Model model) {
        model.addAttribute("ambiente", dto);
        return "ambiente/form-cadastrar";
    }
}
