package com.example.stockify.movimiento.application;

import com.example.stockify.movimiento.domain.MovimientoService;
import com.example.stockify.movimiento.dto.MovimientoNewDTO;
import com.example.stockify.movimiento.dto.MovimientoRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimientos")
@CrossOrigin(origins = "*")
public class MovimientoController {

    private final MovimientoService movimientoService;

    public MovimientoController(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    @PostMapping("/entrada")
    public ResponseEntity<MovimientoRequestDTO> registrarEntrada(@RequestBody MovimientoNewDTO dto) {
        MovimientoRequestDTO movimiento = movimientoService.registrarEntrada(dto);
        return ResponseEntity.ok(movimiento);
    }

    @PostMapping("/salida-manual")
    public ResponseEntity<MovimientoRequestDTO> registrarSalidaManual(@RequestBody MovimientoNewDTO dto) {
        MovimientoRequestDTO movimiento = movimientoService.registrarSalidaManual(dto);
        return ResponseEntity.ok(movimiento);
    }

    @PostMapping("/salida-receta/{recetaBaseId}")
    public ResponseEntity<List<MovimientoRequestDTO>> registrarSalidaPorReceta(
            @PathVariable Long recetaBaseId,
            @RequestParam int porciones) {

        List<MovimientoRequestDTO> movimientos = movimientoService.registrarSalidaPorReceta(recetaBaseId, porciones);
        return ResponseEntity.ok(movimientos);
    }

    @GetMapping
    public ResponseEntity<List<MovimientoRequestDTO>> listarTodos() {
        return ResponseEntity.ok(movimientoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimientoRequestDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(movimientoService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        movimientoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}