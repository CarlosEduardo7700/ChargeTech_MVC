package br.com.chargetech.chargetechmvc.services;

import br.com.chargetech.chargetechmvc.models.ConsumoEnergetico;
import br.com.chargetech.chargetechmvc.repositories.ConsumoEnergeticoRepository;
import br.com.chargetech.chargetechmvc.repositories.DispositivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Random;

@Service
public class ConsumoEnergeticoService {

    @Autowired
    private ConsumoEnergeticoRepository consumoEnergeticoRepository;

    @Autowired
    private DispositivoRepository dispositivoRepository;

    private Random random = new Random();

    //@Scheduled(fixedRate = 1800000)
    @Scheduled(fixedRate = 5000)
    public void simularConsumoEnergetico() {

        dispositivoRepository.findAll().forEach(dispositivo -> {
            if (Objects.equals(dispositivo.getStatus(), "LIGADO")) {
                ConsumoEnergetico consumoEnergetico = new ConsumoEnergetico();
                consumoEnergetico.setDataDeRegistro(LocalDateTime.now());
                int numeroAleatorio = random.nextInt(6);
                consumoEnergetico.setConsumo(dispositivo.getConsumoMedio().add(new BigDecimal(numeroAleatorio)));
                consumoEnergetico.setDispositivo(dispositivo);
                consumoEnergeticoRepository.save(consumoEnergetico);
            }
        });

    }

}
