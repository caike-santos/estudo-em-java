package com.faculdade.teste.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.faculdade.teste.dto.SubtarefaRequestDto;
import com.faculdade.teste.model.Prioridade;
import com.faculdade.teste.model.StatusTarefa;
import com.faculdade.teste.model.Usuario;
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

        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new IllegalArgumentException("Usuario nao existe"));

        Tarefa tarefa = new Tarefa();
        tarefa.setUsuario(usuario);
        tarefa.setTitulo(dto.titulo());
        tarefa.setDescricao(dto.descricao());
        tarefa.setStatus(StatusTarefa.PENDENTE);
        tarefa.setPrioridade(dto.prioridade() != null ? dto.prioridade() : Prioridade.MEDIA);
        tarefa.setInicio(dto.inicio());
        tarefa.setTermino(dto.termino());

        if (tarefaRepository.findByTituloAndUsuarioId(tarefa.getTitulo(), idUsuario) != null) {
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
        List<Tarefa> tarefas = tarefaRepository.findByUsuarioId(idUsuario);
        List<TarefaResponseDto> dto = new ArrayList<>();

        for (Tarefa t : tarefas) {
            dto.add(TarefaResponseDto.fromEntity(t));
        }
        return dto;
    }

    public TarefaResponseDto buscarPorId(UUID id, UUID idUsuario) {
        Tarefa tarefa = buscarTarefa(id);
        verificarUsuario(tarefa, idUsuario, "Usuário [{}] tentou ler a tarefa [{}] pertencente ao usuário [{}]!");
        return TarefaResponseDto.fromEntity(tarefa);
    }

    public TarefaResponseDto atualizar(TarefaRequestDto dto, UUID idUsuario, UUID id) {
        return atualizarDados(dto, idUsuario, id);
    }

    public TarefaResponseDto atualizar(TarefaPatchDto dto, UUID idUsuario, UUID id) {
        return atualizarDados(dto, idUsuario, id);
    }

    private TarefaResponseDto atualizarDados(Object dto, UUID idUsuario, UUID id) {
        Tarefa tarefaAntiga = buscarTarefa(id);
        verificarUsuario(tarefaAntiga, idUsuario, "Usuário [{}] tentou alterar a tarefa [{}] pertencente ao usuário [{}]!");

        if(tarefaAntiga.getStatus() == StatusTarefa.CONCLUIDA || tarefaAntiga.getStatus() == StatusTarefa.CANCELADA){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não pode alterar uma tarefa já concluída ou cancelada");
        }

        Utils.copyNonNullProperties(dto, tarefaAntiga);

        if (tarefaAntiga.getInicio().isAfter(tarefaAntiga.getTermino())) {
            throw new IllegalArgumentException("A data de inicio tem que ser menor que a data de termino");
        }

        Tarefa tarefaAtualizada = tarefaRepository.save(tarefaAntiga);
        return TarefaResponseDto.fromEntity(tarefaAtualizada);
    }

    public void deletar(UUID id, UUID idUsuario) {
        Tarefa tarefa = buscarTarefa(id);
        verificarUsuario(tarefa, idUsuario, "Usuário [{}] tentou deletar a tarefa [{}] do usuário [{}]!");
        tarefaRepository.delete(tarefa);
        return;
    }

    public TarefaResponseDto iniciarTarefa(UUID id, UUID idUsuario) {
        Tarefa tarefa = buscarTarefa(id);
        verificarUsuario(tarefa, idUsuario, "Usuário [{}] tentou alterar a tarefa [{}] pertencente ao usuário [{}]!");
        tarefa.iniciar();
        return TarefaResponseDto.fromEntity(tarefaRepository.save(tarefa));
    }

    public TarefaResponseDto concluirTarefa(UUID id, UUID idUsuario) {
        Tarefa tarefa = buscarTarefa(id);
        verificarUsuario(tarefa, idUsuario, "Usuário [{}] tentou alterar a tarefa [{}] pertencente ao usuário [{}]!");
        tarefa.concluir();
        return TarefaResponseDto.fromEntity(tarefaRepository.save(tarefa));
    }

    public TarefaResponseDto cancelarTarefa(UUID id, UUID idUsuario) {
        Tarefa tarefa = buscarTarefa(id);
        verificarUsuario(tarefa, idUsuario, "Usuário [{}] tentou alterar a tarefa [{}] pertencente ao usuário [{}]!");
        tarefa.cancelar();
        return TarefaResponseDto.fromEntity(tarefaRepository.save(tarefa));
    }

    public TarefaResponseDto adicionarSubtarefa(UUID idTarefa, SubtarefaRequestDto dto, UUID idUsuario){
        Tarefa tarefa = buscarTarefa(idTarefa);
        verificarUsuario(tarefa, idUsuario, "Usuário [{}] tentou alterar a tarefa [{}] pertencente ao usuário [{}]!");
        tarefa.adicionarSubtarefa(dto.titulo());
        return TarefaResponseDto.fromEntity(tarefaRepository.save(tarefa));
    }

    public TarefaResponseDto concluirSubtarefa(UUID idTarefa, UUID idSubtarefa, UUID idUsuario){
        Tarefa tarefa = buscarTarefa(idTarefa);
        verificarUsuario(tarefa, idUsuario, "Usuário [{}] tentou alterar a tarefa [{}] pertencente ao usuário [{}]!");
        tarefa.concluirSubtarefa(idSubtarefa);
        return TarefaResponseDto.fromEntity(tarefaRepository.save(tarefa));
    }

    private Tarefa buscarTarefa(UUID id){
         Tarefa tarefa = tarefaRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Tarefa [{}] não foi encontrada no banco de dados!", id);
                    return new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada");
                });
         return tarefa;
    }

    private void verificarUsuario(Tarefa tarefa, UUID idUsuario, String msgErro){
        if (!tarefa.getUsuario().getId().equals(idUsuario)) {
            log.warn(msgErro, idUsuario, tarefa.getId(),
                    tarefa.getUsuario().getId());
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                    "O usuario não tem permissão para acessar essa tarefa");
        }
    }
}
