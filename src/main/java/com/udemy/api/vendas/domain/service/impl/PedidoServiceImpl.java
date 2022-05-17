package com.udemy.api.vendas.domain.service.impl;

import java.time.LocalDate;

import com.udemy.api.vendas.domain.entity.Cliente;
import com.udemy.api.vendas.domain.entity.ItemPedido;
import com.udemy.api.vendas.domain.entity.Pedido;
import com.udemy.api.vendas.domain.entity.Produto;
import com.udemy.api.vendas.domain.exception.RegraNegocioException;
import com.udemy.api.vendas.domain.repository.ClienteRepository;
import com.udemy.api.vendas.domain.repository.ItemPedidoRepository;
import com.udemy.api.vendas.domain.repository.PedidoRepository;
import com.udemy.api.vendas.domain.repository.ProdutoRepository;
import com.udemy.api.vendas.domain.rest.dto.ItemPedidoDTO;
import com.udemy.api.vendas.domain.rest.dto.PedidoDTO;
import com.udemy.api.vendas.domain.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // contrutores obrigatorios
public class PedidoServiceImpl implements PedidoService{
    @Autowired
    private final PedidoRepository pedidoRepository;
    @Autowired
    private final ClienteRepository clienteRepository;
    @Autowired
    private final ProdutoRepository produtoRepository;
    @Autowired
    private final ItemPedidoRepository itemPedidoRepository;
    
    @Override
    @Transactional
    public Pedido salvar(PedidoDTO pedidoDTO){
        Integer cliente_id = pedidoDTO.getCliente_id();
        Cliente cliente = clienteRepository.findById(cliente_id).orElseThrow(() -> new RegraNegocioException("Código do cliente inválido."));
        
        Pedido pedido = new Pedido();
        pedido.setTotal(pedido.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);

        List<ItemPedido> itens = converterItens(pedido, pedidoDTO.getItens());
        pedidoRepository.save(pedido);
        itemPedidoRepository.saveAll(itens);
        pedido.setItens(itens);

        return pedido;
    }

    private List<ItemPedido> converterItens(Pedido pedido, List<ItemPedidoDTO> itens){
        if(itens.isEmpty()){
            throw new RegraNegocioException("Não é possível realizar um pedido sem itens.");
        }
        return itens.stream()
        .map(dto -> {
            Integer produto_id = dto.getProduto_id();
            Produto produto = produtoRepository.findById(produto_id).orElseThrow(() -> new RegraNegocioException("Codigo de produto inválido." + produto_id));

            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setQuantidade(dto.getQuantidade());
            itemPedido.setPedido(pedido);
            itemPedido.setProduto(produto);

            return itemPedido;
        }).collect(Collectors.toList());
    }
}
