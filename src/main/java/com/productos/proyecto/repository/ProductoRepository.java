package com.productos.proyecto.repository;

import com.productos.proyecto.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

   // findBy[NombreCampo][Operador]Or[NombreCampo][Operador](Param1, Param2)
   //              ↑                    ↑
    //          Usa Param1          Usa Param2

    // Regla: findBy + [NombreCampo] + [Condicional] + [Operador]

    // Buscar por nombre (exacto)
    Optional<Producto> findByNombre(String nombre);

    // Buscar por nombre que contenga texto (like)
    List<Producto> findByNombreContainingIgnoreCase(String nombre);

    // Busca el mismo texto en nombre O descripción
    List<Producto> findByNombreContainingOrDescripcionContainingIgnoreCase(String nombre, String descripcion);

    // Buscar productos con precio mayor a...
    List<Producto> findByPrecioGreaterThan(Double precio);

    // Buscar productos con stock disponible
    List<Producto> findByStockGreaterThan(Integer stock);

    // Buscar por SKU
    Optional<Producto> findBySku(String sku);

    // Buscar productos por rango de precios
    List<Producto> findByPrecioBetween(Double precioMin, Double precioMax);
}