package com.faculdade.teste.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.faculdade.teste.model.Tarefa;
import com.faculdade.teste.repository.TarefaRepository;
import com.faculdade.teste.repository.UsuarioRepository;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/tarefa")
public class TarefaController {
    private final TarefaRepository tarefaRepository;
    private final UsuarioRepository usuarioRepository;

    TarefaController(TarefaRepository tarefaRepository, UsuarioRepository usuarioRepository) {
        this.tarefaRepository = tarefaRepository;
        this.usuarioRepository = usuarioRepository;
    }
    @PostMapping("")
    public ResponseEntity<String> cadastrarTarefa(@RequestBody @NonNull Tarefa tarefa, HttpServletRequest request){
        var idUser = request.getAttribute("idUser");
        tarefa.setIdUsuario(UUID.fromString(idUser.toString()));

        if(usuarioRepository.findById(tarefa.getIdUsuario()).isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario nao existe");
        }

        if(tarefaRepository.findByTitulo(tarefa.getTitulo()) != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Titulo já existente");
        }

        var DataAtual = LocalDateTime.now();
        if(DataAtual.isAfter(tarefa.getInicio()) || DataAtual.isAfter(tarefa.getTermino())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A data de inicio/termino tem que ser maior que a data atual");
        }

        if(tarefa.getInicio().isAfter(tarefa.getTermino())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A data de inicio tem que ser menor que a data de termino");
        }

        
        tarefaRepository.save(tarefa);
        return ResponseEntity.status(HttpStatus.CREATED).body("Tarefa criada");
    }

    @GetMapping("")
    public ResponseEntity<List<Tarefa>> listarTarefas(HttpServletRequest request){
        var idUser = request.getAttribute("idUser");
        var tarefas = tarefaRepository.findByIdUser((UUID) idUser);
        return  ResponseEntity.status(HttpStatus.OK).body(tarefas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizarTarefa(@RequestBody @NonNull Tarefa tarefa, HttpServletRequest request, @PathVariable UUID id){
        if(!tarefaRepository.existsById(id)){
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada");
        }
        var idUser = request.getAttribute("idUser");
        tarefa.setId(id);
        tarefa.setIdUsuario((UUID) idUser);
        
        tarefaRepository.save(tarefa);
        return ResponseEntity.status(HttpStatus.OK).body(tarefa);
    }
}
