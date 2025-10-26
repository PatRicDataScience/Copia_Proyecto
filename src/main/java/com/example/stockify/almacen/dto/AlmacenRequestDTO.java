package com.example.stockify.almacen.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlmacenRequestDTO {
    private Long id;
    private String nombre;
    private String ubicacion;
    private String responsable;
    private Double capacidadMaxima;
    private Boolean activo;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaCreacion;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime ultimoActualizado;
}