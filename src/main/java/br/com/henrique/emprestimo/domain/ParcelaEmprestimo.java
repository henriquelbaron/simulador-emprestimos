package br.com.henrique.emprestimo.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Entity(name = "parcela_emprestimo")
public class ParcelaEmprestimo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeroContrato;
    private Integer numeroDaParcela;
    private BigDecimal valorParcela;
    private LocalDate dataVencimento;
    @ManyToOne
    @JoinColumn(name = "emprestimo_id")
    @JsonBackReference
    private ContratarEmprestimo emprestimo;

}
