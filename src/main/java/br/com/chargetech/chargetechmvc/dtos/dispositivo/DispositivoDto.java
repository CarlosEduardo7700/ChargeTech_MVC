package br.com.chargetech.chargetechmvc.dtos.dispositivo;

import br.com.chargetech.chargetechmvc.models.Dispositivo;

import java.math.BigDecimal;

public record DispositivoDto(
        Long id,
        String nome,
        BigDecimal consumoMedio,
        String ambiente,
        String status
) {
    public DispositivoDto(Dispositivo dispositivo) {
        this (
                dispositivo.getId(),
                dispositivo.getNome(),
                dispositivo.getConsumoMedio(),
                dispositivo.getAmbiente().getNome(),
                dispositivo.getStatus()
        );
    }
}
