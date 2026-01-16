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
public class PedidoEntidade {

    @Id
    @Column(name="id_pedido")
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;
    
    @Column(name="total_pedido")
    private BigDecimal total;
    
    @Column(name="data_pedido")
    private LocalDateTime dataPedido;

    @ManyToOne
    @JoinColumn(name="id_usuario")
    private UsuarioEntidade usuario;

    @OneToMany(mappedBy = "id.pedido")
    private List<ItemPedido> itens;

    public PedidoEntidade() {
    }

    public PedidoEntidade(Long id, BigDecimal total, LocalDateTime dataPedido, UsuarioEntidade usuario,
            List<ItemPedido> itens) {
        this.id = id;
        this.total = total;
        this.dataPedido = dataPedido;
        this.usuario = usuario;
        this.itens = itens;
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

    public LocalDateTime getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDateTime dataPedido) {
        this.dataPedido = dataPedido;
    }

    public UsuarioEntidade getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntidade usuario) {
        this.usuario = usuario;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }   
}