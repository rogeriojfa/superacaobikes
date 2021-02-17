package br.com.superacaobikes.admin.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class Produto implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private Double vlrCompra;
    private Double vlrVenda;
    private String tamanho;
    private String unidade;
    private String Obs;
    private Double margem;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "PRODUTO_CATEGORIA",
           joinColumns = @JoinColumn(name = "produto_id"),
           inverseJoinColumns = @JoinColumn(name = "categoria_id")
    )
    private List<Categoria> categorias = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "id.produto")
    private Set<ItemPedido> itens = new HashSet<>();

    public Produto(){

    }

    public Produto(Integer id, String nome, Double vlrCompra, Double vlrVenda, String tamanho, String unidade, String obs, Double margem) {
        this.id = id;
        this.nome = nome;
        this.vlrCompra = vlrCompra;
        this.vlrVenda = vlrVenda;
        this.tamanho = tamanho;
        this.unidade = unidade;
        Obs = obs;
        this.margem = margem;
    }

    @JsonIgnore
    public List<Pedido> getPedidos(){
        List<Pedido> lista = new ArrayList<>();
        for(ItemPedido x : itens){
            lista.add(x.getPedido());
        }
        return lista;
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

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public Set<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(Set<ItemPedido> itens) {
        this.itens = itens;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(getId(), produto.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }


}
