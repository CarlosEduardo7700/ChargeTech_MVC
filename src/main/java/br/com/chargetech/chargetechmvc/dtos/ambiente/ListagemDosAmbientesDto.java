package br.com.chargetech.chargetechmvc.dtos.ambiente;

import br.com.chargetech.chargetechmvc.models.Ambiente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ListagemDosAmbientesDto {

    private String label;
    private String descricao;


    public ListagemDosAmbientesDto(Ambiente ambiente) {
        this.label = ambiente.getLabel();
        this.descricao = ambiente.getDescricao();
    }
}