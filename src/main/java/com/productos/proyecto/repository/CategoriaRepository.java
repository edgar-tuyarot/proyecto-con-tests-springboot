package com.productos.proyecto.repository;

import com.productos.proyecto.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaRepository  extends JpaRepository<Categoria, Long> {


    //Buscar por nombre que contenga texto
    List<Categoria> findByNombreContainingIgnoreCase(String nombre);

    //Verificar si existe por nombre
    boolean existsByNombre(String nombre);

    //Buscar categorías con productos activos
    @Query("SELECT DISTINCT c FROM Categoria c JOIN c.productos p WHERE p.activo = true")
    List<Categoria> findCategoriasConProductosActivos();

    //Contar productos por categoría
    @Query("SELECT c.nombre, COUNT(p) FROM Categoria c LEFT JOIN c.productos p GROUP BY c.id")
    List<Object[]> contarProductosPorCategoria();

    //Buscar categorías sin productos
    @Query("SELECT c FROM Categoria c WHERE c.productos IS EMPTY")
    List<Categoria> findCategoriasSinProductos();




}
