package com.example.stockify.valorizacionPeriodo.domain;

import com.example.stockify.usuario.domain.Usuario;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "valorizacion_pedidos")
public class ValorizacionPeriodo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 7)
    private String periodo; // formato: YYYY-MM

    @Enumerated(EnumType.STRING)
    @Column(name = "metodo_valorizacion", nullable = false, length = 20)
    private MetodoValorizacion metodoValorizacion;

    @Column(name = "valor_inventario", nullable = false)
    private Double valorInventario;

    @Column(name = "costo_ventas", nullable = false)
    private Double costoVentas;

    @Column(name = "observaciones", length = 255)
    private String observaciones;

    @Column(name = "fecha_valorizacion", nullable = false)
    private LocalDateTime fechaValorizacion = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
}
