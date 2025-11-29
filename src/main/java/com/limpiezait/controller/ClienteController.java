package com.limpiezait.controller;

import com.limpiezait.entity.Cliente;
import com.limpiezait.service.interfaces.ClienteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@Tag(name = "Clientes", description = "Endpoints para la gestión de clientes")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    //Obtener todos los clientes
    @GetMapping()
    public ResponseEntity<List<Cliente>> obtenerTodos(){
        List<Cliente> clientes = clienteService.obtenerTodos();
        if(clientes.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(clientes);
    }

    //Buscar cliente por id y estado activo
    @GetMapping("/{id}")
    Cliente buscarPorId(@PathVariable Long id){
       return clienteService.buscarPorId(id);
    }

    //Crear cliente
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    Cliente crearCliente(@RequestBody @Valid Cliente cliente){
            return clienteService.crearCliente(cliente);
    }

    //Actualizar cliente
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    Cliente actualizarCliente(@PathVariable Long id, @RequestBody @Valid Cliente cliente){
            return clienteService.actualizar(id,cliente);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Void> desactivarCliente(@PathVariable Long id){
        clienteService.desactivarCliente(id);
        return ResponseEntity.noContent().build();
    }


}
