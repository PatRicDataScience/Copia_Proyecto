package com.example.stockify.recetaBase.dto;

import lombok.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecetaBaseNewDTO {

    @NotBlank(message = "El nombre del plato es obligatorio")
    private String nombrePlato;

    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;

    @NotNull(message = "Las porciones base son obligatorias")
    @Positive(message = "Las porciones base deben ser mayores a 0")
    private Integer porcionesBase;

    @NotBlank(message = "La unidad de porción es obligatoria")
    private String unidadPorcion;
}