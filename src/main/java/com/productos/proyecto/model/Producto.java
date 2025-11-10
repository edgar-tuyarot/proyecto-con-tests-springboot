package com.productos.proyecto.model;

import jakarta.persistence.*;
import lombok.*;

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

}
