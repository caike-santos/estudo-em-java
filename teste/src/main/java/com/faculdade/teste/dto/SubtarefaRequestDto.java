package com.faculdade.teste.dto;

import jakarta.validation.constraints.NotBlank;

public record SubtarefaRequestDto(
        @NotBlank(message = "O titulo é obrigatório")
        String titulo
) {
}
