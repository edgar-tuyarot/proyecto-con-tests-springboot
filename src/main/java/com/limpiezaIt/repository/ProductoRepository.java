package com.limpiezaIt.repository;

import com.limpiezaIt.entity.Producto;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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


    // Metodo para desactivar categoria no se usa ya que se utiliza un set para cambiar de activo a false
    @Transactional
    @Modifying
    @Query("UPDATE Producto p SET p.activo = false WHERE p.id = :id")
    void desactivarProducto(@Param("id") Long id);





}