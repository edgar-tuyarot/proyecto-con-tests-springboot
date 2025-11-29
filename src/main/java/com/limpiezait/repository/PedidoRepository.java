package com.limpiezait.repository;

import com.limpiezait.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    //Buscar por activo true
    List<Pedido> findByActivoTrue();

    //Query para buscar por id, activo true
    Optional<Pedido> findByActivoTrueAndId(Long id);


}
