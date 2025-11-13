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

    @GetMapping()
    public ResponseEntity<List<Cliente>> obtenerTodos(){
        List<Cliente> clientes = clienteService.obtenerTodos();
        if(clientes.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id){
        Optional<Cliente> cliente = clienteService.buscarPorId(id);

        if (cliente.isPresent()) return ResponseEntity.ok(cliente.get());

        return ResponseEntity.notFound().build();

    }

    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente){
        try {
            Cliente nuevoCliente =  clienteService.crearCliente(cliente);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCliente);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente){
            Cliente clienteActualizado = clienteService.actualizar(id,cliente);
        if(clienteActualizado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(clienteActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desactivarCliente(@PathVariable Long id){
        boolean resultado = clienteService.desactivarCliente(id);
        if (resultado) return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }


}
