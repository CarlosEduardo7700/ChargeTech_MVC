package br.com.chargetech.chargetechmvc.controllers;

import br.com.chargetech.chargetechmvc.dtos.dispositivo.DispositivoDto;
import br.com.chargetech.chargetechmvc.dtos.dispositivo.EdicaoDoDispositivoDto;
import br.com.chargetech.chargetechmvc.dtos.usuario.CadastroDeUsuarioDto;
import br.com.chargetech.chargetechmvc.dtos.usuario.EdicaoDoUsuarioDto;
import br.com.chargetech.chargetechmvc.models.Dispositivo;
import br.com.chargetech.chargetechmvc.models.Usuario;
import br.com.chargetech.chargetechmvc.repositories.GeneroRepository;
import br.com.chargetech.chargetechmvc.repositories.RoleRepository;
import br.com.chargetech.chargetechmvc.repositories.UsuarioRepository;
import br.com.chargetech.chargetechmvc.services.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

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

    @GetMapping("editar/{id}")
    public String exibirFormularioEditar(@PathVariable("id") Long id, Model model) {
        EdicaoDoUsuarioDto dto = new EdicaoDoUsuarioDto(usuarioRepository.getReferenceById(id));
        model.addAttribute("usuario", dto);
        model.addAttribute("generos", generoRepository.findAll());
        return "usuario/form-editar";
    }

    @PostMapping("editar")
    public String editarUsuario(
            @Valid @ModelAttribute("usuario") EdicaoDoUsuarioDto dto,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            Model model,
            HttpServletRequest request,
            HttpServletResponse response
    ){
        if (result.hasErrors()) {
            model.addAttribute("generos", generoRepository.findAll());
            return "usuario/form-editar";
        }
        Usuario usuario = new Usuario(dto, generoRepository.findByNome(dto.getGenero()));

        usuarioRepository.save(usuario);
        redirectAttributes.addFlashAttribute("mensagem", "O usuário foi atualizado!");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }

        return "redirect:/login?logout";
    }

}
