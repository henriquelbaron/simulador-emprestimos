package br.com.henrique.emprestimo.dto;

import br.com.henrique.emprestimo.domain.SimularEmprestimo;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class SimulacaoEmprestimoDTO {

    private Double valorContratado;
    private Integer quantidadeParcelas;
    private ClienteDTO cliente;

    public SimularEmprestimo dtoToObject() {
        SimularEmprestimo simularEmprestimo = new SimularEmprestimo();
        simularEmprestimo.setValorContratado(valorContratado);
        simularEmprestimo.setQuantidadeParcelas(quantidadeParcelas);
        simularEmprestimo.setCliente(cliente.dtoToObject());
        return simularEmprestimo;
    }
}
