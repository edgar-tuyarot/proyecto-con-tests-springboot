package com.limpiezait.repository;

import com.limpiezait.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaRepository  extends JpaRepository<Categoria, Long> {

    //Construimos la query con activo y true
    List<Categoria> findByActivoTrue();

    //Buscar por id y activo true en optional
    Optional<Categoria> findByActivoTrueAndId(Long id);






}
