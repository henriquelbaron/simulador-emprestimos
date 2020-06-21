package br.com.henrique.emprestimo.dto;

import br.com.henrique.emprestimo.domain.SimularEmprestimo;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@Getter
@NoArgsConstructor
public class SimulacaoEmprestimoDTOInput {

    @NotNull
    @DecimalMin("1.0")
    private Double valorContratado;
    @NotNull
    @Min(2)
    @Max(24)
    private Integer quantidadeParcelas;
    @Valid
    private ClienteDTO cliente;

    public SimularEmprestimo dtoToObject() {
        SimularEmprestimo simularEmprestimo = new SimularEmprestimo();
        simularEmprestimo.setValorContratado(new BigDecimal(valorContratado));
        simularEmprestimo.setQuantidadeParcelas(quantidadeParcelas);
        simularEmprestimo.setCliente(cliente.dtoToObject());
        return simularEmprestimo;
    }
}
