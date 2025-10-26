package com.example.stockify.producto.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoRequestDTO {

    private Long id;
    private String nombre;
    private String descripcion;
    private String unidadMedida;
    private String categoria;
    private Integer stockMinimo;
    private Integer stockActual;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaCreacion;
}
