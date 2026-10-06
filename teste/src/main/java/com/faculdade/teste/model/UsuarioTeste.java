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

@Data
@Entity(name = "usuario")
public class UsuarioTeste {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = false)
    private String nome;

    @Column(nullable = true)
    private Integer idade;

    @Column(nullable = false)
    private String senha;

    @CreationTimestamp
    private LocalDateTime dataCriacao;
    
    public UsuarioTeste(){}

    
}
