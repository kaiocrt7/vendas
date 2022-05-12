package com.udemy.api.vendas;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

import com.udemy.api.vendas.domain.entity.Cliente;
import com.udemy.api.vendas.domain.entity.Pedido;
import com.udemy.api.vendas.domain.resource.ClienteResource;
import com.udemy.api.vendas.domain.resource.PedidoResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VendasApplication implements CommandLineRunner {
	
	@Autowired
	private ClienteResource clienteResource;

	@Autowired
	private PedidoResource pedidoResource;
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

		Pedido p1 = new Pedido(c1, LocalDate.now(), BigDecimal.valueOf(200.50));
		Pedido p2 = new Pedido(c1, LocalDate.now(), BigDecimal.valueOf(200.90));
		Pedido p3 = new Pedido(c2, LocalDate.now(), BigDecimal.valueOf(200.50));
		Pedido p4 = new Pedido(c3, LocalDate.now(), BigDecimal.valueOf(200.99));
		Pedido p5 = new Pedido(c4, LocalDate.now(), BigDecimal.valueOf(1.99));
		Pedido p6 = new Pedido(c5, LocalDate.now(), BigDecimal.valueOf(900.00));

		pedidoResource.salvarPedidos(Arrays.asList(p1, p2, p3, p4, p5, p6));  
	}
}
