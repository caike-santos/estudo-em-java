package com.faculdade.teste.dto;

import com.faculdade.teste.model.Subtarefa;

import java.util.UUID;

public record SubtarefaResponseDto(UUID id, String titulo, boolean concluida) {
    public static SubtarefaResponseDto fromEntity(Subtarefa subtarefa){
        return new SubtarefaResponseDto(subtarefa.getId(), subtarefa.getTitulo(), subtarefa.isConcluida());
    }
}
