package br.com.superacaobikes.admin.dto;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class EmailDTO implements Serializable {

    @NotEmpty(message = "O campo e-mail é obrigatório")
    @Email(message="Email inválido")
    private String email;

    public EmailDTO(){

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
