package br.com.henrique.emprestimo.repository;

import br.com.henrique.emprestimo.domain.SimularEmprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SimularEmprestimoRepository extends JpaRepository<SimularEmprestimo, Long> {

    @Query(value = "select Max(numero_contrato) from simular_emprestimo", nativeQuery = true)
    String pegaUltimoNumeroContrato();

    SimularEmprestimo findByNumeroContrato(String numeroContrato);
}
