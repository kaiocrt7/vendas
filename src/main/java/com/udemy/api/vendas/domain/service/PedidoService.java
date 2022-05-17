package com.udemy.api.vendas.domain.service;

import com.udemy.api.vendas.domain.entity.Pedido;
import com.udemy.api.vendas.domain.rest.dto.PedidoDTO;

public interface PedidoService {
    Pedido salvar(PedidoDTO pedidoDTO);
}
