package br.com.chargetech.chargetechmvc.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CT_ROLE")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue
    @Column(name = "ID_ROLE")
    private Long id;

    @Column(name = "NM_ROLE", length = 25, nullable = false, unique = true)
    private String nome;

    @Column(name = "DS_LABEL", length = 150, nullable = false)
    private String label;

}
