package com.limpiezaIt.repository;

import com.limpiezaIt.entity.Categoria;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaRepository  extends JpaRepository<Categoria, Long> {

    //Construimos la query con activo y true
    List<Categoria> findByActivoTrue();

    //Buscar por id y activo true en optional
    Optional<Categoria> findByActivoTrueAndId(Long id);


    // Metodo para desactivar categoria
    @Transactional
    @Modifying
    @Query("UPDATE Categoria c SET c.activo = false WHERE c.id = :id")
    void desactivarCategoria(@Param("id") Long id);



}
