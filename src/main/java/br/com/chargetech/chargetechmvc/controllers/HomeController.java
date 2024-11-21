package br.com.chargetech.chargetechmvc.controllers;

import br.com.chargetech.chargetechmvc.models.ConsumoEnergetico;
import br.com.chargetech.chargetechmvc.repositories.ConsumoEnergeticoRepository;
import br.com.chargetech.chargetechmvc.repositories.DispositivoRepository;
import br.com.chargetech.chargetechmvc.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Controller
public class HomeController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ConsumoEnergeticoRepository consumoEnergeticoRepository;

    @Autowired
    private DispositivoRepository dispositivoRepository;

    @GetMapping
    public String exibirHome(Model model, Principal principal) {

        List<ConsumoEnergetico> consumosEnergeticosNesseMes = consumoEnergeticoRepository.findAll().stream()
                .filter(logs -> logs.getDataDeRegistro().getMonthValue() == LocalDateTime.now().getMonthValue())
                .toList();

        BigDecimal quantidadeDeEnergiaConsumidaNoMes = new BigDecimal(0);

        for (ConsumoEnergetico log : consumosEnergeticosNesseMes) {
            quantidadeDeEnergiaConsumidaNoMes = quantidadeDeEnergiaConsumidaNoMes.add(log.getConsumo());
        }

        model.addAttribute("quantidadeDeEnergiaConsumidaNoMes", quantidadeDeEnergiaConsumidaNoMes);

        var dispositivosLigados = dispositivoRepository.findAll()
                .stream()
                .filter(dispositivo -> Objects.equals(dispositivo.getStatus(), "LIGADO"))
                .toList();

        var quantidadeDeDispositivosLigados = dispositivosLigados.size();

        model.addAttribute("quantidadeDeDispositivosLigados", quantidadeDeDispositivosLigados);

        model.addAttribute("usuario", usuarioRepository.findByEmail(principal.getName()));
        return "home";
    }
}
