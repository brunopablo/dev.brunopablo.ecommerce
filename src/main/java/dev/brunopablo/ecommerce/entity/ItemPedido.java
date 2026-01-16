package dev.brunopablo.ecommerce.entity;

import java.math.BigDecimal;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name="tb_item_pedido")
public class ItemPedido {

    @EmbeddedId
    private ItemPedidoId id;

    private BigDecimal total;

    private Integer quantidade;

    public ItemPedido() {
    }

    public ItemPedido(ItemPedidoId id, BigDecimal total, Integer quantidade) {
        this.id = id;
        this.total = total;
        this.quantidade = quantidade;
    }

    public ItemPedidoId getId() {
        return id;
    }

    public void setId(ItemPedidoId id) {
        this.id = id;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}