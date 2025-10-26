package com.example.stockify.almacen.infrastructure;

import com.example.stockify.almacen.domain.Almacen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlmacenRepository extends JpaRepository<Almacen, Long> {
}
