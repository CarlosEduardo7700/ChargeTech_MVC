package br.com.chargetech.chargetechmvc.dtos.dispositivo;

import br.com.chargetech.chargetechmvc.models.Dispositivo;

import java.math.BigDecimal;

public record ListagemDosDispositivosDto(
        String nome,
        BigDecimal consumoMedio,
        String status,
        String ambiente
) {
    public ListagemDosDispositivosDto(Dispositivo dispositivo) {
        this (
                dispositivo.getNome(),
                dispositivo.getConsumoMedio(),
                dispositivo.getStatus(),
                dispositivo.getAmbiente().getLabel()
        );
    }
}
