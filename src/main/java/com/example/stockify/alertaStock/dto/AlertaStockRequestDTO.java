package com.example.stockify.alertaStock.dto;

import com.example.stockify.alertaStock.domain.Prioridad;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlertaStockRequestDTO {

    private Long id;

    private String mensaje;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaAlerta;

    private Boolean atendido;

    private Prioridad prioridad;

    private Long productoId;

    private String productoNombre;
}
