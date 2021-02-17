package br.com.superacaobikes.admin.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

@Entity
public class ItemPedido {

    @JsonIgnore
    @EmbeddedId
    private ItemPedidoPK id = new ItemPedidoPK();
    private Double desconto;
    private Integer quantidade;
    private Double preco;

    public ItemPedido(){

    }

    public ItemPedido(Pedido pedido, Produto produto, Double desconto, Integer quantidade, Double preco) {
        id.setPedido(pedido);
        id.setProduto(produto);
        this.desconto = desconto;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public double getSubTotal(){
        return (preco-desconto) * quantidade;
    }

    @JsonIgnore
    public Pedido getPedido(){
        return id.getPedido();
    }

    public void setPedido(Pedido pedido) {id.setPedido(pedido);}

    public Produto getProduto(){
        return id.getProduto();
    }

    public void setProduto(Produto produto) {id.setProduto(produto);}

    public ItemPedidoPK getId() {
        return id;
    }

    public void setId(ItemPedidoPK id) {
        this.id = id;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemPedido that = (ItemPedido) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        final StringBuilder sb = new StringBuilder("ItemPedido{");
        sb.append(getProduto().getNome());
        sb.append(", qte: =").append(getQuantidade());
        sb.append(", Preço Unitário: ").append(nf.format(getPreco()));
        sb.append(", Subtotal: ").append(nf.format(getSubTotal()));
        sb.append("\n");
        sb.append('}');
        return sb.toString();
    }
}
