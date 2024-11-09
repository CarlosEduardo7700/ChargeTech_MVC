package br.com.chargetech.chargetechmvc.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
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

    @Column(name = "DS_EMAIL", length = 200, nullable = false)
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

}
