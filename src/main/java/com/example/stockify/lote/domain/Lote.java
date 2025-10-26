package com.example.stockify.lote.domain;

import com.example.stockify.almacen.domain.Almacen;
import com.example.stockify.movimiento.domain.Movimiento;
import com.example.stockify.producto.domain.Producto;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "lotes")
public class Lote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_lote", nullable = false, unique = true)
    private String codigoLote;

    @Column(name = "fecha_compra")
    private LocalDateTime fechaCompra = LocalDateTime.now();

    @Column(name = "costo_unitario")
    private Double costoUnitario;

    @Column(name = "costo_total")
    private Double costoTotal;

    @Column(name = "cantidad_inicial")
    private Double cantidadInicial = 0.0;

    @Column(name = "cantidad_disponible")
    private Double cantidadDisponible = 0.0;

    @Column(name = "fecha_vencimiento")
    private ZonedDateTime fechaVencimiento;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "almacen_id")
    private Almacen almacen; // n:1

    @OneToMany(mappedBy = "lote")
    private List<Movimiento> movimientos; // 1:n

    public void reducirCantidadDisponible(Double cantidad) {
        if (cantidadDisponible < cantidad) {
            throw new IllegalArgumentException("No hay suficiente cantidad disponible en el lote.");
        }
        cantidadDisponible -= cantidad;
    }

    @PrePersist
    public void generarCodigoLote() {
        if (codigoLote == null || codigoLote.isBlank()) {
            codigoLote = "L" + System.currentTimeMillis();
        }
    }
}
