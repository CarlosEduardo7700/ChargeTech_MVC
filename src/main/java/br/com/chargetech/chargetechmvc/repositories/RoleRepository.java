package br.com.chargetech.chargetechmvc.repositories;

import br.com.chargetech.chargetechmvc.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
