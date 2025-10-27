package com.example.stockify.movimiento.infrastructure;

import com.example.stockify.movimiento.domain.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
}
