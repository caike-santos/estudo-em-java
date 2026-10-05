package com.faculdade.teste.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.faculdade.teste.model.Tarefa;
import java.util.List;


public interface TarefaRepository extends JpaRepository<Tarefa, UUID>{
    Tarefa findByTitulo(String titulo);
}
