package com.limpiezait.service.impl;

import com.limpiezait.entity.Cliente;
import com.limpiezait.error.ResourceNotFoundException;
import com.limpiezait.repository.ClienteRepository;
import com.limpiezait.service.interfaces.ClienteService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {


    private final ClienteRepository clienteRepository;

    //Buscar todos los clientes
    @Override
    public List<Cliente> obtenerTodos() {
        return clienteRepository.findByActivoTrue();
    }

    @Override
    @Transactional
    public Cliente crearCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    //Buscar por id y activo true
    @Override
    public Cliente buscarPorId(Long id) throws ResourceNotFoundException {
        return clienteRepository.findByActivoTrueAndId(id)
                .orElseThrow(()-> new ResourceNotFoundException("El cliente con el id "+id+" no existe"));
    }

    @Override
    @Transactional
    public Cliente actualizar(Long id, Cliente cliente){
        //verificamos que el Cliente este en DB y este activo si no esta se lanza la excepcion desde el "buscarPorId"
        Cliente clienteDB = buscarPorId(id);

        //Actualizamos campos.
        clienteDB.setNombre(cliente.getNombre());
        clienteDB.setApellido(cliente.getApellido());
        clienteDB.setEmail(cliente.getEmail());
        clienteDB.setTelefono(cliente.getTelefono());
        clienteDB.setCelular(cliente.getCelular());
        //Se guarda
        return clienteRepository.save(clienteDB);

    }

    @Override
    @Transactional
    public void desactivarCliente(Long id) {
        Cliente cliente = buscarPorId(id);
        cliente.setActivo(false);
        clienteRepository.save(cliente);
        return;

    }
}
