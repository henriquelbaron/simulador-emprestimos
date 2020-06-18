package br.com.henrique.emprestimo.controller;

import br.com.henrique.emprestimo.domain.Cliente;
import br.com.henrique.emprestimo.dto.ClienteDTO;
import br.com.henrique.emprestimo.service.ClienteService;
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
@RequestMapping("/cliente")
@ApiOperation(value = "Clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @ApiOperation(value = "Listar todos os Clientes")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Listar todos os Clientes")})
    @GetMapping
    public List<Cliente> findAll() {
        return clienteService.findAll();
    }


    @ApiOperation(value = "Procurar CLiente")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Procurar CLiente")})
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findAll(@PathVariable Long id) {
        Cliente cliente = clienteService.findById(id);

        if (cliente != null) return ResponseEntity.ok(cliente);

        return ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "Cadastrar Cliente")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Cadastrar Cliente")})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Cliente> create(@Valid @RequestBody ClienteDTO dto) {
        Cliente cliente = clienteService.save(dto.dtoToObject());
        return new ResponseEntity<>(cliente, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Alterar Cliente")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Alterar Cliente")})
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> change(@Valid @PathVariable Long id, @RequestBody Cliente cliente) {
        if (!clienteService.ifExists(id)) {
            return ResponseEntity.notFound().build();
        }
        cliente.setId(id);
        cliente = clienteService.save(cliente);
        return ResponseEntity.ok(cliente);
    }

    @ApiOperation(value = "Deleta Cliente")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Deleta Cliente")})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!clienteService.ifExists(id)) {
            return ResponseEntity.notFound().build();
        }
        clienteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
