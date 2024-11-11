package br.com.chargetech.chargetechmvc.repositories;

import br.com.chargetech.chargetechmvc.models.Dispositivo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DispositivoRepository extends JpaRepository<Dispositivo, Long> {
}
