package com.faculdade.teste.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.faculdade.teste.model.Prioridade;
import com.faculdade.teste.model.StatusTarefa;
import com.faculdade.teste.model.Subtarefa;
import com.faculdade.teste.model.Tarefa;

public record TarefaResponseDto(
    UUID id,
    String titulo,
    String descricao,
    Prioridade prioridade,
    StatusTarefa status,
    LocalDateTime inicio,
    LocalDateTime termino,
    LocalDateTime conclusao,
    UUID idUsuario,
    LocalDateTime dataCriada,
    List<SubtarefaResponseDto> subtarefas
) {
    public static TarefaResponseDto fromEntity(Tarefa tarefa){
        return  new TarefaResponseDto(tarefa.getId(), tarefa.getTitulo(), tarefa.getDescricao(), tarefa.getPrioridade(), tarefa.getStatus(), tarefa.getInicio(), tarefa.getTermino(), tarefa.getConclusao(), tarefa.getUsuario().getId(), tarefa.getDataCriada(), tarefa.getSubtarefas().stream().map(SubtarefaResponseDto::fromEntity).toList());
    }
}
