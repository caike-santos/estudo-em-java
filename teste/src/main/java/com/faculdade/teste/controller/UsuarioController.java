package com.faculdade.teste.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.faculdade.teste.dto.LoginRequestDto;
import com.faculdade.teste.dto.LoginResponseDto;
import com.faculdade.teste.dto.UsuarioRequestDto;
import com.faculdade.teste.dto.UsuarioResponseDto;
import com.faculdade.teste.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> exibirUsuario(@PathVariable UUID id, HttpServletRequest request) {
        UUID idUsuario = (UUID) request.getAttribute("idUsuario");
        UsuarioResponseDto response = usuarioService.exibir(id, idUsuario);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> logarUsuario(@RequestBody @Valid LoginRequestDto dto) {
        LoginResponseDto response = usuarioService.login(dto);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDto> cadastrarUsuario(@RequestBody @Valid UsuarioRequestDto dto) {
        UsuarioResponseDto response = usuarioService.cadastrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> alterarUsuario(@PathVariable UUID id, HttpServletRequest request,
            @RequestBody UsuarioRequestDto dto) {
        UUID idUsuario = (UUID) request.getAttribute("idUsuario");
        UsuarioResponseDto response = usuarioService.alterar(dto, id, idUsuario);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable UUID id, HttpServletRequest request) {
        UUID idUsuario = (UUID) request.getAttribute("idUsuario");
        usuarioService.deletar(id, idUsuario);
        return ResponseEntity.noContent().build();
    }

}
