package com.limpiezait.controller;

import com.limpiezait.entity.Producto;
import com.limpiezait.service.interfaces.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    //Obtener todos los productos
    @GetMapping
    public ResponseEntity<List<Producto>> obtenerTodos() {
        List<Producto> productos = productoService.obtenerTodos();
        if (productos.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(productos);
    }

    //Obtener producto por ID
    @GetMapping("/{id}")
    Producto obtenerPorId(@PathVariable Long id) {
        return productoService.buscarPorId(id);
    }

    //Crear nuevo producto
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    Producto crearProducto(@RequestBody @Valid Producto producto) {
        return productoService.guardarProducto(producto);
    }

    //Actualizar producto existente
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    Producto actualizarProducto(
            @PathVariable Long id,
            @RequestBody @Valid Producto productoActualizado) {
        return productoService.actualizar(id,productoActualizado);
    }

    //Eliminar producto
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productoService.desactivarProducto(id);
        return ResponseEntity.noContent().build();
    }


}