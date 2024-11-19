package br.com.chargetech.chargetechmvc.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "CT_CONSUMO_ENERGETICO")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class ConsumoEnergetico {

    @Id
    @GeneratedValue
    @Column(name = "ID_CONSUMO_ENERGETICO")
    private Long id;

    @Column(name = "DT_REGISTRO", nullable = false)
    private LocalDateTime dataDeRegistro;

    @Column(name = "VL_CONSUMO", precision = 10, scale = 2, nullable = false)
    private BigDecimal consumo;

    @ManyToOne
    @JoinColumn(name = "ID_DISPOSITIVO", nullable = false)
    private Dispositivo dispositivo;

}
