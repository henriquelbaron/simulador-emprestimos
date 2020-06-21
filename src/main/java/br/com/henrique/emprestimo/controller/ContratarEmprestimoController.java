package br.com.henrique.emprestimo.controller;

import br.com.henrique.emprestimo.domain.ContratarEmprestimo;
import br.com.henrique.emprestimo.dto.EmprestimoDTO;
import br.com.henrique.emprestimo.service.EmprestimoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/contratar-emprestimo")
@ApiOperation(value = "Contratar Emprestimo")
public class ContratarEmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    @ApiOperation(value = "Listar todos os Contratos")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Listar todos os Contratos")})
    @GetMapping
    public List<ContratarEmprestimo> findAll() {
        return emprestimoService.findAll();
    }

    @ApiOperation(value = "Procurar contrato")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Procurar contrato")})
    @GetMapping("/{numero_contrato}")
    public ResponseEntity<ContratarEmprestimo> findAll(@PathVariable String numero_contrato) {
        ContratarEmprestimo emprestimo = emprestimoService.findByNumeroContrato(numero_contrato);

        if (emprestimo == null) {
            throw new RuntimeException("Numero de Contrato Inválido ou Inexistente");
        }
        return ResponseEntity.ok(emprestimo);
    }

    @ApiOperation(value = "Realizar Emprestimo")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Realizar Emprestimo")})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ContratarEmprestimo> emprestimo(@Valid @RequestBody EmprestimoDTO dto) {
        ContratarEmprestimo emprestimo = emprestimoService.save(dto.dtoToObject());
        return new ResponseEntity<>(emprestimo, HttpStatus.CREATED);
    }

}
