package br.com.chargetech.chargetechmvc.dtos.usuario;

import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;

public record CadastroDeUsuarioDto(
        @NotBlank(message = "Nome é obrigatório!")
        @Size(max = 200, message = "Nome deve ter no máximo 200 caracteres.")
        String nome,
        @NotBlank(message = "Email é obrigatório!")
        @Size(max = 200, message = "Email deve ter no máximo 200 caracteres.")
        @Email(message = "Formato de email inválido.")
        String email,
        @NotBlank(message = "Senha é obrigatória!")
        @Pattern(
                regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
                message = "A senha deve conter ao menos um número, uma letra maiúscula, uma letra minúscula, um caractere especial e não pode conter espaços em branco."
        )
        String senha,
        @NotNull(message = "Data de nascimento é obrigatória!")
        @Past(message = "A data de nascimento deve estar no passado")
        LocalDate dataDeNascimento,
        String genero,
        List<String> roles
) {
}
