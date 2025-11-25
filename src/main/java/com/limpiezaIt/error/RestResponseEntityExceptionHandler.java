package com.limpiezaIt.error;

import com.limpiezaIt.error.dto.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError; // Importante
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice; // Mejor que ControllerAdvice para APIs
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice // Combina @ControllerAdvice + @ResponseBody
public class RestResponseEntityExceptionHandler {

    // 1. Manejo de Recurso No Encontrado (Igual que antes)
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorMessage> resourceNotFound(ResourceNotFoundException exception){
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(), HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    // 2. Manejo de Validaciones (@Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessage> handleValidationExceptions(MethodArgumentNotValidException exception){

        // Creamos el mapa de errores es decir que campos fallan o faltan
        Map<String, String> errores = new HashMap<>();

        // Extraemos cada campo que falló y su mensaje
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String nombreCampo = ((FieldError) error).getField();
            String mensaje = error.getDefaultMessage();
            errores.put(nombreCampo, mensaje);
        });

        // Usamos el nuevo constructor del DTO
        ErrorMessage errorMessage = new ErrorMessage(
                "La validación de datos falló", // Mensaje general
                HttpStatus.BAD_REQUEST,
                errores // Pasamos el mapa detallado
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }
}