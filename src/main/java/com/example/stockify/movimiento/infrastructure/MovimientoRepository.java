package com.example.stockify.movimiento.infrastructure;

import com.example.stockify.movimiento.domain.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
    List<Movimiento> findByFechaMovimientoBetween(LocalDateTime inicio, LocalDateTime fin);
}
