package com.example.stockify.excepciones;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {
    private int status;       // Código HTTP (ej: 404, 400, etc.)
    private String message;   // Mensaje de error legible
    private LocalDateTime timestamp;
}