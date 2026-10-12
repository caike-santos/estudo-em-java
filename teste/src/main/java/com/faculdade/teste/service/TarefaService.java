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
            log.warn("Usuario [{}] não foi encontrado no banco de dados!", tarefa.getIdUsuario());
            throw new IllegalArgumentException("Usuario nao existe");
        }

        if (tarefaRepository.findByTituloAndIdUsuario(tarefa.getTitulo(), idUsuario) != null) {
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

    public List<TarefaResponseDto> listar(UUID  idUsuario) {
        List<Tarefa> tarefas = tarefaRepository.findByIdUsuario( idUsuario);
        List<TarefaResponseDto> dto = new ArrayList<>();

        for (Tarefa t : tarefas) {
            dto.add(TarefaResponseDto.fromEntity(t));
        }
        return dto;
    }

    public TarefaResponseDto buscarPorId(UUID id, UUID idUsuario) {
        Tarefa tarefa = tarefaRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Tarefa [{}] não foi encontrada no banco de dados!", id);
                    return new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada");
                });
        if (!idUsuario.equals(tarefa.getIdUsuario())) {
            log.warn("Usuário [{}] tentou alterar a tarefa [{}] pertencente ao usuário [{}]!", idUsuario, id,
                    tarefa.getIdUsuario());
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                    "O usuario não tem permissão para acessar essa informação");
        }
        return TarefaResponseDto.fromEntity(tarefa);
    }

    public TarefaResponseDto atualizar(TarefaRequestDto dto, UUID idUsuario, UUID id) {
        return atualizarDados(dto, idUsuario, id);
    }

    public TarefaResponseDto atualizar(TarefaPatchDto dto, UUID idUsuario, UUID id) {
        return atualizarDados(dto, idUsuario, id);
    }

    private TarefaResponseDto atualizarDados(Object dto, UUID idUsuario, UUID id) {
        Tarefa tarefaAntiga = tarefaRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Tarefa [{}] não foi encontrada no banco de dados!", id);
                    return new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada");
                });

        if (!tarefaAntiga.getIdUsuario().equals(idUsuario)) {
            log.warn("Usuário [{}] tentou alterar a tarefa [{}] pertencente ao usuário [{}]!", idUsuario, id,
                    tarefaAntiga.getId());
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                    "O usuario não tem permissão para alterar essa tarefa");
        }

        Utils.copyNonNullProperties(dto, tarefaAntiga);

        if (tarefaAntiga.getInicio().isAfter(tarefaAntiga.getTermino())) {
            throw new IllegalArgumentException("A data de inicio tem que ser menor que a data de termino");
        }

        Tarefa tarefaAtualizada = tarefaRepository.save(tarefaAntiga);
        return TarefaResponseDto.fromEntity(tarefaAtualizada);
    }

    public void deletar(UUID id, UUID idUsuario) {
        Tarefa tarefa = tarefaRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Tarefa [{}] não foi encontrada no banco de dados!", id);
                    return new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada");
                });

        if (!tarefa.getIdUsuario().equals(idUsuario)) {
            log.warn("Usuário [{}] tentou deletar a tarefa [{}] do usuário [{}]!", idUsuario, tarefa.getId(),
                    tarefa.getIdUsuario());
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                    "O usuario não tem permissão para deletar essa tarefa");
        }

        tarefaRepository.delete(tarefa);
        return;
    }

}
