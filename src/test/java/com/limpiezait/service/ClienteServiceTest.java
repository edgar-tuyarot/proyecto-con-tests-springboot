package com.limpiezait.service;


import com.limpiezait.entity.Cliente;
import com.limpiezait.repository.ClienteRepository;
import com.limpiezait.service.impl.ClienteServiceImpl;
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
public class ClienteServiceTest {
    @Mock
    private ClienteRepository clienteRepository;
    @InjectMocks
    private ClienteServiceImpl clienteService;


    //Teste para el guardado del cliente.
    @Test
    void testGuradarCliente(){
        //Given
        Cliente nuevoCliente = Cliente.builder()
                .nombre("Cliente 1")
                .apellido("Apellido 1")
                .celular("3624555419")
                .email("e@mail.com")
                .telefono("0303456")
                .build();
        when(clienteRepository.save(any(Cliente.class))).thenReturn(nuevoCliente);

        //When
        Cliente resultadoCliente = clienteService.crearCliente(nuevoCliente);

        //Then
        assertNotNull(resultadoCliente);
        assertEquals("Cliente 1",nuevoCliente.getNombre());
        verify(clienteRepository).save(any(Cliente.class));

    }

    @Test
    void testObtenerTodos(){
        //Given
        Cliente nuevoCliente = Cliente.builder()
                .nombre("Cliente 1")
                .apellido("Apellido 1")
                .celular("3624555419")
                .email("e@mail.com")
                .telefono("0303456")
                .build();
        Cliente nuevoCliente2 = Cliente.builder()
                .nombre("Cliente 2")
                .apellido("Apellido 2")
                .celular("3624555419")
                .email("e@mail.com")
                .telefono("0303456")
                .build();
        when(clienteRepository.findAll()).thenReturn(Arrays.asList(nuevoCliente,nuevoCliente2));

        //When
        List<Cliente> clientes = clienteService.obtenerTodos();

        //Then
        assertEquals(2,clientes.size()); //deberia devolver 2 clientes
        verify(clienteRepository, times(1)).findAll();


    }

    @Test
    void testBuscarPorId() {
        //Given
        Long id = 1L;
        Cliente nuevoCliente = Cliente.builder()
                .nombre("Cliente 1")
                .apellido("Apellido 1")
                .celular("3624555419")
                .email("e@mail.com")
                .telefono("0303456")
                .build();
        when(clienteRepository.findById(id)).thenReturn(Optional.of(nuevoCliente));

        //When
        Optional<Cliente> resultado = clienteService.buscarPorId(id);

        //Then
        assertTrue(resultado.isPresent());
        assertEquals("Cliente 1", resultado.get().getNombre());
        verify(clienteRepository, times(1)).findById(id);
    }

}
