package br.com.chargetech.chargetechmvc.repositories;

import br.com.chargetech.chargetechmvc.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String email);
}
