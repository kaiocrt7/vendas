package com.udemy.api.vendas.domain.rest.controller;

import com.udemy.api.vendas.domain.entity.Produto;
import com.udemy.api.vendas.domain.repository.ProdutoRepository;

import org.springframework.data.domain.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Sort.Direction;
import static org.springframework.http.HttpStatus.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/produto")
public class ProdutoController {
    @Autowired
    private ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Produto salvarProduto(@RequestBody Produto produto){
        return produtoRepository.save(produto);
    }

    @PostMapping("/salvar-produtos")
    @ResponseStatus(CREATED)
    public List<Produto> salvarProdutos(@RequestBody List<Produto> produto){
        return produtoRepository.saveAll(produto);
    }

    @GetMapping("/buscar-produto/{id}")
    public Produto buscarProduto(@PathVariable Integer id){
        return produtoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Produto não encontrado."));
    }

    @GetMapping("/listar-produtos")
    @ResponseStatus(ACCEPTED)    
    public List<Produto> listarProdutos(){
        return produtoRepository.findAll(Sort.by(Direction.ASC, "descricao"));
    }

    @DeleteMapping("/deletar-produto/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deletarProduto(@PathVariable Integer id){
        produtoRepository.findById(id).map(
            produto -> {
                produtoRepository.delete(produto);
                return Void.TYPE;
            }
        ).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Produto não encontrado"));
    }

    @PutMapping("/atualizar-produto/{id}")
    @ResponseStatus(NO_CONTENT)
    public void atualizarProduto(@PathVariable Integer id, @RequestBody Produto produto) {
        produtoRepository.findById(id).map(
            produtoEncontrado -> {
                produto.setId(produtoEncontrado.getId());
                produtoRepository.save(produto);
                return produtoEncontrado;
            }
        ).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Produto não encontrado."));
    }

    @GetMapping("/buscar")
    public List<Produto> find(Produto filtro){
        ExampleMatcher example = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);

        return produtoRepository.findAll(Example.of(filtro, example));
    }
}
