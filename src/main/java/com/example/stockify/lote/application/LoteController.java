package com.example.stockify.lote.application;

import com.example.stockify.excepciones.BadRequestException;
import com.example.stockify.excepciones.ErrorResponseDTO;
import com.example.stockify.excepciones.ResourceNotFoundException;
import com.example.stockify.lote.domain.LoteService;
import com.example.stockify.lote.dto.LoteNewDTO;
import com.example.stockify.lote.dto.LoteRequestDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
    public ResponseEntity<?> findAll() {
        try {
            List<LoteRequestDTO> lotes = loteService.findAll();
            return ResponseEntity.ok(lotes);
        } catch (Exception e) {
            return buildErrorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, "/lotes");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            LoteRequestDTO lote = loteService.findByIdPlain(id);
            return ResponseEntity.ok(lote);
        } catch (ResourceNotFoundException e) {
            return buildErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND, "/lotes/" + id);
        }
    }

    @GetMapping("/disponibles/{productoId}")
    public ResponseEntity<?> obtenerLotesDisponiblesFIFO(@PathVariable Long productoId) {
        try {
            List<LoteRequestDTO> lotes = loteService.obtenerLotesDisponiblesFIFO(productoId);
            return ResponseEntity.ok(lotes);
        } catch (ResourceNotFoundException e) {
            return buildErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND, "/lotes/disponibles/" + productoId);
        }
    }

    @GetMapping("/producto/{productoId}")
    public ResponseEntity<?> obtenerLotesPorProducto(@PathVariable Long productoId) {
        try {
            List<LoteRequestDTO> lotes = loteService.obtenerLotesPorProducto(productoId);
            return ResponseEntity.ok(lotes);
        } catch (ResourceNotFoundException e) {
            return buildErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND, "/lotes/producto/" + productoId);
        }
    }

    @GetMapping("/vencimiento")
    public ResponseEntity<?> obtenerLotesProximosAVencer(@RequestParam(name = "dias", defaultValue = "7") int dias) {
        try {
            List<LoteRequestDTO> lotes = loteService.obtenerLotesProximosAVencer(dias);
            return ResponseEntity.ok(lotes);
        } catch (ResourceNotFoundException e) {
            return buildErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND, "/lotes/vencimiento?dias=" + dias);
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody LoteNewDTO dto) {
        try {
            LoteRequestDTO lote = loteService.create(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(lote);
        } catch (BadRequestException e) {
            return buildErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST, "/lotes");
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patchUpdate(@PathVariable Long id, @RequestBody LoteRequestDTO dto) {
        try {
            LoteRequestDTO lote = loteService.patchUpdate(id, dto);
            return ResponseEntity.ok(lote);
        } catch (ResourceNotFoundException e) {
            return buildErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND, "/lotes/" + id);
        } catch (BadRequestException e) {
            return buildErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST, "/lotes/" + id);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody LoteRequestDTO dto) {
        try {
            LoteRequestDTO lote = loteService.update(id, dto);
            return ResponseEntity.ok(lote);
        } catch (ResourceNotFoundException e) {
            return buildErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND, "/lotes/" + id);
        } catch (BadRequestException e) {
            return buildErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST, "/lotes/" + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            loteService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return buildErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND, "/lotes/" + id);
        }
    }

    // Método para construir errores de forma consistente
    private ResponseEntity<ErrorResponseDTO> buildErrorResponse(String message, HttpStatus status, String path) {
        ErrorResponseDTO error = new ErrorResponseDTO(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                message,
                path
        );
        return ResponseEntity.status(status).body(error);
    }

}