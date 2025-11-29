package com.limpiezait.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
        @NotBlank(message = "El nombre es obligatorio")
        @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
        private String nombre;

        @Column(length = 500)
        @Size(min = 2, max = 500, message = "La descripcion debe tener entre 2 y 500 caracteres")
        @NotBlank(message = "La descripcion es obligatoria")
        private String descripcion;


        @Column(nullable = false)
        @NotNull
        @PositiveOrZero(message = "El precio no puede ser negativo")
        private BigDecimal precio;

        @Column(nullable = false)
        @PositiveOrZero(message = "El stock no puede ser negativo")
        @NotNull
        @Builder.Default
        private Integer stock = 0;

        @Column(unique = true)
        @NotBlank(message = "El SKU es obligatorio")
        private String sku;

        @Column(nullable = false)
        @Builder.Default
        private Boolean activo = true;

        @Column
        @CreationTimestamp
        private LocalDateTime fechaCreacion;

        // Decimos que la relacion sera, un producto puede tener una categoria.
        @ManyToOne
        @JoinColumn(name = "categoria_id")
        @NotNull(message = "La categoria es obligatoria")
        private Categoria categoria;

        // Definimos la relacion uno a muchos con ProductoPedido
        @OneToMany(mappedBy = "producto")
        @JsonIgnore
        @Builder.Default
        private List<ProductoPedido> pedidos = new ArrayList<>();

}
