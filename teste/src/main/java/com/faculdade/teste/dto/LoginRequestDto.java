package com.faculdade.teste.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDto(
    @NotBlank(message = "nome obrigatório")
    String nome,
    @NotBlank(message = "senha obrigatória")
    String senha
) {
    
}
