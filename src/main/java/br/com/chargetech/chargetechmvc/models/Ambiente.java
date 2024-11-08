package br.com.chargetech.chargetechmvc.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "CT_AMBIENTE")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Ambiente {

    @Id
    @GeneratedValue
    @Column(name = "ID_AMBIENTE")
    private Long id;

    @Column(name = "NM_AMBIENTE", length = 100, nullable = false)
    private String nome;

    @Column(name = "DS_AMBIENTE", length = 250, nullable = false)
    private String descricao;

    @OneToMany(mappedBy = "ambiente", cascade = CascadeType.ALL)
    private List<Dispositivo> dispositivos;
}
