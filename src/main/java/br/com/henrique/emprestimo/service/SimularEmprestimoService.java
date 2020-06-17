package br.com.henrique.emprestimo.service;

import br.com.henrique.emprestimo.domain.Cliente;
import br.com.henrique.emprestimo.domain.SimularEmprestimo;
import br.com.henrique.emprestimo.repository.SimularEmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimularEmprestimoService {

    @Autowired
    private SimularEmprestimoRepository simularEmprestimoService;

    public List<SimularEmprestimo> findAll() {
        return simularEmprestimoService.findAll();
    }


    public SimularEmprestimo findById(Long id) {
        return simularEmprestimoService.findById(id).orElse(null);
    }

    public SimularEmprestimo save(SimularEmprestimo entity) {
        return simularEmprestimoService.save(entity);
    }

    public void delete(SimularEmprestimo entity) {
        simularEmprestimoService.delete(entity);
    }

    public void deleteById(Long id) {
        simularEmprestimoService.deleteById(id);
    }

    public boolean ifExists(Long id) {
        return simularEmprestimoService.existsById(id);
    }
}
