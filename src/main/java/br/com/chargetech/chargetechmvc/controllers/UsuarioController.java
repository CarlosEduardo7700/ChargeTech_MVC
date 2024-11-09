package br.com.chargetech.chargetechmvc.controllers;

import br.com.chargetech.chargetechmvc.dtos.usuario.CadastroDeUsuarioDto;
import br.com.chargetech.chargetechmvc.repositories.GeneroRepository;
import br.com.chargetech.chargetechmvc.repositories.RoleRepository;
import br.com.chargetech.chargetechmvc.repositories.UsuarioRepository;
import br.com.chargetech.chargetechmvc.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private GeneroRepository generoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("cadastrar")
    public String exibirCadastroDeUsuario(CadastroDeUsuarioDto dto, Model model) {
        model.addAttribute("usuario", dto);
        model.addAttribute("generos", generoRepository.findAll());
        model.addAttribute("roles", roleRepository.findAll());
        return "usuario/form-cadastrar";
    }

    @PostMapping("cadastrar")
    public String cadastrarUsuario(@Valid @ModelAttribute("usuario") CadastroDeUsuarioDto dto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("generos", generoRepository.findAll());
            model.addAttribute("roles", roleRepository.findAll());
            return "usuario/form-cadastrar";
        }
        usuarioService.saveUser(dto, passwordEncoder);
        return "redirect:/login";
    }

}
