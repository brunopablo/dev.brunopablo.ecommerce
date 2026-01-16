package dev.brunopablo.ecommerce.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_endereco_cobranca")
public class EnderecoCobranca {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;

    @Column(name="rua")
    private String rua;

    @Column(name="numero")
    private String numero;
    
    @Column(name="complemento")
    private String complemento;

    @OneToOne(mappedBy="enderecoCobranca")
    private UsuarioEntidade usuario;

    public EnderecoCobranca() {
    }

    public EnderecoCobranca(Long id, String rua, String numero, String complemento, UsuarioEntidade usuario) {
        this.id = id;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public UsuarioEntidade getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntidade usuario) {
        this.usuario = usuario;
    }

    
}