package com.limpiezaIt.service.impl;


import com.limpiezaIt.entity.Producto;
import com.limpiezaIt.error.ResourceNotFoundException;
import com.limpiezaIt.repository.ProductoRepository;
import com.limpiezaIt.service.interfaces.ProductoService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductoServiceImp implements ProductoService {

    private final ProductoRepository productoRepository;

    //Buscar todos los productos
    @Override
    public List<Producto> obtenerTodos() {
        return productoRepository.findByActivoTrue();
    }

    //Guardar producto
    @Override
    public Producto guardarProducto(Producto producto) {
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
    public boolean desactivarProducto(Long id) {
        //verificamos que el producto este en DB o desde el find se lanza la exception
        Producto producto = buscarPorId(id);
            producto.setActivo(false);
            productoRepository.save(producto);
            return true;

    }


}
