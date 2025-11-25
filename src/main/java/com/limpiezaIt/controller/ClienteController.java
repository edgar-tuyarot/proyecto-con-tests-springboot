package com.limpiezaIt.controller;

import com.limpiezaIt.entity.Cliente;
import com.limpiezaIt.service.interfaces.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
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
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente){
        try {
            Cliente nuevoCliente =  clienteService.crearCliente(cliente);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCliente);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }

    //Actualizar cliente
    @PutMapping("/{id}")
    Cliente actualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente){
            return clienteService.actualizar(id,cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desactivarCliente(@PathVariable Long id){
        boolean resultado = clienteService.desactivarCliente(id);
        if (resultado) return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }


}
