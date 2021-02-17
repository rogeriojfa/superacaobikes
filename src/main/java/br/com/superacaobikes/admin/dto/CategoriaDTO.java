package br.com.superacaobikes.admin.dto;

import br.com.superacaobikes.admin.domain.Categoria;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;


public class CategoriaDTO implements Serializable {

    private Integer id;

    @NotEmpty(message="O campo nome é obrigstório")
    @Length(min=5, max=80, message="O tamanho máximo deve ser entre 5 e 80 caracteres")
    private String nome;

    public CategoriaDTO(){

    }

    public CategoriaDTO(Categoria cat) {
        id = cat.getId();
        nome = cat.getNome();
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
}
