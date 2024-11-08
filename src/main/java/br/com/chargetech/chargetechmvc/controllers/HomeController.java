package br.com.chargetech.chargetechmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    @GetMapping
    public String exibirHome(Model model) {
        return "home";
    }
}
