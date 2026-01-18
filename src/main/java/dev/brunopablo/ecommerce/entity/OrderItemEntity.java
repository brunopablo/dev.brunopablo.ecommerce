package dev.brunopablo.ecommerce.entity;

import java.math.BigDecimal;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_order_item")
public class OrderItemEntity {

    @EmbeddedId
    private OrderItemId id;

    private BigDecimal total;

    private Integer quantity;

    public OrderItemEntity() {
    }

    public OrderItemEntity(OrderItemId id, Integer quantity, BigDecimal total) {
        this.id = id;
        this.quantity = quantity;
        this.total = total;
    }

    public OrderItemId getId() {
        return id;
    }

    public void setId(OrderItemId id) {
        this.id = id;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}