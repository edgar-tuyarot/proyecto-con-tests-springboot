package com.limpiezait.service.impl;


import com.limpiezait.entity.Categoria;
import com.limpiezait.entity.Producto;
import com.limpiezait.error.ResourceNotFoundException;
import com.limpiezait.repository.ProductoRepository;
import com.limpiezait.service.interfaces.CategoriaService;
import com.limpiezait.service.interfaces.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaService categoriaService;

    //Buscar todos los productos
    @Override
    public List<Producto> obtenerTodos() {
        return productoRepository.findByActivoTrue();
    }

    //Guardar producto
    @Override
    public Producto guardarProducto(Producto producto) {
        Categoria categoria = categoriaService.buscarPorId(producto.getCategoria().getId());
        return productoRepository.save(producto);
    }

    //Buscar por id y activo true
    @Override
    public Producto buscarPorId(Long id) throws ResourceNotFoundException {
         return productoRepository.findByActivoTrueAndId(id)
                 .orElseThrow(()->new ResourceNotFoundException("Producto con id "+id+" no encontrado"));
    }

    //Actualizar producto
    @Override
    public Producto actualizar(Long id, Producto producto) {
        //verificamos que el producto este en DB o desde el find se lanza la exception
        Producto productoDB = buscarPorId(id);

        productoDB.setNombre(producto.getNombre());
        productoDB.setPrecio(producto.getPrecio());
        productoDB.setStock(producto.getStock());
        productoDB.setSku(producto.getSku());
         return productoRepository.save(productoDB);

    }


    @Override
    public void desactivarProducto(Long id) {
        //verificamos que el producto este en DB o desde el find se lanza la exception
        Producto producto = buscarPorId(id);
            producto.setActivo(false);
            productoRepository.save(producto);
            return;

    }


}
