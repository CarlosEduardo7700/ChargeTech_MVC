package br.com.chargetech.chargetechmvc.controllers;

import br.com.chargetech.chargetechmvc.dtos.dispositivo.CadastroDeDispositivoDto;
import br.com.chargetech.chargetechmvc.dtos.dispositivo.ListagemDosDispositivosDto;
import br.com.chargetech.chargetechmvc.models.ConsumoEnergetico;
import br.com.chargetech.chargetechmvc.models.Dispositivo;
import br.com.chargetech.chargetechmvc.repositories.ConsumoEnergeticoRepository;
import br.com.chargetech.chargetechmvc.repositories.DispositivoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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
    public String exibirConsumoEnergetico(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "20") int tamanho,
            Model model) {

        Pageable pageable = PageRequest.of(pagina, tamanho, Sort.by("dataDeRegistro").descending());
        Page<ConsumoEnergetico> consumosEnergeticosTeste = consumoEnergeticoRepository.findAll(pageable);


        model.addAttribute("consumosEnergeticos", consumosEnergeticosTeste.getContent());
        model.addAttribute("paginaAtual", pagina);
        model.addAttribute("totalPaginas", consumosEnergeticosTeste.getTotalPages());
        model.addAttribute("tamanhoPagina", tamanho);

        return "consumo-nergetico/list-consumos";
    }
}
