package com.example.stockify.almacen.domain;

import com.example.stockify.lote.domain.Lote;
import com.example.stockify.movimiento.domain.Movimiento;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "almacenes")
public class Almacen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "ubicacion", nullable = false, length = 150)
    private String ubicacion;

    @Column(name = "responsable", nullable = false, length = 100)
    private String responsable;

    @Column(name = "capacidad_maxima")
    private Double capacidadMaxima;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @OneToMany(mappedBy = "almacen")
    private List<Lote> lotes;

    @OneToMany(mappedBy = "almacen")
    private List<Movimiento> movimientos;
}