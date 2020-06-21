package br.com.henrique.emprestimo.dto;

import br.com.henrique.emprestimo.domain.SimularEmprestimo;
import br.com.henrique.emprestimo.util.UtilsDate;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;


@Getter
@NoArgsConstructor
public class SimulacaoEmprestimoDTOOutput {

    private String numeroContrato;
    private String dataAtual;
    private String dataValidade;
    private String valorContrato;
    private String quantidadeParcelas;
    private String valorParcela;
    private String taxaJuros;

    public SimulacaoEmprestimoDTOOutput(SimularEmprestimo simulacao) {
        this.numeroContrato = simulacao.getNumeroContrato();
        this.quantidadeParcelas = simulacao.getQuantidadeParcelas().toString();
        this.dataAtual = UtilsDate.dataFormatada(simulacao.getDataSimulacao());
        this.dataValidade = UtilsDate.dataFormatada(simulacao.getDataValidadeSimulacao());
        this.valorParcela = NumberFormat.getCurrencyInstance().format(simulacao.getValorParcela());
        this.valorContrato = NumberFormat.getCurrencyInstance().format(simulacao.getValorContratado());
        this.taxaJuros = simulacao.getTaxaJurosEmprestimo().multiply(new BigDecimal(10)).setScale(1, RoundingMode.HALF_UP).toString();
    }


}
