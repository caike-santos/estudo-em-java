package com.faculdade.teste.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.faculdade.teste.model.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    Usuario findByNome(String nome);
}
