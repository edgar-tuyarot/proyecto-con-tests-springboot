package com.limpiezaIt.error.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Data
@NoArgsConstructor
public class ErrorMessage {
    private String message;
    private HttpStatus status;
    private Map<String, String> errors; // <--- Agregamos esto

    // Constructor para errores simples (como el 404)
    public ErrorMessage(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    // Constructor para errores de validación (como el 400)
    public ErrorMessage(String message, HttpStatus status, Map<String, String> errors) {
        this.message = message;
        this.status = status;
        this.errors = errors;
    }

}
