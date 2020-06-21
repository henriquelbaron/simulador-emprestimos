package br.com.henrique.emprestimo.service;

import br.com.henrique.emprestimo.domain.ContratarEmprestimo;
import br.com.henrique.emprestimo.domain.ParcelaEmprestimo;
import br.com.henrique.emprestimo.domain.SimularEmprestimo;
import br.com.henrique.emprestimo.repository.EmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private SimularEmprestimoService simularEmprestimoService;

    public ContratarEmprestimo findByNumeroContrato(String numeroContrato) {
        return emprestimoRepository.findByNumeroContrato(numeroContrato);
    }

    public List<ContratarEmprestimo> findAll() {
        return emprestimoRepository.findAll();
    }

    public ContratarEmprestimo findById(Long id) {
        return emprestimoRepository.findById(id).orElse(null);
    }

    public ContratarEmprestimo save(ContratarEmprestimo entity)  {
        SimularEmprestimo simulacao = simularEmprestimoService.findByNumeroContrato(entity.getNumeroContrato());
        if (simulacao == null){
            throw  new RuntimeException("Numero de Contrato Inválido ou Inexistente");
        }
        if (simulacao.getDataValidadeSimulacao().isBefore(LocalDate.now())) {
            throw new RuntimeException("Data de validade da Simulação Expirada, Realize uma nova Simulação.");
        }
        if (!entity.getCliente().getCpf().equals(simulacao.getCliente().getCpf()) ||
                !entity.getCliente().getEmail().equals(simulacao.getCliente().getEmail()) ||
                !entity.getCliente().getNome().equalsIgnoreCase(simulacao.getCliente().getNome())){
            throw new RuntimeException("Os dados fornecidos não conferem com os dados da simulação.");
        }

        if(emprestimoRepository.findByNumeroContrato(entity.getNumeroContrato()) != null){
            throw  new RuntimeException("Este contrato ja foi efetivado! Para um novo, realizar uma nova simulação");
        }

        ContratarEmprestimo emprestimo = simulacao.simulacaoToContrato();
        List<ParcelaEmprestimo> parcelas = new ArrayList<>();
        for (int i = 1; i <= simulacao.getQuantidadeParcelas(); i++) {
            ParcelaEmprestimo parcela = new ParcelaEmprestimo();
            parcela.setNumeroContrato(emprestimo.getNumeroContrato());
            parcela.setValorParcela(simulacao.getValorParcela());
            parcela.setNumeroDaParcela(i);
            parcela.setDataVencimento(emprestimo.getDataContratacao().plusMonths(i));
            parcela.setEmprestimo(emprestimo);
            parcelas.add(parcela);
        }
        emprestimo.setParcelas(parcelas);
        return emprestimoRepository.save(emprestimo);
    }

    public void delete(ContratarEmprestimo entity) {
        emprestimoRepository.delete(entity);
    }

    public void deleteById(Long id) {
        emprestimoRepository.deleteById(id);
    }

    public boolean ifExists(Long id) {
        return emprestimoRepository.existsById(id);
    }

}
