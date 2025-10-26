package com.example.stockify.valorizacionPeriodo.infrastructure;

import com.example.stockify.valorizacionPeriodo.domain.ValorizacionPeriodo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ValorizacionPeriodoRepository extends JpaRepository<ValorizacionPeriodo, Integer> {
}
