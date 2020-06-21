package br.com.henrique.emprestimo.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
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
    @Column(length = 14,unique = true,nullable = false)
    private String numeroContrato;
    private LocalDate dataContratacao;
    private BigDecimal valorContratado;
    private Integer quantidadeParcelas;
    private BigDecimal taxaJurosEmprestimo;
    private BigDecimal iofContrato;
    @OneToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @JsonManagedReference
    @OneToMany(mappedBy = "emprestimo", cascade = CascadeType.ALL)
    private List<ParcelaEmprestimo> parcelas;
}
