package br.com.henrique.emprestimo.repository;

import br.com.henrique.emprestimo.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query(value = "select * from cliente where cpf = ?1", nativeQuery = true)
    Cliente findByCpf(String cpf);
}
