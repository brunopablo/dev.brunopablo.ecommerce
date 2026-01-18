package dev.brunopablo.ecommerce.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_users")
public class UserEntity {

    @Id
    @Column(name="id_usario")
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;

    @Column(name="name")
    private String name;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="id_billing_address")
    private BillingAddressEntity billing_address;

    public UserEntity() {
    }

    public UserEntity(Long id, String name, BillingAddressEntity billing_address) {
        this.id = id;
        this.name = name;
        this.billing_address = billing_address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BillingAddressEntity getBilling_address() {
        return billing_address;
    }

    public void setBilling_address(BillingAddressEntity billing_address) {
        this.billing_address = billing_address;
    }   
}