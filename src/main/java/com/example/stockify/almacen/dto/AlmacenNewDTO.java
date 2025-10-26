package com.example.stockify.almacen.dto;

import jakarta.validation.constraints.Positive;
import lombok.*;
import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlmacenNewDTO {

    private Long id;

    @NotBlank(message = "El nombre del almacén es obligatorio")
    private String nombre;

    @NotBlank(message = "La ubicación es obligatoria")
    private String ubicacion;

    @NotBlank(message = "El responsable es obligatorio")
    private String responsable;

    @Positive(message = "La capacidad máxima debe ser positiva")
    private Double capacidadMaxima;

    private Boolean activo;
}