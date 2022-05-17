package com.udemy.api.vendas;

import java.math.BigDecimal;
import java.util.Arrays;

import com.udemy.api.vendas.domain.entity.Cliente;
import com.udemy.api.vendas.domain.entity.Produto;
import com.udemy.api.vendas.domain.rest.controller.ClienteController;
import com.udemy.api.vendas.domain.rest.controller.ProdutoController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VendasApplication implements CommandLineRunner {
	
	@Autowired
	private ClienteController clienteResource;

	@Autowired
	private ProdutoController produtoResource;
	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		Cliente  c1 = new Cliente("Teste 1", "042.771.491-54");		
		Cliente  c2 = new Cliente("Testando 2", "042.771.491-54");		
		Cliente  c3 = new Cliente("Absbsb 3", "042.771.491-54");		
		Cliente  c4 = new Cliente("Boodod d odkod 4", "042.771.491-54");		
		Cliente  c5 = new Cliente("Oppdpodo odo od 5", "042.771.491-54");		
		Cliente  c6 = new Cliente("Rririm dfmifm 6", "042.771.491-54");		
		
		clienteResource.salvarClientes(Arrays.asList(c1, c2, c3, c4, c5, c6));

		Produto pro1 = new Produto("TV Smart 59", BigDecimal.valueOf(20000.50));
		Produto pro2 = new Produto("TV Smart", BigDecimal.valueOf(2000.90));
		Produto pro3 = new Produto("TV AOC 59", BigDecimal.valueOf(2000.50));
		Produto pro4 = new Produto("Monitor 59", BigDecimal.valueOf(2000.99));
		Produto pro5 = new Produto("CPU 5Ghz", BigDecimal.valueOf(1990.00));
		Produto pro6 = new Produto("TV Samsung 99", BigDecimal.valueOf(9000.00));

		produtoResource.salvarProdutos(Arrays.asList(pro1, pro2, pro3, pro4, pro5, pro6));
	}
}
