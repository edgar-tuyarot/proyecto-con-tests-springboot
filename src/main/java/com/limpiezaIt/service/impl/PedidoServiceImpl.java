package com.limpiezaIt.service.impl;

import com.limpiezaIt.entity.Cliente;
import com.limpiezaIt.entity.EstadoPedido;
import com.limpiezaIt.entity.Pedido;
import com.limpiezaIt.error.ResourceNotFoundException;
import com.limpiezaIt.repository.ClienteRepository;
import com.limpiezaIt.repository.EstadoPedidoRepository;
import com.limpiezaIt.repository.PedidoRepository;
import com.limpiezaIt.service.interfaces.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final EstadoPedidoRepository estadoPedidoRepository;
    private final ClienteRepository clienteRepository;




    //Actualizar Estado de pedido
    @Override
    public Pedido actualizarEstado(Long id, Long idEstado){
        //Buscamos el pedido por id
        Pedido pedido = pedidoRepository.findByActivoTrueAndId(id)
                .orElseThrow(()-> new ResourceNotFoundException("El pedido con el id "+ id+" no existe"));
        //Buscamos si el id del estado existe
        EstadoPedido ePedido =  estadoPedidoRepository.findById(idEstado)
                .orElseThrow(()->new ResourceNotFoundException("El estado id "+idEstado+" no existe"));
        //Si llegamos a este punto, debemos gestionar el cambio de estado
        pedido.setEstadoPedido(ePedido);
        //Retornamos el pedido actualizado
        return pedidoRepository.save(pedido);

    }


    @Override
    public List<Pedido> obtenerTodos() {
        return pedidoRepository.findByActivoTrue();
    }


    //Buscar Pedido por id
    @Override
    public Pedido buscarPorId(Long id) throws ResourceNotFoundException {
            //Devolvemos el "Pedido" o la exception
            return pedidoRepository.findByActivoTrueAndId(id)
                    .orElseThrow(()->new  ResourceNotFoundException("El pedido con el id "+id+" no existe"));
    }

    @Override
    public Pedido crearPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }


    @Override
    public Pedido actualizarPedido(Long id, Pedido pedido)   {
        //LLamamos al "Pedido" o lanza exception desde el metodo de busqueda
        Pedido pedidoDB = buscarPorId(id);
        pedidoDB.setCliente(pedido.getCliente());
        pedidoDB.setProductos(pedido.getProductos());
        pedidoDB.setTotal(pedido.getTotal());
        pedidoRepository.save(pedidoDB);
        return pedidoDB;
    }

    @Override
    public boolean eliminarPedido(Long id){
        Pedido pedido = buscarPorId(id);
        pedido.setActivo(false);
        pedidoRepository.save(pedido);
        return true;
    }


}


