package com.faculdade.teste.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TarefaRequestDto(
    @NotBlank(message = "O titulo é obrigatório")
    @Size(max = 50, message = "O campo titulo não deve conter mais de 50 caracteres")
    String titulo,
    @NotBlank(message =  "A descrição é obrigatória")
    String descricao,
    @NotNull(message = "A data/hora de inicio é obrigatória")
    LocalDateTime inicio,
    @NotNull(message = "A data/hora de termino é obrigatória")
    LocalDateTime termino
) {
    
}
