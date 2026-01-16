package dev.brunopablo.ecommerce.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_billing_address")
public class BillingAddressEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;

    @Column(name="address")
    private String address;

    @Column(name="number")
    private String number;
    
    @Column(name="complement")
    private String complement;


    public BillingAddressEntity() {
    }

    public BillingAddressEntity(String address, String complement, Long id, String number) {
        this.address = address;
        this.complement = complement;
        this.id = id;
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }
}