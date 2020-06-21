package br.com.henrique.emprestimo.dto;

import br.com.henrique.emprestimo.domain.Cliente;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class ClienteDTO {

    @CPF
    private String cpf;
    @NotBlank
    private String nome;
    @Email
    private String email;

    public Cliente dtoToObject() {
        cpf = cpf.replaceAll("[.-]", "");
        return new Cliente(cpf, nome, email);
    }

}
