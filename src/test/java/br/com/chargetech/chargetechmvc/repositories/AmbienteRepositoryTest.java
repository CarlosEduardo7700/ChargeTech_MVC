package br.com.chargetech.chargetechmvc.repositories;

import br.com.chargetech.chargetechmvc.models.Ambiente;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AmbienteRepositoryTest {
    @Autowired
    private AmbienteRepository ambienteRepository;

    @Test
    @DisplayName("Deveria salvar um novo ambiente no banco de dados")
    public void salvarAmbiente() {
        Ambiente ambiente = new Ambiente();
        ambiente.setNome("TESTE");
        ambiente.setLabel("teste");
        ambiente.setDescricao("Ambiente de testes.");
        Ambiente novoAmbiente = ambienteRepository.save(ambiente);

        assertNotNull(novoAmbiente.getId());
    }

}