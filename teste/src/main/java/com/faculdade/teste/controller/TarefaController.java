package com.faculdade.teste.controller;

import com.faculdade.teste.helper.TarefaModelAssembler;
import java.util.List;
import java.util.UUID;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.faculdade.teste.dto.TarefaPatchDto;
import com.faculdade.teste.dto.TarefaRequestDto;
import com.faculdade.teste.dto.TarefaResponseDto;
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
            HttpServletRequest request) {
        UUID idUsuario = (UUID) request.getAttribute("idUsuario");
        TarefaResponseDto response = tarefaService.cadastrar(dto, idUsuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefaModelAssembler.toModel(response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<TarefaResponseDto>> buscarTarefa(@PathVariable UUID id, HttpServletRequest request){
        UUID idUsuario = (UUID) request.getAttribute("idUsuario");
        TarefaResponseDto dto = tarefaService.buscarPorId(id, idUsuario);
        return ResponseEntity.ok(tarefaModelAssembler.toModel(dto));
    }

    @GetMapping()
    public ResponseEntity<CollectionModel<EntityModel<TarefaResponseDto>>> listarTarefas( HttpServletRequest request) {
        UUID idUsuario = (UUID) request.getAttribute("idUsuario");
        List<TarefaResponseDto> tarefas = tarefaService.listar(idUsuario);
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
}
