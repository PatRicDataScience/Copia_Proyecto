package com.example.stockify.recetaDetalle.application;

import com.example.stockify.recetaDetalle.domain.RecetaDetalleService;
import com.example.stockify.recetaDetalle.dto.RecetaDetalleNewDTO;
import com.example.stockify.recetaDetalle.dto.RecetaDetalleRequestDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recetas-detalle")
@CrossOrigin(origins = "*")
public class RecetaDetalleController {
    private final RecetaDetalleService recetaDetalleService;

    public RecetaDetalleController(RecetaDetalleService recetaDetalleService) {
        this.recetaDetalleService = recetaDetalleService;
    }

    @PostMapping
    public ResponseEntity<RecetaDetalleRequestDTO> crear(@Valid @RequestBody RecetaDetalleNewDTO dto) {
        return ResponseEntity.ok(recetaDetalleService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<RecetaDetalleRequestDTO>> listar() {
        return ResponseEntity.ok(recetaDetalleService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecetaDetalleRequestDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(recetaDetalleService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        recetaDetalleService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}