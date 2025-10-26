package com.example.stockify.movimiento.dto;

import com.example.stockify.movimiento.domain.TipoMovimiento;
import jakarta.validation.constraints.Positive;
import lombok.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovimientoNewDTO {

    @NotNull(message = "El tipoMovimiento es obligatorio")
    private TipoMovimiento tipoMovimiento;

    @NotNull(message = "La cantidad es obligatoria")
    @Positive(message = "La cantidad debe ser mayor que 0")
    private Double cantidad;

    @NotNull(message = "El costoUnitario es obligatorio para entradas")
    @Positive(message = "El costoUnitario debe ser mayor que 0")
    private Double costoUnitario;

    @NotBlank(message = "La observación es obligatoria")
    private String observacion;

    @NotBlank(message = "El origen es obligatorio")
    private String origen;

    @NotNull(message = "El productoId es obligatorio")
    private Long productoId;

    @NotNull(message = "El almacenId es obligatorio")
    private Long almacenId;
}