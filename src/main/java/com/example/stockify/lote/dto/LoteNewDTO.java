package com.example.stockify.lote.dto;
import com.example.stockify.almacen.domain.Almacen;
import com.example.stockify.lote.domain.Estado;
import com.example.stockify.producto.domain.Producto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoteNewDTO {
    private String codigoLote;
    private Double costoUnitario;
    private Double costoTotal;
    private Double cantidadInicial;
    private Double cantidadDisponible;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaCompra;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaVencimiento;

    private Producto producto;
    private Almacen almacen;
    private Estado estado;
}
