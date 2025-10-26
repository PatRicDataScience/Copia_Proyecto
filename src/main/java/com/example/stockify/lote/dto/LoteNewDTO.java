package com.example.stockify.lote.dto;

import lombok.*;
import jakarta.validation.constraints.NotBlank;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoteNewDTO {

    @NotBlank(message = "codigoLote es obligatorio")
    private String codigoLote;

    private Double costoUnitario;

    private Double costoTotal;

    private Double cantidadInicial;

    private Double cantidadDisponible;

}
