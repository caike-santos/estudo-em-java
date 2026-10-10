package com.faculdade.teste.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.faculdade.teste.model.Tarefa;

public record TarefaResponseDto(
    UUID id,
    String titulo,
    String descricao,
    LocalDateTime inicio,
    LocalDateTime termino,
    UUID idUsuario,
    LocalDateTime dataCriada
) {
    public static TarefaResponseDto fromEntity(Tarefa tarefa){
        return  new TarefaResponseDto(tarefa.getId(), tarefa.getTitulo(), tarefa.getDescricao(), tarefa.getInicio(), tarefa.getTermino(), tarefa.getIdUsuario(), tarefa.getDataCriada());
    }
}
