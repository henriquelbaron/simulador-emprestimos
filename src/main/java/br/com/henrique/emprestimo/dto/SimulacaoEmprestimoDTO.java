package br.com.henrique.emprestimo.dto;

import br.com.henrique.emprestimo.domain.SimularEmprestimo;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;


@Getter
@NoArgsConstructor
public class SimulacaoEmprestimoDTO {

    @NotNull
    @DecimalMin("0.0")
    private Double valorContratado;
    @NotNull
    @Min(0)
    @Max(24)
    private Integer quantidadeParcelas;
    @Valid
    private ClienteDTO cliente;
    @ApiModelProperty(required = false, hidden = true)
    private String numeroContrato;
    @ApiModelProperty(required = false, hidden = true)
    private LocalDate dataSimulacao;
    @ApiModelProperty(required = false, hidden = true)
    private LocalDate dataValidadeSimulacao;
    @ApiModelProperty(allowableValues = "", hidden = true)
    private Double valorParcela;
    @ApiModelProperty(required = false, hidden = true)
    private Double taxaJurosEmprestimo;

    public SimularEmprestimo dtoToObject() {
        SimularEmprestimo simularEmprestimo = new SimularEmprestimo();
        simularEmprestimo.setValorContratado(valorContratado);
        simularEmprestimo.setQuantidadeParcelas(quantidadeParcelas);
        simularEmprestimo.setCliente(cliente.dtoToObject());
        return simularEmprestimo;
    }
}
