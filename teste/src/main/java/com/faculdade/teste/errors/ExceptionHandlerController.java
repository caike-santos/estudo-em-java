package com.faculdade.teste.errors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardErrorDto> handleValidationErrors(MethodArgumentNotValidException e) {
        List<ValidationErrorDto> errosDeCampos = new ArrayList<>();

        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            errosDeCampos.add(new ValidationErrorDto(fieldError.getField(), fieldError.getDefaultMessage()));
        }

        StandardErrorDto erro = new StandardErrorDto(
                Instant.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Erro de Validação",
                "Um ou mais campos estão invalidos",
                errosDeCampos
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<StandardErrorDto> handleResponseStatusException(ResponseStatusException e){
        StandardErrorDto erro = new StandardErrorDto(
                Instant.now(),
                e.getStatusCode().value(),
                e.getStatusCode().toString(),
                e.getReason()
        );
        return ResponseEntity.status(e.getStatusCode()).body(erro);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<StandardErrorDto> handleIllegalArgumentException(IllegalArgumentException e) {
        StandardErrorDto erro = new StandardErrorDto(
                Instant.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Regra de Negócio Inválida",
                e.getMessage()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<StandardErrorDto> handleHttpMessageNotReadableException(HttpMessageNotReadableException e){
        StandardErrorDto erro = new StandardErrorDto(
                Instant.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Formato de Requisição Inválido",
                e.getMostSpecificCause().getMessage()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardErrorDto> handleGenericException(Exception e){
        log.error("Erro interno inesperado no servidor: ", e);

        StandardErrorDto erro = new StandardErrorDto(
                Instant.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro Interno do Servidor",
                "Ocorreu um erro interno. Entre em contato com o suporte."
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }
}
