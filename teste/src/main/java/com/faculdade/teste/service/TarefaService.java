package com.faculdade.teste.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.faculdade.teste.dto.TarefaPatchDto;
import com.faculdade.teste.dto.TarefaRequestDto;
import com.faculdade.teste.dto.TarefaResponseDto;
import com.faculdade.teste.model.Tarefa;
import com.faculdade.teste.repository.TarefaRepository;
import com.faculdade.teste.repository.UsuarioRepository;
import com.faculdade.teste.utils.Utils;

import lombok.extern.slf4j.Slf4j;
@Slf4j 
@Service
public class TarefaService {
    private final TarefaRepository tarefaRepository;
    private final UsuarioRepository usuarioRepository;

    public TarefaService(TarefaRepository tarefaRepository, UsuarioRepository usuarioRepository) {
        this.tarefaRepository = tarefaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public TarefaResponseDto cadastrar(TarefaRequestDto dto, UUID idUsuario) {

        Tarefa tarefa = new Tarefa();
        tarefa.setIdUsuario(idUsuario);
        tarefa.setTitulo(dto.titulo());
        tarefa.setDescricao(dto.descricao());
        tarefa.setInicio(dto.inicio());
        tarefa.setTermino(dto.termino());

        if (usuarioRepository.findById(tarefa.getIdUsuario()).isEmpty()) {
            throw new IllegalArgumentException("Usuario nao existe");
        }

        if (tarefaRepository.findByTitulo(tarefa.getTitulo()) != null) {
            throw new IllegalArgumentException("Titulo já existente");
        }

        var dataAtual = LocalDateTime.now();
        if (dataAtual.isAfter(tarefa.getInicio()) || dataAtual.isAfter(tarefa.getTermino())) {
            throw new IllegalArgumentException("A data de inicio/termino tem que ser maior que a data atual");
        }

        if (tarefa.getInicio().isAfter(tarefa.getTermino())) {
            throw new IllegalArgumentException("A data de inicio tem que ser menor que a data de termino");
        }

        if (tarefa.getTitulo().length() > 50) {
            throw new IllegalArgumentException("O campo titulo não deve conter mais de 50 caracteres");
        }

        Tarefa tarefaSalva = tarefaRepository.save(tarefa);
        log.info("Tarefa [{}] criada com sucesso pelo usuário [{}]", tarefaSalva.getId(), idUsuario);
        return TarefaResponseDto.fromEntity(tarefaSalva);
    }

    public List<TarefaResponseDto> listar(UUID idUser){
        List<Tarefa> tarefas = tarefaRepository.findByIdUsuario(idUser);
        List<TarefaResponseDto> dto = new ArrayList<>();

        for(Tarefa t : tarefas){
            dto.add(TarefaResponseDto.fromEntity(t));
        }
        return dto;
    }

    public TarefaResponseDto atualizar(TarefaRequestDto dto, UUID idUsuario, UUID id){
        return  atualizarDados(dto, idUsuario, id);
    }

    public TarefaResponseDto atualizar(TarefaPatchDto dto, UUID idUsuario, UUID id){
        return  atualizarDados(dto, idUsuario, id);
    }

    private  TarefaResponseDto atualizarDados(Object dto, UUID idUsuario, UUID id){
        var tarefaAntiga = tarefaRepository.findById(id)
        .orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada"));

       
        if(!tarefaAntiga.getIdUsuario().equals(idUsuario)){
            log.warn("Usuário [{}] tentou alterar a tarefa [{}] pertencente a outro usuário!", idUsuario, id);
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "O usuario não tem permissão para alterar essa tarefa");
        }
        
        Utils.copyNonNullProperties(dto, tarefaAntiga);

        if (tarefaAntiga.getInicio().isAfter(tarefaAntiga.getTermino())) {
            throw new IllegalArgumentException("A data de inicio tem que ser menor que a data de termino");
        }
        
        Tarefa tarefaAtualizada = tarefaRepository.save(tarefaAntiga);
        return TarefaResponseDto.fromEntity(tarefaAtualizada);
    }

}
