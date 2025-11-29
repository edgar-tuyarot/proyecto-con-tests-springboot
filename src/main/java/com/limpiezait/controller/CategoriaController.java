package com.limpiezait.controller;


import com.limpiezait.entity.Categoria;
import com.limpiezait.service.impl.CategoriaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    CategoriaServiceImpl categoriaServiceImpl;

    //Obtener todas las categorías
    @GetMapping
    public ResponseEntity<List<Categoria>> obtenerTodas(){
        List<Categoria> categorias = categoriaServiceImpl.obtenerTodas();

        return ResponseEntity.ok(categorias);
    }

    //Buscar por ID
    @GetMapping("/{id}")
    Categoria obtenerPorId (@PathVariable long id){
        return categoriaServiceImpl.buscarPorId(id);

    }

    //Crear nueva categoría
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    Categoria guardarCategoria(@RequestBody Categoria categoria){
               return  categoriaServiceImpl.guardarCategoria(categoria);
    }

    //Actualizar categoría
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Categoria> actualizarCategoria(
            @PathVariable Long id,
            @RequestBody Categoria categoriaActualizada) {
        Categoria categoria = categoriaServiceImpl.actualizar(id, categoriaActualizada);
        if (categoria == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(categoria);
    }

    //Delete lógico
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Void> desactivarCategoria(@PathVariable Long id) {
        categoriaServiceImpl.desactivarCategoria(id);
        return ResponseEntity.noContent().build();
    }



}
