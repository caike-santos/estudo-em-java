package com.faculdade.teste.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.faculdade.teste.model.UsuarioTeste;


public interface UsuarioRepository extends JpaRepository<UsuarioTeste, UUID> {
    UsuarioTeste findByNome(String nome);
}
