package com.udemy.api.vendas.domain.repository;

import com.udemy.api.vendas.domain.entity.ItemPedido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer>{
    
}
