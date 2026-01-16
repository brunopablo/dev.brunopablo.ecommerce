package dev.brunopablo.ecommerce.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_pedidos")
public class OrderEntity {

    @Id
    @Column(name="id_order")
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;
    
    @Column(name="total_order")
    private BigDecimal total;
    
    @Column(name="date_order")
    private LocalDateTime dateOrder;

    @ManyToOne
    @JoinColumn(name="id_user")
    private UserEntity user;

    @OneToMany(mappedBy = "id.order")
    private List<OrderItem> items;

    public OrderEntity() {
    }

    public OrderEntity(LocalDateTime dateOrder, Long id, List<OrderItem> items, BigDecimal total, UserEntity user) {
        this.dateOrder = dateOrder;
        this.id = id;
        this.items = items;
        this.total = total;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public LocalDateTime getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(LocalDateTime dateOrder) {
        this.dateOrder = dateOrder;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
}