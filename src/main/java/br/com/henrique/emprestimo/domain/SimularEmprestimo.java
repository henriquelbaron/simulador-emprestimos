package br.com.henrique.emprestimo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "simular_emprestimo")
public class SimularEmprestimo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 14, unique = true, nullable = false)
    private String numeroContrato;
    private LocalDate dataSimulacao;
    private LocalDate dataValidadeSimulacao;
    private BigDecimal valorContratado;
    private Integer quantidadeParcelas;
    private BigDecimal valorParcela;
    private BigDecimal taxaJurosEmprestimo;
    @OneToOne(cascade = CascadeType.ALL)
    private Cliente cliente;

    public ContratarEmprestimo simulacaoToContrato() {
        ContratarEmprestimo emprestimo = new ContratarEmprestimo();
        emprestimo.setTaxaJurosEmprestimo(taxaJurosEmprestimo);
        emprestimo.setQuantidadeParcelas(quantidadeParcelas);
        emprestimo.setValorContratado(valorContratado);
        emprestimo.setDataContratacao(LocalDate.now());
        emprestimo.setNumeroContrato(numeroContrato);
        emprestimo.setCliente(cliente);
        return emprestimo;
    }
}

