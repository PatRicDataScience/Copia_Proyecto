package com.example.stockify.recetaDetalle.infrastructure;

import com.example.stockify.recetaDetalle.domain.RecetaDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecetaDetalleRepository extends JpaRepository<RecetaDetalle, Long> {
    List<RecetaDetalle> findByRecetaBase_Id(Long recetaBaseId);
}