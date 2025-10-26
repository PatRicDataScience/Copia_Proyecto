package com.example.stockify.recetaBase.infrastructure;

import com.example.stockify.recetaBase.domain.RecetaBase;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RecetaBaseRepository extends JpaRepository<RecetaBase, Long> {
    List<RecetaBase> findByActivo(boolean activo);
}
