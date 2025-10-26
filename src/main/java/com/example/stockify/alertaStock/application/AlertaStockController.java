package com.example.stockify.alertaStock.application;

import com.example.stockify.alertaStock.domain.AlertaStockService;
import com.example.stockify.alertaStock.dto.AlertaStockNewDTO;
import com.example.stockify.alertaStock.dto.AlertaStockRequestDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alertas")
@CrossOrigin(origins = "*")
public class AlertaStockController {

    private final AlertaStockService alertaStockService;

    public AlertaStockController(AlertaStockService alertaStockService) {
        this.alertaStockService = alertaStockService;
    }

    @GetMapping
    public ResponseEntity<List<AlertaStockRequestDTO>> listarTodas() {
        return ResponseEntity.ok(alertaStockService.findAll());
    }

    @GetMapping("/pendientes")
    public ResponseEntity<List<AlertaStockRequestDTO>> listarPendientes() {
        return ResponseEntity.ok(alertaStockService.findPendientes());
    }

    @PostMapping
    public ResponseEntity<AlertaStockRequestDTO> crear(@Valid @RequestBody AlertaStockNewDTO dto) {
        return ResponseEntity.ok(alertaStockService.crear(dto));
    }

    @PutMapping("/{id}/atender")
    public ResponseEntity<AlertaStockRequestDTO> marcarComoAtendida(@PathVariable Long id) {
        return ResponseEntity.ok(alertaStockService.marcarComoAtendida(id));
    }


}