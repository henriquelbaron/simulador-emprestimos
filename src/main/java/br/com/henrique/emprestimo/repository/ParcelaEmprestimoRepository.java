package br.com.henrique.emprestimo.repository;

import br.com.henrique.emprestimo.domain.ContratarEmprestimo;
import br.com.henrique.emprestimo.domain.ParcelaEmprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParcelaEmprestimoRepository extends JpaRepository<ParcelaEmprestimo, Long> {
}
