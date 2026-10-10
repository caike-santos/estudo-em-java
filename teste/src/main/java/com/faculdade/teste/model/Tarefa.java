package com.faculdade.teste.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NonNull;

@Data
@Entity(name = "tarefas")
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 50, unique = false)
    private String titulo;

    private String descricao;

    private LocalDateTime inicio;

    private LocalDateTime termino;

    @Column(nullable = false)
    @NonNull
    private UUID idUsuario;

    @CreationTimestamp
    private LocalDateTime DataCriada;

    public Tarefa(){}

}
