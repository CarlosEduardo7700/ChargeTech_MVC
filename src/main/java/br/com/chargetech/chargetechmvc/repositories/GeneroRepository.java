package br.com.chargetech.chargetechmvc.repositories;

import br.com.chargetech.chargetechmvc.models.Genero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneroRepository extends JpaRepository<Genero, Long> {
    Genero findByNome(String nome);
}
