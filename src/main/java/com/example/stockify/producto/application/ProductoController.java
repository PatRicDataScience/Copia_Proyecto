package com.example.stockify.producto.application;

import com.example.stockify.producto.domain.ProductoService;
import com.example.stockify.producto.dto.ProductoNewDTO;
import com.example.stockify.producto.dto.ProductoRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping
    public ResponseEntity<ProductoRequestDTO> crear(@RequestBody ProductoNewDTO dto) {
        return ResponseEntity.ok(productoService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<ProductoRequestDTO>> listar() {
        return ResponseEntity.ok(productoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoRequestDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoRequestDTO> actualizar(@PathVariable Long id, @RequestBody ProductoNewDTO dto) {
        return ResponseEntity.ok(productoService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}