package br.com.chargetech.chargetechmvc.controllers;

import br.com.chargetech.chargetechmvc.dtos.usuario.CadastroDeUsuarioDto;
import br.com.chargetech.chargetechmvc.repositories.GeneroRepository;
import br.com.chargetech.chargetechmvc.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private GeneroRepository generoRepository;

    @GetMapping("cadastrar")
    public String exibirCadastroDeUsuario(CadastroDeUsuarioDto dto, Model model) {
        model.addAttribute("usuario", dto);
        model.addAttribute("generos", generoRepository);
        model.addAttribute("roles", roleRepository.findAll());
        return "usuario/form-cadastrar";
    }

}
