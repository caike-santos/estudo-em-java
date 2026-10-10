package com.faculdade.teste.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import com.faculdade.teste.model.Usuario;

public record UsuarioResponseDto(
    UUID id,
    String nome,
    LocalDate dataNascimento,
    LocalDateTime dataCriacao
) {
    public static UsuarioResponseDto fromEntity(Usuario usuario){
        return  new UsuarioResponseDto(
            usuario.getId(),
            usuario.getNome(),
            usuario.getDataNascimento(),
            usuario.getDataCriacao()
        );
    }
}
