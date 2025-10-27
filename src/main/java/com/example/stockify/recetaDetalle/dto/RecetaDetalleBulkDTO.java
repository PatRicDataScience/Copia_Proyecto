package com.example.stockify.recetaDetalle.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecetaDetalleBulkDTO {
    private List<RecetaDetalleNewDTO> ingredientes;

}
