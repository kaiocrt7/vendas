package com.udemy.api.vendas.domain.rest.controller;

import com.udemy.api.vendas.domain.rest.dto.PedidoDTO;
import com.udemy.api.vendas.domain.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.HttpStatus.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/pedido")
public class PedidoController {
    @Autowired
    private PedidoService service;

    public PedidoController(PedidoService service){
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Integer salvarPedido(@RequestBody PedidoDTO pedidoDTO){
        return service.salvar(pedidoDTO).getId();
    }
}
