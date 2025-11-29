package com.limpiezait.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDto {
    private long id;
    private Double total;
    private boolean activo;
    private Long estadoPedidoId;
    private Long clienteId;
}
