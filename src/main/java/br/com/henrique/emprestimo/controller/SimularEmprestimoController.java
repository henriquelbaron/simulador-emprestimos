package br.com.henrique.emprestimo.controller;

import br.com.henrique.emprestimo.domain.SimularEmprestimo;
import br.com.henrique.emprestimo.dto.SimulacaoEmprestimoDTOInput;
import br.com.henrique.emprestimo.dto.SimulacaoEmprestimoDTOOutput;
import br.com.henrique.emprestimo.service.SimularEmprestimoService;
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
@RequestMapping("/simular-emprestimo")
@ApiOperation(value = "Simular Emprestimos")
public class SimularEmprestimoController {

    @Autowired
    private SimularEmprestimoService simularEmprestimoService;

    @ApiOperation(value = "Listar todas as Simulações")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Listar todas as Simulações")})
    @GetMapping
    public List<SimularEmprestimo> findAll() {
        return simularEmprestimoService.findAll();
    }

    @ApiOperation(value = "Procurar Simulações")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Procurar Simulações")})
    @GetMapping("/{numeroContrato}")
    public ResponseEntity<SimularEmprestimo> findAll(@PathVariable String numeroContrato) {
        SimularEmprestimo simularEmprestimo = simularEmprestimoService.findByNumeroContrato(numeroContrato);

        if (simularEmprestimo == null) {
            throw new RuntimeException("Numero de Contrato Inválido ou Inexistente");
        }

        return ResponseEntity.ok(simularEmprestimo);
    }

    @ApiOperation(value = "Realizar Simulação de Emprestimo")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Realizar Simulação de Emprestimo")})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<SimulacaoEmprestimoDTOOutput> simularEmprestimo(@Valid @RequestBody SimulacaoEmprestimoDTOInput dto) {
        SimularEmprestimo simularEmprestimo = dto.dtoToObject();
        simularEmprestimo = simularEmprestimoService.save(simularEmprestimo);
        return new ResponseEntity<>(new SimulacaoEmprestimoDTOOutput(simularEmprestimo), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Alterar Simulação")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Alterar Simulação")})
    @PutMapping("/{id}")
    public ResponseEntity<SimularEmprestimo> change(@PathVariable Long id, @Valid @RequestBody SimulacaoEmprestimoDTOInput dto) {
        if (!simularEmprestimoService.ifExists(id)) {
            return ResponseEntity.notFound().build();
        }
        SimularEmprestimo simularEmprestimo = dto.dtoToObject();
        simularEmprestimo.setId(id);
        simularEmprestimo = simularEmprestimoService.save(simularEmprestimo);
        return ResponseEntity.ok(simularEmprestimo);
    }

    @ApiOperation(value = "Deletar Simulação")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Deletar Simulação")})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!simularEmprestimoService.ifExists(id)) {
            return ResponseEntity.notFound().build();
        }
        simularEmprestimoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
