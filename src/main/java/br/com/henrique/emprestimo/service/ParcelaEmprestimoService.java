package br.com.henrique.emprestimo.service;

import br.com.henrique.emprestimo.domain.ParcelaEmprestimo;
import br.com.henrique.emprestimo.repository.ParcelaEmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParcelaEmprestimoService {

    @Autowired
    private ParcelaEmprestimoRepository parcelaEmprestimoRepository;

    public List<ParcelaEmprestimo> findAll() {
        return parcelaEmprestimoRepository.findAll();
    }

    public ParcelaEmprestimo findById(Long id) {
        return parcelaEmprestimoRepository.findById(id).orElse(null);
    }

    public ParcelaEmprestimo save(ParcelaEmprestimo entity) {
        return parcelaEmprestimoRepository.save(entity);
    }

    public void delete(ParcelaEmprestimo entity) {
        parcelaEmprestimoRepository.delete(entity);
    }

    public void deleteById(Long id) {
        parcelaEmprestimoRepository.deleteById(id);
    }

    public boolean ifExists(Long id) {
        return parcelaEmprestimoRepository.existsById(id);
    }
}
