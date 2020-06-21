package br.com.henrique.emprestimo.dto;

import br.com.henrique.emprestimo.domain.ContratarEmprestimo;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class EmprestimoDTO {

    @Valid
    ClienteDTO cliente;
    @NotBlank
    @Size(min = 14, max = 14)
    private String numeroDaSimulacao;

    public ContratarEmprestimo dtoToObject() {
        ContratarEmprestimo emprestimo = new ContratarEmprestimo();
        emprestimo.setNumeroContrato(numeroDaSimulacao);
        emprestimo.setCliente(cliente.dtoToObject());
        return emprestimo;
    }
}
