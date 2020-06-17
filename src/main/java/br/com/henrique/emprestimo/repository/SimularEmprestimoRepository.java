package br.com.henrique.emprestimo.repository;

import br.com.henrique.emprestimo.domain.Cliente;
import br.com.henrique.emprestimo.domain.SimularEmprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SimularEmprestimoRepository extends JpaRepository<SimularEmprestimo, Long> {
}
