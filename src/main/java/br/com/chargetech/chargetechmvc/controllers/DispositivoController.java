package br.com.chargetech.chargetechmvc.controllers;

import br.com.chargetech.chargetechmvc.dtos.dispositivo.CadastroDeDispositivoDto;
import br.com.chargetech.chargetechmvc.repositories.AmbienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("dispositivo")
public class DispositivoController {

    @Autowired
    private AmbienteRepository ambienteRepository;

    @GetMapping("cadastrar")
    public String exibirFormularioCadastrar(CadastroDeDispositivoDto dto, Model model) {
        model.addAttribute("dispositivo", dto);
        model.addAttribute("ambientes", ambienteRepository.findAll());
        return "dispositivo/form-cadastrar";
    }

}
