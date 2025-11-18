package com.limpiezaIt.controller;

import com.limpiezaIt.entity.Cliente;
import com.limpiezaIt.service.interfaces.ClienteService;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.when;

@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ClienteService clienteService;

    //Implementar prueba para obtener todos los clientes|
    @Test
    void testObtenerTodos() throws Exception {
        //Given
        Cliente cliente1 = Cliente.builder()
                .nombre("Cliente 1")
                .apellido("Apellido 1")
                .celular("3624555419")
                .email("e@mail.com")
                .telefono("0303456")
                .build();

        Cliente cliente2 = Cliente.builder()
                .nombre("Cliente 2")
                .apellido("Apellido 2")
                .celular("3624555420")
                .email("e@mail2.com")
                .telefono("0303457")
                .build();
        when(clienteService.obtenerTodos()).thenReturn(Arrays.asList(cliente1, cliente2));

        //When & Then
        mockMvc.perform(get("/api/clientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].nombre").value("Cliente 1"))
                .andExpect(jsonPath("$[1].nombre").value("Cliente 2"));


    }

    //Implementar prueba para obtener cliente por id y activo
    @Test
    void testObtenerPorId_Existente_Activo() throws Exception {
        //Given
        Cliente cliente = Cliente.builder()
                .nombre("Cliente 1")
                .apellido("Apellido 1")
                .celular("3624555419")
                .email("e@mail.com")
                .telefono("0303456")
                .activo(true)
                .build();
        when(clienteService.buscarPorId(1L)).thenReturn(java.util.Optional.of(cliente));

        //When & Then
        mockMvc.perform(get("/api/clientes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Cliente 1"))
                .andExpect(jsonPath("$.apellido").value("Apellido 1"))
                .andExpect(jsonPath("$.activo").value(true));


    }

    //Implementar prueba para guardar un nuevo cliente
    @Test
    void testCrearCliente() throws Exception {
        //Given
        Cliente cliente = Cliente.builder()
                .nombre("Cliente 1")
                .apellido("Apellido 1")
                .celular("3624555419")
                .email("e@mail.com")
                .telefono("0303456")
                .activo(true)
                .build();
        when(clienteService.crearCliente(any(Cliente.class))).thenReturn(cliente);
        //When & Then
        mockMvc.perform(post("/api/clientes")
                        .contentType("application/json")
                        .content("""
                                    {
                                      "nombre": "Cliente 1",
                                      "apellido": "Apellido 1",
                                      "celular": "3624555419",
                                      "email": "e@mail",
                                      "telefono": "0303456",
                                      "activo": true
                                    }
                                    """))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(jsonPath("$.nombre").value("Cliente 1"))
                .andExpect(jsonPath("$.apellido").value("Apellido 1"))
                .andExpect(jsonPath("$.activo").value(true));


    }
}