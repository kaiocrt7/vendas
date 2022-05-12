package com.udemy.api.vendas.domain.resource;

import com.udemy.api.vendas.domain.repository.ClienteRepository;
import com.udemy.api.vendas.domain.entity.Cliente;
import org.springframework.data.domain.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public ResponseEntity<Cliente> salvarCliente (@RequestBody Cliente cliente){
        return ResponseEntity.ok(clienteRepository.save(cliente));        
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
    public ResponseEntity<?> buscarCliente (@PathVariable Integer id){ 
        if(clienteRepository.findById(id).isPresent()){
            return ResponseEntity.ok(clienteRepository.findById(id).get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value="/listar-clientes")
    public ResponseEntity<List<Cliente>> listarTodosClientes (){
        return ResponseEntity.ok().body(clienteRepository.findAll(Sort.by(Sort.Direction.ASC, "nome")));
    }     
    // noContent() não irá retornar nada no corpo da requisição, somente codigo de status ok
    @DeleteMapping(value = "/deletar-cliente/{id}")
    public ResponseEntity<?> deletarCliente(@PathVariable Integer id){
        if (clienteRepository.findById(id).isPresent()){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(value = "/atualizar-cliente/{id}")
    public ResponseEntity<?> atualizarCliente(@PathVariable Integer id, @RequestBody Cliente cliente){
        return clienteRepository.findById(id).map(
            clienteExistente -> {
                cliente.setId(clienteExistente.getId());
                clienteRepository.save(cliente);
                return ResponseEntity.noContent().build();
            }
        ).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/buscar")
    public ResponseEntity<?> find(Cliente filtro){
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);
        Example example = Example.of(filtro, matcher);
        List<Cliente> lista = clienteRepository.findAll(example);
        return ResponseEntity.ok(lista);
    }
}
