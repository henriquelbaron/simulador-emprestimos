package br.com.henrique.emprestimo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "parcela_emprestimo")
public class ParcelaEmprestimo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer numeroContrato;
    private Integer numeroDaParcela;
    private Double valorParcela;
    private LocalDate dataVencimento;
    @ManyToOne
    private ContratarEmprestimo emprestimo;
}
