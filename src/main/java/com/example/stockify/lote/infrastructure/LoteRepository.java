package com.example.stockify.lote.infrastructure;

import com.example.stockify.lote.domain.Lote;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface LoteRepository extends JpaRepository<Lote, Long> {
    List<Lote> findByProductoIdOrderByFechaCompraAsc(Long productoId);
    Optional<Lote> findTopByProductoIdAndCantidadDisponibleGreaterThanOrderByFechaCompraAsc(Long productoId, double cantidad);
}
