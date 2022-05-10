package com.udemy.api.vendas.domain.resource;

import com.udemy.api.vendas.domain.repository.ClienteRepository;
import com.udemy.api.vendas.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteResource {
    @Autowired
    public ClienteRepository clienteRepository;

    @PostMapping
    public void salvarCliente (@RequestBody Cliente cliente){
        clienteRepository.save(cliente);
    }

    @GetMapping(value = "/salvar-clientes")
    public void salvarClientes (@RequestBody List<Cliente> clientes){
        clienteRepository.saveAll(clientes);
    }    
    
    @GetMapping(value="/listar-cliente/{id}")
    public ResponseEntity<Cliente> listarCliente (@PathVariable Integer id){
        return clienteRepository.findClienteFetchPedidos(id);
    }

    @GetMapping(value="/buscar-cliente/{id}")
    public Cliente buscarCliente (@PathVariable Integer id){
        return clienteRepository.findById(id).get();
    }

    @GetMapping(value="/listar-clientes")
    public ResponseEntity<List<Cliente>> listarTodosClientes (){
        return ResponseEntity.ok().body(clienteRepository.findAll(Sort.by(Sort.Direction.ASC, "nome")));
    }        
}
