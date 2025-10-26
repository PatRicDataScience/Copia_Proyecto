package com.example.stockify.usuario.application;
import com.example.stockify.usuario.domain.UsuarioServiceCRUD;
import com.example.stockify.usuario.dto.UsuarioNewDTO;
import com.example.stockify.usuario.dto.UsuarioRequestDTO;
import com.example.stockify.usuario.dto.UsuarioResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final UsuarioServiceCRUD usuarioService;

    public UsuarioController(UsuarioServiceCRUD usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> crear(@Valid @RequestBody UsuarioNewDTO dto) {
        return ResponseEntity.ok(usuarioService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioRequestDTO>> listar() {
        return ResponseEntity.ok(usuarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioRequestDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioRequestDTO> actualizarUsuarioCompleto(
            @PathVariable Long id,
            @Valid @RequestBody UsuarioNewDTO dto) {
        UsuarioRequestDTO updated = usuarioService.updateFull(id, dto);
        return ResponseEntity.ok(updated);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UsuarioRequestDTO> actualizarUsuarioParcial(
            @PathVariable Long id,
            @RequestBody UsuarioNewDTO dto) {
        UsuarioRequestDTO updated = usuarioService.updatePartial(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        usuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
