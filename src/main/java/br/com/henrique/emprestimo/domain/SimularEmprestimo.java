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
@Entity(name = "simular_emprestimo")
public class SimularEmprestimo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer numeroContrato;
    private LocalDate dataSimulacao;
    private LocalDate dataValidadeSimulacao;
    private Double valorContratado;
    private Integer quantidadeParcelas;
    private Double valorParcela;
    private Double taxaJurosEmprestimo;
    @OneToOne(cascade=CascadeType.ALL)
    private Cliente cliente;
}

