package com.example.stockify.valorizacionPeriodo.application;
import com.example.stockify.valorizacionPeriodo.domain.ValorizacionPeriodoService;
import com.example.stockify.valorizacionPeriodo.dto.ValorizacionPeriodoNewDTO;
import com.example.stockify.valorizacionPeriodo.dto.ValorizacionPeriodoRequestDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/valorizaciones")
@CrossOrigin(origins = "*")
public class ValorizacionPeriodoController {
    private final ValorizacionPeriodoService valorizacionPeriodoService;

    public ValorizacionPeriodoController(ValorizacionPeriodoService valorizacionPeriodoService) {
        this.valorizacionPeriodoService = valorizacionPeriodoService;
    }

    @GetMapping
    public ResponseEntity<List<ValorizacionPeriodoRequestDTO>> listar() {
        return ResponseEntity.ok(valorizacionPeriodoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ValorizacionPeriodoRequestDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(valorizacionPeriodoService.findById(id));
    }

    @PostMapping("/ejecutar")
    public ResponseEntity<ValorizacionPeriodoRequestDTO> ejecutar(@Valid @RequestBody ValorizacionPeriodoNewDTO dto) {
        return ResponseEntity.ok(
                valorizacionPeriodoService.ejecutarValorizacion(dto.getPeriodo(), dto.getMetodoValorizacion(), dto.getUsuarioId())
        );
    }
}