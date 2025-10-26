package com.example.stockify.recetaBase.application;

import com.example.stockify.recetaBase.domain.RecetaBaseService;
import com.example.stockify.recetaBase.dto.RecetaBaseNewDTO;
import com.example.stockify.recetaBase.dto.RecetaBaseRequestDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recetas-base")
@CrossOrigin(origins = "*")
public class RecetaBaseController {

    private final RecetaBaseService recetaBaseService;

    public RecetaBaseController(RecetaBaseService recetaBaseService) {
        this.recetaBaseService = recetaBaseService;
    }

    @PostMapping
    public ResponseEntity<RecetaBaseRequestDTO> crear(@Valid @RequestBody RecetaBaseNewDTO dto) {
        return ResponseEntity.ok(recetaBaseService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<RecetaBaseRequestDTO>> listar() {
        return ResponseEntity.ok(recetaBaseService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecetaBaseRequestDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(recetaBaseService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecetaBaseRequestDTO> actualizar(@PathVariable Long id, @Valid @RequestBody RecetaBaseNewDTO dto) {
        return ResponseEntity.ok(recetaBaseService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        recetaBaseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}