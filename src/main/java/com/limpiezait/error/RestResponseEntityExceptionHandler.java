package com.limpiezait.error;

import com.limpiezait.error.dto.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError; // Importante
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice; // Mejor que ControllerAdvice para APIs
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice // Combina @ControllerAdvice + @ResponseBody
public class RestResponseEntityExceptionHandler {

    //Manejo de Recurso No Encontrado (Igual que antes)
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorMessage> resourceNotFound(ResourceNotFoundException exception){
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(), HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    //Manejo de Validaciones (@Valid)
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

    //Este metodo se activa  cuando se lanza SinStockException
    @ExceptionHandler(SinStockException.class)
    public ResponseEntity<Map<String, Object>> manejarSinStock(SinStockException ex) {

        // Preparamos la respuesta JSON
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("timestamp", LocalDateTime.now());
        respuesta.put("tipo", "Error de Negocio");
        respuesta.put("mensaje", ex.getMessage());

        // Devolvemos un código HTTP 400 (Bad Request) o 409 (Conflict)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
    }



}

