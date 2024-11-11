package br.com.chargetech.chargetechmvc.dtos.dispositivo;

import java.math.BigDecimal;

public record CadastroDeDispositivoDto(
        String nome,
        BigDecimal consumoMedio,
        String ambiente
) {
}
