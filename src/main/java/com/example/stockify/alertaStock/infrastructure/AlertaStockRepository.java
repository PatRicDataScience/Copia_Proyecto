package com.example.stockify.alertaStock.infrastructure;

import com.example.stockify.alertaStock.domain.AlertaStock;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AlertaStockRepository extends JpaRepository<AlertaStock, Long> {
    List<AlertaStock> findByAtendidoFalse();
    List<AlertaStock> findByProducto_Id(Long productoId);
}