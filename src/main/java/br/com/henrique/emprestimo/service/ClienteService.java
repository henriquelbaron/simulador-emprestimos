package br.com.henrique.emprestimo.service;

import br.com.henrique.emprestimo.domain.Cliente;
import br.com.henrique.emprestimo.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }


    public Cliente findById(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public Cliente save(Cliente entity) {
        return clienteRepository.save(entity);
    }

    public void delete(Cliente entity) {
        clienteRepository.delete(entity);
    }

    public void deleteById(Long id) {
        clienteRepository.deleteById(id);
    }

    public boolean ifExists(Long id) {
        return clienteRepository.existsById(id);
    }
}
