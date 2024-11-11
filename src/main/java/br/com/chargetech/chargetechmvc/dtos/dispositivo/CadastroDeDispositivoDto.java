package br.com.chargetech.chargetechmvc.dtos.dispositivo;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record CadastroDeDispositivoDto(
        @NotBlank(message = "Nome é obrigatório!")
        @Size(max = 150, message = "Nome deve ter no máximo 150 caracteres.")
        String nome,
        @NotNull(message = "Consumo médio é obrigatório!")
        @Digits(integer = 10, fraction = 2, message = "Consumo médio deve ter até 8 digitos antes da vírgula e até 2 digitos após a vírgula.")
        BigDecimal consumoMedio,
        String ambiente
) {
}
