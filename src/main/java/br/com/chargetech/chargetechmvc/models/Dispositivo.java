package br.com.chargetech.chargetechmvc.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "CT_DISPOSITIVO")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Dispositivo {

    @Id
    @GeneratedValue
    @Column(name = "ID_DISPOSITIVO")
    private Long id;

    @Column(name = "NM_DISPOSITIVO", length = 150, nullable = false)
    private String nome;

    @Column(name = "VL_CONSUMO_MEDIO", precision = 10, scale = 2, nullable = false)
    private BigDecimal consumoMedio;

    @Column(name = "ST_DISPOSITIVO", length = 15, nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "ID_AMBIENTE", nullable = false)
    private Ambiente ambiente;

    @OneToMany(mappedBy = "dispositivo", cascade = CascadeType.ALL)
    private List<ConsumoEnergetico> consumosEnergeticos;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "CT_DISPOSITIVOS_USUARIO",
            joinColumns = @JoinColumn(name = "ID_DISPOSITIVO"),
            inverseJoinColumns = @JoinColumn(name = "ID_USUARIO")
    )
    private List<Usuario> usuarios;

}
