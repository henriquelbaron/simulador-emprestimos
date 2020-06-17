package br.com.henrique.emprestimo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "contratar_emprestimo")
public class ContratarEmprestimo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer numeroContrato;
    private LocalDate dataContratacao;
    private Double valorContratado;
    private Integer quantidadeParcelas;
    private Double taxaJurosEmprestimo;
    private Double iofContrato;
    @OneToOne
    @JoinColumn(name="simulacao_id", nullable = false)
    private SimularEmprestimo simularEmprestimo;
    @OneToMany(mappedBy = "emprestimo", cascade = CascadeType.ALL)
    private List<ParcelaEmprestimo> parcelas;
}
