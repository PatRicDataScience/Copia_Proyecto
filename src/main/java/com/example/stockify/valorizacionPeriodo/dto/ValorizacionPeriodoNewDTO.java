package com.example.stockify.valorizacionPeriodo.dto;

import com.example.stockify.valorizacionPeriodo.domain.MetodoValorizacion;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.*;
import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ValorizacionPeriodoNewDTO {
    @NotBlank(message = "El periodo es obligatorio (formato: YYYY-MM)")
    @Pattern(regexp = "\\d{4}-\\d{2}", message = "El periodo debe tener el formato YYYY-MM")
    private String periodo;

    @NotNull(message = "El método de valorización es obligatorio")
    private MetodoValorizacion metodoValorizacion;

    @NotNull(message = "El ID del usuario es obligatorio")
    @Positive(message = "El ID del usuario debe ser positivo")
    private Long usuarioId;
}
