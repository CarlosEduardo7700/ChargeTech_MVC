package br.com.chargetech.chargetechmvc.models;

import br.com.chargetech.chargetechmvc.dtos.usuario.EdicaoDoUsuarioDto;
import br.com.chargetech.chargetechmvc.repositories.GeneroRepository;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "CT_USUARIO")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue
    @Column(name = "ID_USUARIO")
    private Long id;

    @Column(name = "NM_USUARIO", length = 200, nullable = false)
    private String nome;

    @Column(name = "DS_EMAIL", length = 200, nullable = false, unique = true)
    private String email;

    @Column(name = "DS_SENHA", nullable = false)
    private String senha;

    @Column(name = "DT_NASCIMENTO", nullable = false)
    private LocalDate dataDeNascimento;

    @ManyToOne
    @JoinColumn(name = "ID_GENERO", nullable = false)
    private Genero genero;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "CT_ROLE_USUARIO",
            joinColumns = @JoinColumn(name = "ID_USUARIO"),
            inverseJoinColumns = @JoinColumn(name = "ID_ROLE")
    )
    private Set<Role> roles;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Dispositivo> dispositivos;

    public Usuario(EdicaoDoUsuarioDto usuario, Genero genero) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
        this.genero = genero;
        this.dataDeNascimento = usuario.getDataDeNascimento();
        this.roles = usuario.getRoles();
    }

    public void editarDados(EdicaoDoUsuarioDto dto, Genero genero) {

        if (dto.getNome() != null)
            this.nome = dto.getNome();
        if (dto.getEmail() != null)
            this.email = dto.getEmail();
        if (dto.getGenero() != null)
            this.genero = genero;
        if (dto.getDataDeNascimento() != null)
            this.dataDeNascimento = dto.getDataDeNascimento();
    }
}
