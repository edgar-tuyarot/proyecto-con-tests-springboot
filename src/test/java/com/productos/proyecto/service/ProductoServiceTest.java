package com.productos.proyecto.service;

import com.productos.proyecto.model.Producto;
import com.productos.proyecto.repository.ProductoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    @Test
    void testGuardarProducto() {
        // Given
        Producto producto = Producto.builder()
                .nombre("Test Product")
                .precio(100.0)
                .stock(10)
                .build();

        when(productoRepository.save(any(Producto.class))).thenReturn(producto);

        // When
        Producto resultado = productoService.guardarProducto(producto);

        // Then
        assertNotNull(resultado);
        assertEquals("Test Product", resultado.getNombre());
        verify(productoRepository, times(1)).save(producto);
    }

    @Test
    void testObtenerTodos() {
        // Given
        Producto p1 = new Producto(1L, "Producto 1", "Desc 1", 100.0, 10, "SKU1");
        Producto p2 = new Producto(2L, "Producto 2", "Desc 2", 200.0, 20, "SKU2");

        when(productoRepository.findAll()).thenReturn(Arrays.asList(p1, p2));

        // When
        List<Producto> resultados = productoService.obtenerTodos();

        // Then
        assertEquals(2, resultados.size());
        verify(productoRepository, times(1)).findAll();
    }

    @Test
    void testBuscarPorId_Existente() {
        // Given
        Producto producto = new Producto(1L, "Test", "Desc", 100.0, 10, "SKU");
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        // When
        Optional<Producto> resultado = productoService.buscarPorId(1L);

        // Then
        assertTrue(resultado.isPresent());
        assertEquals("Test", resultado.get().getNombre());
    }

    @Test
    void testBuscarPorId_NoExistente() {
        // Given
        when(productoRepository.findById(999L)).thenReturn(Optional.empty());

        // When
        Optional<Producto> resultado = productoService.buscarPorId(999L);

        // Then
        assertFalse(resultado.isPresent());
    }
}