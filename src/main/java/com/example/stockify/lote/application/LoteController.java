package com.example.stockify.lote.application;

import com.example.stockify.lote.domain.LoteService;
import com.example.stockify.lote.dto.LoteNewDTO;
import com.example.stockify.lote.dto.LoteRequestDTO;
import com.example.stockify.lote.domain.Lote;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lotes")
@CrossOrigin(origins = "*")
public class LoteController {


    private final LoteService loteService;

    public LoteController(LoteService loteService) {
        this.loteService = loteService;
    }

    @GetMapping
    public ResponseEntity<List<LoteRequestDTO>> listarTodos() {
        List<LoteRequestDTO> lotes = loteService.findAll();
        return ResponseEntity.ok(lotes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoteRequestDTO> obtenerPorId(
            @PathVariable @Positive(message = "El ID debe ser mayor que 0") Long id) {
        LoteRequestDTO lote = loteService.findById(id);
        return ResponseEntity.ok(lote);
    }

    @PostMapping
    public ResponseEntity<LoteRequestDTO> crear(@Valid @RequestBody LoteNewDTO dto) {
        LoteRequestDTO nuevoLote = loteService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoLote);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoteRequestDTO> actualizar(
            @PathVariable @Positive(message = "El ID debe ser mayor que 0") Long id,
            @Valid @RequestBody LoteRequestDTO dto) {
        LoteRequestDTO actualizado = loteService.update(id, dto);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable @Positive(message = "El ID debe ser mayor que 0") Long id) {
        loteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/disponibles/{productoId}")
    public ResponseEntity<List<Lote>> obtenerLotesDisponiblesFIFO(
            @PathVariable @Positive(message = "El ID del producto debe ser mayor que 0") Long productoId) {
        List<Lote> lotesDisponibles = loteService.obtenerLotesDisponiblesFIFO(productoId);
        return ResponseEntity.ok(lotesDisponibles);
    }
}