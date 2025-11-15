package com.limpiezaIt.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Producto {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(nullable = false, length = 100)
        private String nombre;
        @Column(length = 500)
        private String descripcion;
        @Column(nullable = false)
        private Double precio;
        @Column(nullable = false)
        private Integer stock;
        @Column(unique = true)
        private String sku;
        @Column(nullable = false)
        private Boolean activo = true;
        @Column
        private LocalDateTime fechaCreacion = LocalDateTime.now();
        // Decimos que la relacion sera, un producto puede tener una categoria.
        @ManyToOne
        @JoinColumn(name = "categoria_id")
        private Categoria categoria;

        // Definimos la relacion uno a muchos con ProductoPedido
        @OneToMany(mappedBy = "producto")
        private List<ProductoPedido> pedidos;

}
