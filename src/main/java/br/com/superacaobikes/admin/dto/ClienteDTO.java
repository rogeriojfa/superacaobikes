package br.com.superacaobikes.admin.dto;

import br.com.superacaobikes.admin.domain.Cliente;
import br.com.superacaobikes.admin.services.validation.ClienteUpdate;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@ClienteUpdate
public class ClienteDTO implements Serializable {
    private Integer id;

    @NotEmpty(message = "O campo nome é obrigatório")
    @Length(min = 5, max = 120, message = "O e-mail deve ter entre 5 e 120 caracteres")
    private String nome;

    @NotEmpty(message = "O campo e-mail é obrigatório")
    @Email(message="Email inválido")
    private String email;

    public ClienteDTO(){

    }

    public ClienteDTO(Cliente obj){
        id = obj.getId();
        nome = obj.getNone();
        email = obj.getEmail();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
