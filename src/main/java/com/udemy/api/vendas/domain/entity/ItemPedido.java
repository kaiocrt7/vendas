package com.udemy.api.vendas.domain.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "item_pedido")
public class ItemPedido implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    //Muitos itens pode estar em um pedido || Um pedido pode ter muitos itens
    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    //Muitos itens pode ter um produto || Um produto pode estar em apenas um item
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;
    private Integer quantidade;
}
