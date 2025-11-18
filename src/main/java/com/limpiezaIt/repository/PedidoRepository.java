package com.limpiezaIt.repository;

import com.limpiezaIt.entity.Pedido;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    //Buscar por activo true
    List<Pedido> findByActivoTrue();

    //Query para buscar por id, activo true
    Optional<Pedido> findByActivoTrueAndId(Long id);

    //Metodo para desactivar producto
    @Transactional
    @Modifying
    @Query("UPDATE Pedidos p SET p.activo = false WHERE p.id = :id")
    void desactivarPedido(@Param("id") Long id);


}
