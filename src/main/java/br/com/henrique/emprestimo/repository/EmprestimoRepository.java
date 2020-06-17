package br.com.henrique.emprestimo.repository;

import br.com.henrique.emprestimo.domain.Cliente;
import br.com.henrique.emprestimo.domain.ContratarEmprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmprestimoRepository extends JpaRepository<ContratarEmprestimo, Long> {
}
