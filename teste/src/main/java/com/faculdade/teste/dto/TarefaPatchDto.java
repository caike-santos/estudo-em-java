package com.faculdade.teste.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Size;

public record TarefaPatchDto(
    @Size(max = 50, message = "O campo titulo não deve conter mais de 50 caracteres")
    String titulo,
    String descricao,
    LocalDateTime inicio,
    LocalDateTime termino
) {
    
}
