package com.example.stockify.almacen.domain;

import com.example.stockify.lote.domain.Lote;
import com.example.stockify.movimiento.domain.Movimiento;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Data
@Table(name = "almacenes")
public class Almacen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del almacén es obligatorio")
    @Column(nullable = false, length = 100)
    private String nombre;

    @NotBlank(message = "La ubicación es obligatoria")
    @Column(nullable = false, length = 200)
    private String ubicacion;

    @NotBlank(message = "El responsable es obligatorio")
    @Pattern(
            regexp = "^[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+$",
            message = "El nombre del responsable solo puede contener letras y espacios"
    )
    @Column(nullable = false, length = 100)
    private String responsable;

    @DecimalMin(value = "0.1", message = "La capacidad máxima debe ser mayor a cero")
    @Column(name = "capacidad_maxima", nullable = false)
    private Double capacidadMaxima;

    @Column(nullable = false)
    private Boolean activo = true;

    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Column(name = "fecha_actualizacion", nullable = false)
    private LocalDateTime ultimoActualizado = LocalDateTime.now();

    @JsonIgnore
    @OneToMany(mappedBy = "almacen", fetch = FetchType.LAZY)
    private List<Lote> lotes;

    @JsonIgnore
    @OneToMany(mappedBy = "almacen", fetch = FetchType.LAZY)
    private List<Movimiento> movimientos;

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