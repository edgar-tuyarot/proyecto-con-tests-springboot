package com.limpiezaIt.controller;


import com.limpiezaIt.entity.Categoria;
import com.limpiezaIt.service.impl.CategoriaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    Categoria guardarCategoria(@RequestBody Categoria categoria){
               return  categoriaServiceImpl.guardarCategoria(categoria);
    }

    //Actualizar categoría
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> actualizarCategoria(
            @PathVariable Long id,
            @RequestBody Categoria categoriaActualizada) {
        Categoria categoria = categoriaServiceImpl.actualizar(id, categoriaActualizada);
        if (categoria == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(categoria);
    }

    //Delete lógico
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desactivarCategoria(@PathVariable Long id) {
        boolean result = categoriaServiceImpl.desactivarCategoria(id);
        if (result) return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }



}
