package com.productos.proyecto.service;

import com.productos.proyecto.model.Producto;
import com.productos.proyecto.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    // ✅ Guardar o actualizar producto
    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    // ✅ Obtener todos los productos
    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    // ✅ Buscar producto por ID
    public Optional<Producto> buscarPorId(Long id) {
        return productoRepository.findById(id);
    }

    // ✅ Buscar productos por nombre (exacto)
    public Optional<Producto> buscarPorNombre(String nombre) {
        return productoRepository.findByNombre(nombre);
    }

    // ✅ Buscar productos que contengan texto en nombre
    public List<Producto> buscarPorNombreConteniendo(String texto) {
        return productoRepository.findByNombreContainingIgnoreCase(texto);
    }

    // ✅ Buscar productos por rango de precios
    public List<Producto> buscarPorRangoPrecio(Double min, Double max) {
        return productoRepository.findByPrecioBetween(min, max);
    }

    // ✅ Buscar productos con stock disponible
    public List<Producto> buscarConStock() {
        return productoRepository.findByStockGreaterThan(0);
    }

    // ✅ Buscar en nombre O descripción (usando el método con @Query)
    public List<Producto> buscarEnNombreODescripcion(String texto) {
        return productoRepository.findByNombreContainingOrDescripcionContainingIgnoreCase(texto,texto);
    }

    // ✅ Eliminar producto por ID
    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }

    // ✅ Verificar si existe producto por ID
    public boolean existeProducto(Long id) {
        return productoRepository.existsById(id);
    }

    //Contar total de productos
    public long contarProductos() {
        return productoRepository.count();
    }

    //Desactivar producto
    public void activarDesactivar(long id, boolean estado){
        productoRepository.actualizarEstado(id, estado);
    }

    // Obtener productos por categoría (ID)
    public List<Producto> obtenerPorCategoria(Long categoriaId) {
        return productoRepository.findByCategoriaId(categoriaId);
    }



}
