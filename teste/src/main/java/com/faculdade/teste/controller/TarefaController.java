package com.faculdade.teste.controller;

import com.faculdade.teste.dto.*;
import com.faculdade.teste.helper.TarefaModelAssembler;
import java.util.List;
import java.util.UUID;

import com.faculdade.teste.model.Prioridade;
import com.faculdade.teste.model.StatusTarefa;
import com.faculdade.teste.model.Tarefa;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.faculdade.teste.service.TarefaService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/tarefas")
public class TarefaController {
    private final TarefaModelAssembler tarefaModelAssembler;
    private final TarefaService tarefaService;

    TarefaController(TarefaService tarefaService, TarefaModelAssembler tarefaModelAssembler) {
        this.tarefaService = tarefaService;
        this.tarefaModelAssembler = tarefaModelAssembler;
    }

    @PostMapping
    public ResponseEntity<EntityModel<TarefaResponseDto>> cadastrarTarefa(@RequestBody @Valid TarefaRequestDto dto,
            HttpServletRequest request, @RequestHeader(value = "Idempotency-Key", required = false) String idempotencyKey) {
        UUID idUsuario = (UUID) request.getAttribute("idUsuario");
        TarefaResponseDto response = tarefaService.cadastrar(dto, idUsuario, idempotencyKey);
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefaModelAssembler.toModel(response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<TarefaResponseDto>> buscarTarefa(@PathVariable UUID id, HttpServletRequest request){
        UUID idUsuario = (UUID) request.getAttribute("idUsuario");
        TarefaResponseDto dto = tarefaService.buscarPorId(id, idUsuario);
        return ResponseEntity.ok(tarefaModelAssembler.toModel(dto));
    }

    @GetMapping()
    public ResponseEntity<CollectionModel<EntityModel<TarefaResponseDto>>> listarTarefas(HttpServletRequest request, @RequestParam(required = false)StatusTarefa status, @RequestParam(required = false)Prioridade prioridade, @PageableDefault(size = 10, sort = "inicio")Pageable pageable) {
        UUID idUsuario = (UUID) request.getAttribute("idUsuario");
        List<TarefaResponseDto> tarefas = tarefaService.listar(idUsuario, status, prioridade, pageable);
        return ResponseEntity.ok(tarefaModelAssembler.toCollectionModel(tarefas));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<TarefaResponseDto>> atualizarTarefa(@RequestBody @Valid TarefaRequestDto dto,
            HttpServletRequest request, @PathVariable UUID id) {
        UUID idUsuario = (UUID) request.getAttribute("idUsuario");
        TarefaResponseDto response = tarefaService.atualizar(dto, idUsuario, id);
        return ResponseEntity.ok(tarefaModelAssembler.toModel(response));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EntityModel<TarefaResponseDto>> atualizarTarefaParcialmente(@RequestBody @Valid TarefaPatchDto dto,
            HttpServletRequest request, @PathVariable UUID id) {
        UUID idUsuario = (UUID) request.getAttribute("idUsuario");
        TarefaResponseDto response = tarefaService.atualizar(dto, idUsuario, id);
        return ResponseEntity.ok(tarefaModelAssembler.toModel(response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTarefa(@PathVariable UUID id, HttpServletRequest request){
        UUID idUsuario = (UUID) request.getAttribute("idUsuario");
        tarefaService.deletar(id, idUsuario);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/iniciar")
    public ResponseEntity<EntityModel<TarefaResponseDto>> iniciarTarefa(HttpServletRequest request, @PathVariable UUID id){
        UUID idUsuario = (UUID) request.getAttribute("idUsuario");
        TarefaResponseDto response = tarefaService.iniciarTarefa(id, idUsuario);
        return ResponseEntity.ok(tarefaModelAssembler.toModel(response));
    }

    @PatchMapping("/{id}/concluir")
    public ResponseEntity<EntityModel<TarefaResponseDto>> concluirTarefa(HttpServletRequest request, @PathVariable UUID id){
        UUID idUsuario = (UUID) request.getAttribute("idUsuario");
        TarefaResponseDto response = tarefaService.concluirTarefa(id, idUsuario);
        return ResponseEntity.ok(tarefaModelAssembler.toModel(response));
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<EntityModel<TarefaResponseDto>> cancelarTarefa(HttpServletRequest request, @PathVariable UUID id){
        UUID idUsuario = (UUID) request.getAttribute("idUsuario");
        TarefaResponseDto response = tarefaService.cancelarTarefa(id, idUsuario);
        return ResponseEntity.ok(tarefaModelAssembler.toModel(response));
    }

    @PostMapping("/{id}/subtarefas")
    public ResponseEntity<EntityModel<TarefaResponseDto>> cadastrarSubtarefas(@RequestBody @Valid SubtarefaRequestDto dto,  @PathVariable UUID id, HttpServletRequest request){
        UUID idUsuario = (UUID) request.getAttribute("idUsuario");
        TarefaResponseDto response = tarefaService.adicionarSubtarefa(id, dto, idUsuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefaModelAssembler.toModel(response));
    }

    @PatchMapping("/{id}/subtarefas/{idSubtarefa}/concluir")
    public ResponseEntity<EntityModel<TarefaResponseDto>> concluirSubtarefas(@PathVariable UUID id, @PathVariable UUID idSubtarefa, HttpServletRequest request){
        UUID idUsuario = (UUID) request.getAttribute("idUsuario");
        TarefaResponseDto response = tarefaService.concluirSubtarefa(id, idSubtarefa, idUsuario);
        return ResponseEntity.ok(tarefaModelAssembler.toModel(response));
    }

    @GetMapping("/estatisticas")
    public ResponseEntity<EstatisticasResponseDto> obterEstatisticas(HttpServletRequest request){
        UUID idUsuario = (UUID) request.getAttribute("idUsuario");
        EstatisticasResponseDto response = tarefaService.obterEstatistica(idUsuario);
        return ResponseEntity.ok(response);
    }
}
