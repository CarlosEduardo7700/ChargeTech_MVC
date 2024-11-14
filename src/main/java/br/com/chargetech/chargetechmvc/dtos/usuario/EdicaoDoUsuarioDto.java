package br.com.chargetech.chargetechmvc.dtos.usuario;

import br.com.chargetech.chargetechmvc.models.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class EdicaoDoUsuarioDto {

    private String nome;
    private String email;
    private LocalDate dataDeNascimento;
    private String genero;

    public EdicaoDoUsuarioDto(Usuario usuario) {
    }
}
