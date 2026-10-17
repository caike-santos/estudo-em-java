package com.faculdade.teste.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusTarefa status = StatusTarefa.PENDENTE;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Prioridade prioridade;

    private LocalDateTime inicio;

    private LocalDateTime termino;

    private LocalDateTime conclusao;

    @OneToMany(mappedBy = "tarefa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Subtarefa> subtarefas = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ElementCollection
    @CollectionTable(name = "tarefa_historico", joinColumns =
    @JoinColumn(name = "tarefa_id"))
    @Column(name = "evento")
    private List<String> historico = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime dataCriada;

    public Tarefa(){}

    public void iniciar(){
        if(this.status != StatusTarefa.PENDENTE){
            throw new IllegalArgumentException("Apenas tarefas pendentes podem ser iniciadas");
        }
        this.registrarEvento("Tarefa iniciada");
        this.status = StatusTarefa.EM_ANDAMENTO;
    }

    public void concluir(){
        if(this.status == StatusTarefa.CONCLUIDA || this.status == StatusTarefa.CANCELADA){
            throw new IllegalArgumentException("Tarefas concluídas e cancelados não podem ser concluídas");
        }
        for(Subtarefa s : this.subtarefas){
            if(!s.isConcluida()){
                throw new IllegalArgumentException("Não é possível concluir a tarefa principal enquanto houver subtarefas pendentes");
            }
        }
        this.status = StatusTarefa.CONCLUIDA;
        this.registrarEvento("Tarefa concluída");
        this.conclusao = LocalDateTime.now();
    }

    public void cancelar(){
        if(this.status == StatusTarefa.CONCLUIDA || this.status == StatusTarefa.CANCELADA){
            throw new IllegalArgumentException("Tarefas concluídas ou canceladas não podem ser canceladas");
        }
        this.registrarEvento("Tarefa cancelada");
        this.status = StatusTarefa.CANCELADA;
    }

    public void adicionarSubtarefa(String titulo){
        if(this.status == StatusTarefa.CANCELADA || this.status == StatusTarefa.CONCLUIDA){
            throw new IllegalArgumentException("Não é possível adicionar subtarefas a uma tarefa concluída ou cancelada");
        }
        Subtarefa subtarefa = new Subtarefa();
        subtarefa.setTitulo(titulo);
        subtarefa.setTarefa(this);
        this.subtarefas.add(subtarefa);
        this.registrarEvento("SubTarefa adicionada: " + titulo);
    }

    public void concluirSubtarefa(UUID idSubtarefa){
        if(this.status == StatusTarefa.CANCELADA){
            throw new IllegalArgumentException("Não é possível concluir subtarefas a uma tarefa cancelada");
        }

        for(Subtarefa s : this.subtarefas){
            if(s.getId().equals(idSubtarefa)){
                s.setConcluida(true);
                this.registrarEvento("SubTarefa concluída: " + s.getTitulo());
                return;
            }
        }
        throw new IllegalArgumentException("Subtarefa não encontrada");
    }

    public boolean isAtrasada(){
        return (this.getStatus() == StatusTarefa.PENDENTE || this.getStatus() == StatusTarefa.EM_ANDAMENTO)
                && this.getTermino().isBefore(LocalDateTime.now());
    }

    public void registrarEvento(String descricaoEvento){
        this.historico.add(LocalDateTime.now() + " - " + descricaoEvento);
    }

}


