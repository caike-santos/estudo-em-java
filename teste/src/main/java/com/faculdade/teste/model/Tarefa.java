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

    @CreationTimestamp
    private LocalDateTime dataCriada;

    public Tarefa(){}

    public void iniciar(){
        if(this.status != StatusTarefa.PENDENTE){
            throw new IllegalArgumentException("Apenas tarefas pendentes podem ser iniciadas");
        }
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
        this.conclusao = LocalDateTime.now();
    }

    public void cancelar(){
        if(this.status == StatusTarefa.CONCLUIDA || this.status == StatusTarefa.CANCELADA){
            throw new IllegalArgumentException("Tarefas concluídas ou canceladas não podem ser canceladas");
        }
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
    }

    public void concluirSubtarefa(UUID idSubtarefa){
        if(this.status == StatusTarefa.CANCELADA){
            throw new IllegalArgumentException("Não é possível concluir subtarefas a uma tarefa cancelada");
        }

        for(Subtarefa s : this.subtarefas){
            if(s.getId().equals(idSubtarefa)){
                s.setConcluida(true);
                return;
            }
        }
        throw new IllegalArgumentException("Subtarefa não encontrada");
    }

}


