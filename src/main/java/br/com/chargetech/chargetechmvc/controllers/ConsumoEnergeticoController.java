package br.com.chargetech.chargetechmvc.controllers;

import br.com.chargetech.chargetechmvc.dtos.dispositivo.CadastroDeDispositivoDto;
import br.com.chargetech.chargetechmvc.dtos.dispositivo.ListagemDosDispositivosDto;
import br.com.chargetech.chargetechmvc.models.ConsumoEnergetico;
import br.com.chargetech.chargetechmvc.models.Dispositivo;
import br.com.chargetech.chargetechmvc.repositories.ConsumoEnergeticoRepository;
import br.com.chargetech.chargetechmvc.repositories.DispositivoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Controller
@RequestMapping("consumo-energetico")
public class ConsumoEnergeticoController {

    @Autowired
    private ConsumoEnergeticoRepository consumoEnergeticoRepository;

    @GetMapping
    public String exibirConsumoEnergetico(Model model) {
        List<ConsumoEnergetico> consumosEnergeticos = consumoEnergeticoRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(ConsumoEnergetico::getDataDeRegistro).reversed()) // Ordena por nome
                .collect(Collectors.toList());
        model.addAttribute("consumosEnergeticos", consumosEnergeticos);
        return "consumo-nergetico/list-consumos";
    }
}
