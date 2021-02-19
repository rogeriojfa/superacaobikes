package br.com.superacaobikes.admin.dto;

import br.com.superacaobikes.admin.domain.Cidade;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class CidadeDTO implements Serializable {
    private Integer id;

    @NotEmpty(message="O campo nome é obrigstório")
    private String nome;

    public CidadeDTO(){

    }

    public CidadeDTO(Cidade cid) {
        this.id = cid.getId();
        this.nome = cid.getNome();
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
