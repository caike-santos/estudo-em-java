package com.faculdade.teste.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarTarefa(@RequestBody @NonNull Tarefa tarefa, HttpServletRequest request){
        if(usuarioRepository.findById(tarefa.getIdUsuario()).isEmpty()){
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario nao existe");
        }

        if(tarefaRepository.findByTitulo(tarefa.getTitulo()) != null){
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Titulo já existente");
        }

        tarefaRepository.save(tarefa);
        return ResponseEntity.status(HttpStatus.CREATED).body("Tarefa criada" + request.getAttribute("idUser"));
    }
}
