package com.limpiezait.service;

import com.limpiezait.entity.Producto;
import com.limpiezait.repository.ProductoRepository;
import com.limpiezait.service.impl.ProductoServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
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
    private ProductoServiceImpl productoService;

    @Test
    void testGuardarProducto() {
        // Given
        Producto producto = Producto.builder()
                .nombre("Test Product")
                .precio(100.0)
                .stock(10)
                .activo(true)  // ← Empieza como activo
                .fechaCreacion(LocalDateTime.now())
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
        Producto producto = Producto.builder()
                .nombre("Test Product")
                .precio(100.0)
                .stock(10)
                .activo(true)  // ← Empieza como activo
                .fechaCreacion(LocalDateTime.now())
                .build();
        Producto producto2 = Producto.builder()
                .nombre("Test Product2")
                .precio(100.0)
                .stock(10)
                .activo(true)  // ← Empieza como activo
                .fechaCreacion(LocalDateTime.now())
                .build();

        when(productoRepository.findAll()).thenReturn(Arrays.asList(producto, producto2));

        // When
        List<Producto> resultados = productoService.obtenerTodos();

        // Then
        assertEquals(2, resultados.size());
        verify(productoRepository, times(1)).findAll();
    }

    @Test
    void testBuscarPorId_Existente() {
        // Given
        Producto producto = Producto.builder()
                .nombre("Test")
                .precio(100.0)
                .stock(10)
                .activo(true)  // ← Empieza como activo
                .fechaCreacion(LocalDateTime.now())
                .build();
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