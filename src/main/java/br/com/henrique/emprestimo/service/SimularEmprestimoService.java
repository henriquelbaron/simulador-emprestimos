package br.com.henrique.emprestimo.service;

import br.com.henrique.emprestimo.domain.SimularEmprestimo;
import br.com.henrique.emprestimo.repository.SimularEmprestimoRepository;
import br.com.henrique.emprestimo.util.UtilsDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.util.List;

@Service
public class SimularEmprestimoService {

    @Autowired
    private SimularEmprestimoRepository simularEmprestimoRepository;

    @Autowired
    private ClienteService clienteService;

    public String gerarNumeroDoContrato() {
        String ultimoContrato = simularEmprestimoRepository.pegaUltimoNumeroContrato();
        String numeroContrato = ultimoContrato == null ? "000000" : ultimoContrato.substring(8, 14);
        numeroContrato = String.format("%06d", Integer.valueOf(numeroContrato) + 1);
        numeroContrato = UtilsDate.getyyyyMMdd() + numeroContrato;
        return numeroContrato;
    }

    private BigDecimal calcularValorParcela(Integer parcelas, BigDecimal valorContrato, BigDecimal taxaJuros) {
        BigDecimal parcelasBigDc = new BigDecimal(parcelas);
        BigDecimal valorParcela = valorContrato.multiply(new BigDecimal(1).add(taxaJuros.multiply(parcelasBigDc)))
                .divide(parcelasBigDc, MathContext.DECIMAL128);
        return valorParcela;
    }

    private BigDecimal calculaJuros(Integer parcelas, BigDecimal valorContrato) {
        BigDecimal taxaJuros = new BigDecimal(valorContrato.doubleValue() > 1000 ? "0.30" : "0.18")
                .add(new BigDecimal(parcelas > 12 ? "0.05" : "0"));
        return taxaJuros;
    }

    public List<SimularEmprestimo> findAll() {
        return simularEmprestimoRepository.findAll();
    }

    public SimularEmprestimo findById(Long id) {
        return simularEmprestimoRepository.findById(id).orElse(null);
    }

    public SimularEmprestimo save(SimularEmprestimo entity) {
        entity.setCliente(clienteService.findByCpf(entity.getCliente()));
        String numeroContrato = gerarNumeroDoContrato();
        BigDecimal taxaJuros = calculaJuros(entity.getQuantidadeParcelas(), entity.getValorContratado());
        BigDecimal valorParcelas = calcularValorParcela(entity.getQuantidadeParcelas(), entity.getValorContratado(), taxaJuros);
        entity.setValorParcela(valorParcelas);
        entity.setTaxaJurosEmprestimo(taxaJuros);
        entity.setNumeroContrato(numeroContrato);
        entity.setDataSimulacao(LocalDate.now());
        entity.setDataValidadeSimulacao(LocalDate.now().plusDays(30));
        return simularEmprestimoRepository.save(entity);
    }

    public SimularEmprestimo findByNumeroContrato(String numeroContrato){
        return simularEmprestimoRepository.findByNumeroContrato(numeroContrato);
    }

    public void delete(SimularEmprestimo entity) {
        simularEmprestimoRepository.delete(entity);
    }

    public void deleteById(Long id) {
        simularEmprestimoRepository.deleteById(id);
    }

    public boolean ifExists(Long id) {
        return simularEmprestimoRepository.existsById(id);
    }
}
