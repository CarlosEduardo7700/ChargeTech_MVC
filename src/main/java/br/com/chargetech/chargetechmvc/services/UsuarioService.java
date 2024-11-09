package br.com.chargetech.chargetechmvc.services;

import br.com.chargetech.chargetechmvc.dtos.usuario.CadastroDeUsuarioDto;
import br.com.chargetech.chargetechmvc.models.Role;
import br.com.chargetech.chargetechmvc.models.Usuario;
import br.com.chargetech.chargetechmvc.repositories.GeneroRepository;
import br.com.chargetech.chargetechmvc.repositories.RoleRepository;
import br.com.chargetech.chargetechmvc.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private GeneroRepository generoRepository;

    @Autowired
    private RoleRepository roleRepository;

    public void saveUser(CadastroDeUsuarioDto dto, PasswordEncoder passwordEncoder){
        Usuario usuario = new Usuario();

        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setSenha(passwordEncoder.encode(dto.senha()));
        usuario.setDataDeNascimento(dto.dataDeNascimento());
        usuario.setGenero(generoRepository.findByNome(dto.genero()));

        HashSet<Role> rolesDoUsuario = new HashSet<>();
        for (String nomeDaRole : dto.roles()) {
            Role role = roleRepository.findByNome(nomeDaRole);
            if (role != null) {
                rolesDoUsuario.add(role);
            }
        }
        usuario.setRoles(rolesDoUsuario);

        usuarioRepository.save(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email);

        if (usuario == null) {
            throw new UsernameNotFoundException("O email do usuário não foi encontrado em nossa base de dados!");
        }

        Set<SimpleGrantedAuthority> authorities = usuario.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getNome()))
                .collect(Collectors.toSet());

        return new User(
                usuario.getEmail(),
                usuario.getSenha(),
                authorities
        );
    }
}
