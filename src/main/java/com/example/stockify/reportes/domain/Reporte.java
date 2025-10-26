package com.example.stockify.reportes.domain;

import com.example.stockify.usuario.domain.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "reporte")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String periodo;

    @Column(nullable = false)
    private LocalDateTime fechaGeneracion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FormatoReporte formato;

    private String nombreArchivo;

    // Observaciones o notas adicionales
    private String observaciones;

    // Usuario que generó el reporte
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
}