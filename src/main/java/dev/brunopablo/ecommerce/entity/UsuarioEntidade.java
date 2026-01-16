package dev.brunopablo.ecommerce.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_usuarios")
public class UsuarioEntidade {

    @Id
    @Column(name="id_usario")
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;

    @Column(name="nome")
    private String nome;

    @OneToOne
    @JoinColumn(name="id_endereco_cobranca")
    private EnderecoCobranca enderecoCobranca;

    public UsuarioEntidade() {
    }

    public UsuarioEntidade(Long id, String nome, EnderecoCobranca enderecoCobranca) {
        this.id = id;
        this.nome = nome;
        this.enderecoCobranca = enderecoCobranca;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public EnderecoCobranca getEnderecoCobranca() {
        return enderecoCobranca;
    }

    public void setEnderecoCobranca(EnderecoCobranca enderecoCobranca) {
        this.enderecoCobranca = enderecoCobranca;
    }

    
}
