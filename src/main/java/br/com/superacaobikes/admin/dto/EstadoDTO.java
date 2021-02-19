package br.com.superacaobikes.admin.dto;


import br.com.superacaobikes.admin.domain.Estado;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class EstadoDTO implements Serializable {
    private Integer id;

    @NotEmpty(message="O campo nome é obrigstório")
    private String nome;

    public EstadoDTO(){

    }

    public EstadoDTO(Estado est) {
        this.id = est.getId();
        this.nome = est.getNome();
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
