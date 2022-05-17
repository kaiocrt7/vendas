package com.udemy.api.vendas.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "pedido")
public class Pedido implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate dataPedido;
    @Column(name = "total")
    private BigDecimal total;

    // Muitos pedidos para um cliente
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    @JsonBackReference
    private Cliente cliente;

    // Um pedido pode ter muitos itens || Muitos itens pode estar em um pedido 
    // mappedBy - usado por não ter nenhuma chave da tabela ITEM_PEDIDO na tabela PEDIDO, representa a variavel da entidade Pedido em ItemPedido
    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens;

    public Pedido(Cliente cliente, LocalDate dataPedido, BigDecimal total){
        this.cliente = cliente;
        this.dataPedido = dataPedido;
        this.total = total;
    }

    public Pedido(Integer id, Cliente cliente, LocalDate dataPedido, BigDecimal total){
        this.id = id;
        this.cliente = cliente;
        this.dataPedido = dataPedido;
        this.total = total;
    }
}
