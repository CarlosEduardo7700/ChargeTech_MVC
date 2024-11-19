package br.com.chargetech.chargetechmvc.controllers;

import br.com.chargetech.chargetechmvc.dtos.dispositivo.ListagemDosDispositivosDto;
import br.com.chargetech.chargetechmvc.models.ConsumoEnergetico;
import br.com.chargetech.chargetechmvc.repositories.ConsumoEnergeticoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("consumo-energetico")
public class ConsumoEnergeticoController {

    @Autowired
    private ConsumoEnergeticoRepository consumoEnergeticoRepository;

    @GetMapping
    public String exibirConsumoEnergetico(Model model) {
        List<ConsumoEnergetico> consumosEnergeticos = consumoEnergeticoRepository.findAll();
        model.addAttribute("consumosEnergeticos", consumosEnergeticos);
        return "consumo-nergetico/list-consumos";
    }
}
