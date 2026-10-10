package com.faculdade.teste.controller;

import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/tarefa")
public class TarefaController {
    private final TarefaService tarefaService;

    TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }
    @PostMapping
    public ResponseEntity<TarefaResponseDto> cadastrarTarefa(@RequestBody @Valid TarefaRequestDto dto, HttpServletRequest request){
        UUID idUsuario = (UUID) request.getAttribute("idUsuario");
        TarefaResponseDto response = tarefaService.cadastrar(dto, idUsuario); 

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<TarefaResponseDto>> listarTarefas(HttpServletRequest request){
        UUID idUsuario = (UUID) request.getAttribute("idUsuario");
        List<TarefaResponseDto> response = tarefaService.listar(idUsuario);

        return  ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TarefaResponseDto> atualizarTarefa(@RequestBody @Valid TarefaRequestDto dto, HttpServletRequest request, @PathVariable UUID id){
        UUID idUsuario = (UUID) request.getAttribute("idUsuario");
        TarefaResponseDto response = tarefaService.atualizar(dto, idUsuario, id);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping("/{id}")
     public ResponseEntity<TarefaResponseDto> atualizarTarefaParcialmente(@RequestBody @Valid TarefaPatchDto dto, HttpServletRequest request, @PathVariable UUID id){
       UUID idUsuario = (UUID) request.getAttribute("idUsuario");
        TarefaResponseDto response = tarefaService.atualizar(dto, idUsuario, id);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
