package br.com.chargetech.chargetechmvc.repositories;

import br.com.chargetech.chargetechmvc.models.ConsumoEnergetico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsumoEnergeticoRepository extends JpaRepository<ConsumoEnergetico, Long> {
}
