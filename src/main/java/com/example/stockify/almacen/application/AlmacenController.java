package com.example.stockify.almacen.application;

import com.example.stockify.almacen.domain.AlmacenService;
import com.example.stockify.almacen.dto.AlmacenNewDTO;
import com.example.stockify.almacen.dto.AlmacenRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/almacenes")
@CrossOrigin(origins = "*")
public class AlmacenController {

    private final AlmacenService almacenService;

    public AlmacenController(AlmacenService almacenService) {
        this.almacenService = almacenService;
    }

    @PostMapping
    public ResponseEntity<AlmacenRequestDTO> crear(@RequestBody AlmacenNewDTO dto) {
        return ResponseEntity.ok(almacenService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<AlmacenRequestDTO>> listar() {
        return ResponseEntity.ok(almacenService.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<AlmacenRequestDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(almacenService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlmacenRequestDTO> actualizar(@PathVariable Long id, @RequestBody AlmacenNewDTO dto) {
        return ResponseEntity.ok(almacenService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        almacenService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}