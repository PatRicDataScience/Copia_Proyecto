package com.example.stockify.config;


import com.example.stockify.excepciones.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleResourceNotFound(ResourceNotFoundException ex) {
        return new ErrorMessage(HttpStatus.NOT_FOUND.value(), ex.getMessage(),LocalDateTime.now());
    }

    @ExceptionHandler(StockInsuficienteException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleStockInsuficiente(StockInsuficienteException ex) {
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.getMessage(),LocalDateTime.now());
    }

    @ExceptionHandler(OperacionNoPermitidaException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorMessage handleOperacionNoPermitida(OperacionNoPermitidaException ex) {
        return new ErrorMessage(HttpStatus.CONFLICT.value(), ex.getMessage(),LocalDateTime.now());
    }

    @ExceptionHandler(ValidacionException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorMessage handleValidacion(ValidacionException ex) {
        return new ErrorMessage(HttpStatus.UNPROCESSABLE_ENTITY.value(), ex.getMessage(),LocalDateTime.now());
    }

    @ExceptionHandler(ReporteException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage handleReporte(ReporteException ex) {
        return new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error al generar el reporte: " + ex.getMessage(),LocalDateTime.now());
    }
}
