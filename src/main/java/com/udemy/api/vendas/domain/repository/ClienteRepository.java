package com.udemy.api.vendas.domain.repository;

import com.udemy.api.vendas.domain.entity.Pedido;
import com.udemy.api.vendas.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{ 

    @Query(value = "SELECT * FROM cliente c WHERE c.nome LIKE :nome", nativeQuery = true)
    List<Cliente> consultaNome(@Param("nome") String nome);

    @Query(value = "SELECT * FROM cliente c WHERE c.nome LIKE :nome", nativeQuery = true)
    List<Cliente> consultaSQLNativo(@Param("nome") String nome);

    @Query(value = "SELECT * FROM pedido p WHERE p.cliente = :cliente_id", nativeQuery = true)
    List<Pedido> consultaPedidos(@Param("cliente_id") String cliente_id);

    // Trazer o cliente pelo id, juntamente com seus pedidos ou não
    @Query(value = "SELECT * FROM cliente c LEFT JOIN FETCH pedido p WHERE c.id = :cliente_id", nativeQuery = true )
    ResponseEntity<Cliente> findClienteFetchPedidos(@Param("cliente_id") Integer cliente_id);

    @Modifying
    @Query(value = "DELETE FROM cliente c WHERE c.nome = :nome", nativeQuery = true)
    void deleteByNome(String nome);

    List<Cliente> findByNomeLike(String nome);
    // Buscar por nome ou id e ordenar
    List<Cliente> findByNomeOrIdOrderById(String nome, Integer id);
    // Buscar por nome
    Cliente findOneById(Integer nome);
    // verificar se existe o nome
    boolean existsByNome(String nome);
}
