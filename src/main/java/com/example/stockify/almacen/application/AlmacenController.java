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
    public ResponseEntity<?> listarTodos() {
        return ResponseEntity.ok(almacenService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlmacenRequestDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(almacenService.findById(id));
    }
    @GetMapping("/activos")
    public ResponseEntity<List<AlmacenRequestDTO>> listarActivos() {
        return ResponseEntity.ok(almacenService.listarActivos());
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<AlmacenRequestDTO>> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(almacenService.buscarPorNombre(nombre));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<AlmacenRequestDTO> actualizarEstado(
            @PathVariable Long id,
            @RequestParam Boolean activo) {
        AlmacenRequestDTO updated = almacenService.actualizarEstado(id, activo);
        return ResponseEntity.ok(updated);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AlmacenRequestDTO> actualizarParcial(
            @PathVariable Long id,
            @RequestBody AlmacenNewDTO dto) {
        AlmacenRequestDTO updated = almacenService.updatePartial(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        almacenService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}