package com.udemy.api.vendas.domain.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
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
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Pedido> pedido;    

    public Cliente(String nome, String cpf){
        this.nome = nome;
        this.cpf = cpf;
    }
    // Acessar os pedidos do cliente
    public List<Pedido> getPedido(){
        return pedido;
    }
}
