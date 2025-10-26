package com.example.stockify.movimiento.domain;

import com.example.stockify.almacen.domain.Almacen;
import com.example.stockify.lote.domain.Lote;
import com.example.stockify.producto.domain.Producto;
import com.example.stockify.usuario.domain.Usuario;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "movimientos")
public class Movimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_movimiento", nullable = false)
    private TipoMovimiento tipoMovimiento;

    @Column(name = "cantidad", nullable = false)
    private Double cantidad;

    @Column(name = "costo_unitario", nullable = false)
    private Double costoUnitario;

    @Column(name = "costo_total", nullable = false)
    private Double costoTotal;

    @Column(name = "fecha_movimiento", nullable = false)
    private LocalDateTime fechaMovimiento = LocalDateTime.now();

    @Column(name = "observacion", nullable = false, length = 255)
    private String observacion;

    @Column(name = "origen", nullable = false, length = 100)
    private String origen;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "lote_id")
    private Lote lote;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "almacen_id", nullable = false)
    private Almacen almacen;

}
