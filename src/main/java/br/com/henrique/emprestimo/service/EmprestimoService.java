package br.com.henrique.emprestimo.service;

import br.com.henrique.emprestimo.domain.ContratarEmprestimo;
import br.com.henrique.emprestimo.repository.EmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    public List<ContratarEmprestimo> findAll() {
        return emprestimoRepository.findAll();
    }

    public ContratarEmprestimo findById(Long id) {
        return emprestimoRepository.findById(id).orElse(null);
    }

    public ContratarEmprestimo save(ContratarEmprestimo entity) {
        return emprestimoRepository.save(entity);
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
