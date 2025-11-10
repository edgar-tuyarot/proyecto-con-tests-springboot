package com.productos.proyecto.controller;

import com.productos.proyecto.model.Producto;
import com.productos.proyecto.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // ✅ GET - Obtener todos los productos
    @GetMapping
    public ResponseEntity<List<Producto>> obtenerTodos() {
        List<Producto> productos = productoService.obtenerTodos();
        return ResponseEntity.ok(productos);
    }

    // ✅ GET - Obtener producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerPorId(@PathVariable Long id) {
        Optional<Producto> producto = productoService.buscarPorId(id);

        return producto.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ POST - Crear nuevo producto
    @PostMapping
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto) {
        try {
            Producto nuevoProducto = productoService.guardarProducto(producto);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // ✅ PUT - Actualizar producto existente
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(
            @PathVariable Long id,
            @RequestBody Producto productoActualizado) {

        if (!productoService.existeProducto(id)) {
            return ResponseEntity.notFound().build();
        }

        productoActualizado.setId(id);
        Producto producto = productoService.guardarProducto(productoActualizado);
        return ResponseEntity.ok(producto);
    }

    // ✅ DELETE - Eliminar producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        if (!productoService.existeProducto(id)) {
            return ResponseEntity.notFound().build();
        }

        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ GET - Buscar productos por nombre
    @GetMapping("/buscar")
    public ResponseEntity<List<Producto>> buscarPorNombre(
            @RequestParam String nombre) {

        List<Producto> productos = productoService.buscarPorNombreConteniendo(nombre);
        return ResponseEntity.ok(productos);
    }

    // ✅ GET - Buscar productos por rango de precios
    @GetMapping("/rango-precio")
    public ResponseEntity<List<Producto>> buscarPorRangoPrecio(
            @RequestParam Double min,
            @RequestParam Double max) {

        List<Producto> productos = productoService.buscarPorRangoPrecio(min, max);
        return ResponseEntity.ok(productos);
    }

    // ✅ GET - Buscar productos con stock disponible
    @GetMapping("/con-stock")
    public ResponseEntity<List<Producto>> buscarConStock() {
        List<Producto> productos = productoService.buscarConStock();
        return ResponseEntity.ok(productos);
    }

    // ✅ GET - Buscar en nombre o descripción
    @GetMapping("/buscar-texto")
    public ResponseEntity<List<Producto>> buscarEnNombreODescripcion(
            @RequestParam String texto) {

        List<Producto> productos = productoService.buscarEnNombreODescripcion(texto);
        return ResponseEntity.ok(productos);
    }
}