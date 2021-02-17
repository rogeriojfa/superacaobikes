package br.com.superacaobikes.admin.dto;

import br.com.superacaobikes.admin.domain.Produto;

import java.io.Serializable;

public class ProdutoDTO implements Serializable {
    private Integer id;
    private String nome;
    private Double vlrCompra;
    private Double vlrVenda;
    private String tamanho;
    private String unidade;
    private String Obs;
    private Double margem;

    public ProdutoDTO(){

    }

    public ProdutoDTO(Produto obj){
        id = obj.getId();
        nome = obj.getNome();
        vlrCompra = obj.getVlrCompra();
        vlrVenda = obj.getVlrVenda();
        tamanho = obj.getTamanho();
        unidade = obj.getUnidade();
        Obs = obj.getObs();
        margem = obj.getMargem();
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

    public Double getVlrCompra() {
        return vlrCompra;
    }

    public void setVlrCompra(Double vlrCompra) {
        this.vlrCompra = vlrCompra;
    }

    public Double getVlrVenda() {
        return vlrVenda;
    }

    public void setVlrVenda(Double vlrVenda) {
        this.vlrVenda = vlrVenda;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String getObs() {
        return Obs;
    }

    public void setObs(String obs) {
        Obs = obs;
    }

    public Double getMargem() {
        return margem;
    }

    public void setMargem(Double margem) {
        this.margem = margem;
    }
}
