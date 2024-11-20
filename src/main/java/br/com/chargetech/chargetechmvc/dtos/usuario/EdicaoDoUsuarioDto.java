package br.com.chargetech.chargetechmvc.dtos.usuario;

import br.com.chargetech.chargetechmvc.models.Role;
import br.com.chargetech.chargetechmvc.models.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class EdicaoDoUsuarioDto {

    private Long id;
    private String nome;
    private String email;
    private String senha;
    private LocalDate dataDeNascimento;
    private String genero;
    private Set<Role> roles;

    public EdicaoDoUsuarioDto(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
        this.dataDeNascimento = usuario.getDataDeNascimento();
        this.genero = usuario.getGenero().getNome();
        this.roles = usuario.getRoles();
    }
}
