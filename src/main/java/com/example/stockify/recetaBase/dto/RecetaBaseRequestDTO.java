package com.example.stockify.recetaBase.dto;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

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
    private Boolean activo;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaCreacion;
}