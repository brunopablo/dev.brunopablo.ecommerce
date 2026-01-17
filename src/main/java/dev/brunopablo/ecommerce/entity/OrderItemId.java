package dev.brunopablo.ecommerce.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class OrderItemId {

    @ManyToOne
    @JoinColumn(name = "id_order")
    private OrderEntity order;
    
    @ManyToOne
    @JoinColumn(name = "id_product")
    private ProductEntity product;

    public OrderItemId() {
    }

    public OrderItemId(OrderEntity order, ProductEntity product) {
        this.order = order;
        this.product = product;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }    
}