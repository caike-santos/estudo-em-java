package com.faculdade.teste.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioRequestDto(
    @NotBlank(message = "O nome é obrigatório")
    String nome,

    @NotBlank(message = "A senha é obrigatória")
    String senha,

    @NotNull(message = "A data de nascimento é obrigatória")
    LocalDate dataNascimento
) { }
