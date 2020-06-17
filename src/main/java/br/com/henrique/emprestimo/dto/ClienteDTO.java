package br.com.henrique.emprestimo.dto;

import br.com.henrique.emprestimo.domain.Cliente;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ClienteDTO {

    private String cpf;
    private String nome;
    private String email;

    public Cliente dtoToObject() {
        return new Cliente(cpf, nome, email);
    }
}
