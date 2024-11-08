package br.com.chargetech.chargetechmvc.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "CT_GENERO")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Genero {

    @Id
    @GeneratedValue
    @Column(name = "ID_GENERO")
    private Long id;

    @Column(name = "NM_GENERO", length = 30, nullable = false, unique = true)
    private String nome;

    @Column(name = "DS_LABEL", length = 100, nullable = false)
    private String label;

    @Column(name = "DS_GENERO", length = 200, nullable = false)
    private String descricao;

    @OneToMany(mappedBy = "genero", cascade = CascadeType.ALL)
    private List<Usuario> usuarios;

}
