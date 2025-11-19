package com.limpiezaIt.repository;

import com.limpiezaIt.entity.Cliente;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {



    List<Cliente> findByActivoTrue();


    Optional<Cliente> findByActivoTrueAndId(Long id);

    // Metodo para desactivar categoria no se usa ya que se utiliza un set para cambiar de activo a false
    @Transactional
    @Modifying
    @Query("UPDATE Cliente c SET c.activo = false WHERE c.id = :id")
    void desactivarCliente(@Param("id") Long id);
}
