package com.example.stockify.recetaBase.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecetaBaseRequestDTO {

    private Long id;
    private String nombrePlato;
    private String descripcion;
    private Integer porcionesBase;
    private String unidadPorcion;
}