package br.com.chargetech.chargetechmvc.controllers;

import br.com.chargetech.chargetechmvc.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;


@Controller
public class HomeController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public String exibirHome(Model model, Principal principal) {
        model.addAttribute("usuario", usuarioRepository.findByEmail(principal.getName()));
        return "home";
    }
}
