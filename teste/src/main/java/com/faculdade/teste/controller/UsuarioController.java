package com.faculdade.teste.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.faculdade.teste.model.UsuarioTeste;
import com.faculdade.teste.repository.UsuarioRepository;

import at.favre.lib.crypto.bcrypt.BCrypt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    
    private UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    
    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarUsuario(@RequestBody @NonNull UsuarioTeste user ) {
        if(usuarioRepository.findByNome(user.getNome()) != null){
            System.err.println("Nome já utilizado");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nome já utilizado");
        }

        String senha = user.getSenha();
        String senhaCriptografada = BCrypt.withDefaults().hashToString(12, senha.toCharArray());
        user.setSenha(senhaCriptografada);
        usuarioRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario criado com sucesso");
    }
    
}
