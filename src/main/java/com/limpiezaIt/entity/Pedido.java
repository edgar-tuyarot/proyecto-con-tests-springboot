package com.limpiezaIt.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedidos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pedido {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private long id;

    @Column
    @PositiveOrZero(message = "El total no puede ser negativo")
    @Builder.Default
    private Double total = 0.0;

    //Definimos el estado activo
    @Column
    @Builder.Default
    private boolean activo = true;

    //Definimos que la relacion sera un pedido tiene un estado
    @ManyToOne
    @JoinColumn(name = "estado_pedido_id")
    private EstadoPedido estadoPedido;

    //Definimos que la relacion sera un pedido pertenece a un cliente
    @NotNull(message = "El cliente es obligatorio")
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    //Definimos la relacion uno a muchos con ProductoPedido
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    @Builder.Default
    private List<ProductoPedido> productos = new ArrayList<>();


}
