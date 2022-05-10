package com.udemy.api.vendas.domain.repository;

import java.util.List;

import com.udemy.api.vendas.domain.entity.Cliente;
import com.udemy.api.vendas.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer>{
    ResponseEntity<List<Pedido>> findByCliente(Cliente cliente);
}
