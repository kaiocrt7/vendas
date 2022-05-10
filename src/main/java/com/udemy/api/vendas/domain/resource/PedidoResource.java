package com.udemy.api.vendas.domain.resource;

import java.util.List;

import com.udemy.api.vendas.domain.entity.Pedido;
import com.udemy.api.vendas.domain.repository.PedidoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/pedido")
public class PedidoResource {
    @Autowired
    private PedidoRepository pedidoRepository;

    @PostMapping
    public void salvarPedido(@RequestBody Pedido pedido){
        pedidoRepository.save(pedido);
    }

    @PostMapping (value = "/salvar-pedidos")
    public void salvarPedidos(@RequestBody List<Pedido> pedido){
        pedidoRepository.saveAll(pedido);
    }

    @GetMapping(value = "/listar-pedidos")
    public ResponseEntity<List<Pedido>> listar(){
        return ResponseEntity.ok().body(pedidoRepository.findAll());
    }
}
