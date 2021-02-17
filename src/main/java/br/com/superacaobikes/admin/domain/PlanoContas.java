package br.com.superacaobikes.admin.domain;

import br.com.superacaobikes.admin.domain.enums.TipoPlano;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class PlanoContas implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private Integer tipo;

    public PlanoContas(){

    }

    public PlanoContas(Integer id, String nome, TipoPlano tipo) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo.getCod();
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

    public TipoPlano getTipo() {
        return TipoPlano.toEnum(tipo);
    }

    public void setTipo(TipoPlano tipo) {
        this.tipo = tipo.getCod();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlanoContas that = (PlanoContas) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

}
