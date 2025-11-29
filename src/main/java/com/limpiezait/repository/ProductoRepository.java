package com.limpiezait.repository;

import com.limpiezait.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

   // findBy[NombreCampo][Operador]Or[NombreCampo][Operador](Param1, Param2)
   //              ↑                    ↑
   //           Usa Param1          Usa Param2

   // Regla: findBy + [NombreCampo] + [Condicional] + [Operador]


    // Buscar por nombre que contenga texto (like)
    List<Producto> findByNombreContainingIgnoreCase(String nombre);

    // Buscar productos activos
    List<Producto> findByActivoTrue();

    //Buscamos por producto activo y id
    Optional<Producto> findByActivoTrueAndId(Long id);








}