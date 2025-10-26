package com.example.stockify.producto.application;

import com.example.stockify.producto.domain.ProductoService;
import com.example.stockify.producto.dto.ProductoNewDTO;
import com.example.stockify.producto.dto.ProductoRequestDTO;
import jakarta.validation.Valid;
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<ProductoRequestDTO> actualizarProductoCompleto(
            @PathVariable Long id,
            @Valid @RequestBody ProductoNewDTO dto) {
        ProductoRequestDTO updated = productoService.updateFull(id, dto);
        return ResponseEntity.ok(updated);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductoRequestDTO> actualizarProductoParcial(
            @PathVariable Long id,
            @RequestBody ProductoNewDTO dto) {
        ProductoRequestDTO updated = productoService.updatePartial(id, dto);
        return ResponseEntity.ok(updated);
    }


    @GetMapping("/filtrar")
    public ResponseEntity<List<ProductoRequestDTO>> filtrarProductos(
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) Boolean activo) {
        List<ProductoRequestDTO> productos = productoService.filtrar(categoria, activo);
        return ResponseEntity.ok(productos);
    }


    @GetMapping("/activo")
    public ResponseEntity<List<ProductoRequestDTO>> listarPorActivo(
            @RequestParam Boolean activo) {
        List<ProductoRequestDTO> productos = productoService.listarPorActivo(activo);
        return ResponseEntity.ok(productos);
    }
}