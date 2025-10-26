package com.example.stockify.producto.domain;

import com.example.stockify.alertaStock.domain.AlertaStock;
import com.example.stockify.lote.domain.Lote;
import com.example.stockify.movimiento.domain.Movimiento;
import com.example.stockify.recetaDetalle.domain.RecetaDetalle;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "unidad_medida")
    private String unidadMedida;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "stock_minimo")
    private Double stockMinimo = 0.0;

    @Column(name = "stock_actual")
    private Double stockActual = 0.0;

    @Column(name = "activo")
    private Boolean activo;

    @OneToMany(mappedBy = "producto")
    private List<Lote> lotes;

    @OneToMany(mappedBy = "producto")
    private List<Movimiento> movimientos; // 1:n

    @OneToMany(mappedBy = "producto")
    private List<AlertaStock> alertas; // 1:n

    @OneToMany(mappedBy = "producto")
    private List<RecetaDetalle> detallesReceta;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Column(name = "ultimo_actualizado")
    private LocalDateTime ultimoActualizado = LocalDateTime.now();

    @PrePersist
    public void prePersist() {
        fechaCreacion = LocalDateTime.now();
        ultimoActualizado = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        ultimoActualizado = LocalDateTime.now();
    }
}

