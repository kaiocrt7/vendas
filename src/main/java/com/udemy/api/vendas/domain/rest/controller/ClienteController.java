package com.udemy.api.vendas.domain.rest.controller;

import com.udemy.api.vendas.domain.repository.ClienteRepository;
import com.udemy.api.vendas.domain.entity.Cliente;
import org.springframework.data.domain.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import static org.springframework.http.HttpStatus.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    public ClienteRepository clienteRepository;

    @PostMapping
    @ResponseStatus(CREATED)
    public Cliente salvarCliente (@RequestBody Cliente cliente){
        return clienteRepository.save(cliente);        
    }

    @PostMapping("/salvar-clientes")
    @ResponseStatus(CREATED)
    public List<Cliente> salvarClientes (@RequestBody List<Cliente> clientes){
        return clienteRepository.saveAll(clientes);
    }    
    
    @GetMapping("/listar-cliente/{id}")
    public ResponseEntity<Cliente> listarCliente (@PathVariable Integer id){
        return clienteRepository.findClienteFetchPedidos(id);
    }

    @GetMapping("/buscar-cliente/{id}")
    public Cliente buscarCliente (@PathVariable Integer id){ 
        return clienteRepository.findById(id)
        .orElseThrow(()-> new ResponseStatusException(NOT_FOUND, "Cliente não encontrado."));
    }

    @GetMapping("/listar-clientes")
    public ResponseEntity<List<Cliente>> listarTodosClientes (){
        return ResponseEntity.ok().body(clienteRepository.findAll(Sort.by(Sort.Direction.ASC, "nome")));
    }     
    
    @DeleteMapping("/deletar-cliente/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deletarCliente(@PathVariable Integer id){
        clienteRepository
        .findById(id)
        .map(cliente -> {clienteRepository.delete(cliente);
            return Void.TYPE;
        })
        .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Cliente não encontrado"));
    }

    @PutMapping("/atualizar-cliente/{id}")
    @ResponseStatus(NO_CONTENT)
    public void atualizarCliente(@PathVariable Integer id, @RequestBody Cliente cliente){
        clienteRepository.findById(id)
        .map(clienteExistente -> {
            cliente.setId(clienteExistente.getId());
            clienteRepository.save(cliente);
            return clienteExistente;
        }).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Cliente não encontrado para atualização."));
    }

    @GetMapping("/buscar")
    public List<Cliente> find(Cliente filtro){
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);        
        
        return clienteRepository.findAll(Example.of(filtro, matcher));
    }
}
