package com.example.stockify.recetaBase.domain;

import com.example.stockify.recetaDetalle.domain.RecetaDetalle;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Entity
@Data
@Table(name = "recetas_base")
public class RecetaBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_plato", nullable = false, length = 100)
    private String nombrePlato;

    @Column(name = "descripcion", nullable = false, length = 255)
    private String descripcion;

    @Column(name = "porciones_base", nullable = false)
    private Integer porcionesBase;

    @Column(name = "unidad_porcion", nullable = false, length = 50)
    private String unidadPorcion;

    @Column(name = "activo", nullable = false)
    private Boolean activo = true;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @OneToMany(mappedBy = "recetaBase", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RecetaDetalle> detalles = new ArrayList<>();
}