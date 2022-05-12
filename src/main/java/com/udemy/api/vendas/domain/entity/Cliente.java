package com.udemy.api.vendas.domain.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "cliente")
public class Cliente implements Serializable{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nome; 
    private String cpf;

    //Um cliente tem muitos pedidos (trazer todos os pedidos do cliente)
    //fetch = FetchType.LAZY - Trazer os clientes sem pedidos
    //@JsonManagedReference
    @JsonIgnore
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private List<Pedido> pedido;    

    public Cliente(String nome, String cpf){
        this.nome = nome;
        this.cpf = cpf;
    }
    public Cliente(Integer id, String nome, String cpf){
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
    }
    // Acessar os pedidos do cliente
    public List<Pedido> getPedido(){
        return pedido;
    }
}
