package com.faculdade.teste.repository;

import java.time.LocalDateTime;
import java.util.UUID;

import com.faculdade.teste.model.Prioridade;
import com.faculdade.teste.model.StatusTarefa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.faculdade.teste.model.Tarefa;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface TarefaRepository extends JpaRepository<Tarefa, UUID> {
    Tarefa findByTitulo(String titulo);

    List<Tarefa> findByUsuarioId(UUID usuarioId);

    Optional<Tarefa> findById(UUID id);

    Tarefa findByTituloAndUsuarioId(String titulo, UUID usuarioId);

    @Query("""
            select t from tarefas t where t.usuario.id = :usuarioId and (:status is null or t.status = :status) and (:prioridade is null or t.prioridade = :prioridade)
    """)
    Page<Tarefa> buscarComFiltros(
            @Param("usuarioId")
            UUID usuarioId,
            @Param("status")
            StatusTarefa status,
            @Param("prioridade")
            Prioridade prioridade,
            Pageable pageable
    );

    @Query("""
         SELECT t FROM tarefas t
            WHERE (t.status = 'PENDENTE' OR t.status = 'EM_ANDAMENTO')
            AND t.termino < :agora
""")
    List<Tarefa> buscarTarefasAtrasadas(@Param("agora")LocalDateTime agora);

    Optional<Tarefa> findByIdempotencyKeyAndUsuarioId(String idempotencyKey, UUID usuarioId);
}
