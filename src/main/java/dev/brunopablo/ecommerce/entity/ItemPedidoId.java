package dev.brunopablo.ecommerce.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class ItemPedidoId {

    @ManyToOne
    @JoinColumn(name = "id_pedido")
    private PedidoEntidade pedido;
    
    @ManyToOne
    @JoinColumn(name = "id_produto")
    private ProdutoEntidade produto;

    public ItemPedidoId() {
    }

    public ItemPedidoId(PedidoEntidade pedido, ProdutoEntidade produto) {
        this.pedido = pedido;
        this.produto = produto;
    }

    public PedidoEntidade getPedido() {
        return pedido;
    }

    public void setPedido(PedidoEntidade pedido) {
        this.pedido = pedido;
    }

    public ProdutoEntidade getProduto() {
        return produto;
    }

    public void setProduto(ProdutoEntidade produto) {
        this.produto = produto;
    }

    

    
}