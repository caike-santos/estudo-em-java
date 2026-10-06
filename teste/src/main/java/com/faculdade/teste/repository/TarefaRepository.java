package com.faculdade.teste.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.faculdade.teste.model.Tarefa;

import java.util.List;
import java.util.Optional;



public interface TarefaRepository extends JpaRepository<Tarefa, UUID>{
    Tarefa findByTitulo(String titulo);
    
    List<Tarefa> findByIdUser(UUID idUser);
    Optional<Tarefa> findById(UUID id);
}
