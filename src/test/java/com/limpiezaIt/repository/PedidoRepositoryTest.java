package com.limpiezaIt.repository;

import com.limpiezaIt.entity.EstadoPedido;
import com.limpiezaIt.entity.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class PedidoRepositoryTest {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
   // private EstadoPedidoRepository estadoPedidoRepository; //Sin EstadoPeridoRepository no se puede crear Pedido

    //Teste para guardar pedido
    void testGuardarPedido() {
        // Given - Configuración inicial
        Pedido pedido = Pedido.builder()
                .total(100.00)

                // Completar con los atributos necesarios
                .build();

        // When - Ejecutar la acción


        // Then - Verificar el resultado

    }


}
