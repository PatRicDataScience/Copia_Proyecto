package com.example.stockify.recetaDetalle.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecetaDetalleRequestDTO {
    private Long id;
    private Double cantidadNecesaria;
    private String unidadMedida;
    private Long productoId;
    private Long recetaBaseId;
}
