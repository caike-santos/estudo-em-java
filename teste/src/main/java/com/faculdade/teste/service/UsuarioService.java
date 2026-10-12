package com.faculdade.teste.service;

import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.faculdade.teste.dto.LoginRequestDto;
import com.faculdade.teste.dto.LoginResponseDto;
import com.faculdade.teste.dto.UsuarioRequestDto;
import com.faculdade.teste.dto.UsuarioResponseDto;
import com.faculdade.teste.model.Usuario;
import com.faculdade.teste.repository.UsuarioRepository;
import com.faculdade.teste.utils.Utils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UsuarioService {
    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder,
            TokenService tokenService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    public LoginResponseDto login(LoginRequestDto dto) {
        Usuario usuario = usuarioRepository.findByNome(dto.nome());

        if (usuario == null || !passwordEncoder.matches(dto.senha(), usuario.getSenha())) {
            log.warn("\"Falha no login: credenciais inválidas para o usuário [{}]", dto.nome());
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuário ou senha incorretos");
        }

        String token = tokenService.gerarToken(usuario);
        return new LoginResponseDto(token);
    }

    public UsuarioResponseDto cadastrar(UsuarioRequestDto dto) {
        String senhaCriptografada = passwordEncoder.encode(dto.senha());

        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setSenha(senhaCriptografada);
        usuario.setDataNascimento(dto.dataNascimento());

        if (dto.nome() != null && dto.nome().equals(usuario.getNome()) && usuarioRepository.existsByNome(dto.nome())) {
            log.warn("Usuário [{}] já cadastrado", dto.nome());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome já utilizado");
        }

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return UsuarioResponseDto.fromEntity(usuarioSalvo);
    }

    public UsuarioResponseDto exibir(UUID id, UUID idUsuario) {
        UsuarioResponseDto usuario = usuarioRepository.findById(id).map(UsuarioResponseDto::fromEntity)
                .orElseThrow(() -> {
                    log.warn("Usuário [{}] não encontrado", id);
                    return new ResponseStatusException(HttpStatus.NOT_FOUND, "O usuario não foi encontrado");
                });

        if (!usuario.id().equals(idUsuario)) {
            log.warn("Usuário [{}] tentou acessar as informações pertencente ao usuário [{}]!", id, idUsuario);
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                    "O usuario não tem permissão para acessar essa informação");
        }

        return usuario;
    }

    public UsuarioResponseDto alterar(UsuarioRequestDto dto, UUID id, UUID idUsuario) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> {
            log.warn("Usuário [{}] não encontrado", id);
            return new ResponseStatusException(HttpStatus.NOT_FOUND, "O usuario não foi encontrado");
        });

        if (usuarioRepository.existsByNome(dto.nome())) {
            log.warn("Usuário [{}] já cadastrado", dto.nome());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome já utilizado");
        }

        if (!usuario.getId().equals(idUsuario)) {
            log.warn("Usuário [{}] tentou acessar as informações pertencente ao usuário [{}]!", id, idUsuario);
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                    "O usuario não tem permissão para acessar essa informação");
        }

        Utils.copyNonNullProperties(dto, usuario);

        if (dto.senha() != null) {
            String senhaCriptografada = passwordEncoder.encode(dto.senha());
            usuario.setSenha(senhaCriptografada);
        }

        return UsuarioResponseDto.fromEntity(usuarioRepository.save(usuario));
    }

    public void deletar(UUID id, UUID idUsuario) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> {
            log.warn("Usuário [{}] não encontrado", id);
            return new ResponseStatusException(HttpStatus.NOT_FOUND, "O usuario não foi encontrado");
        });

        if (!usuario.getId().equals(idUsuario)) {
            log.warn("Usuário [{}] tentou deletar o usuário [{}]!", id, idUsuario);
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                    "O usuario não tem permissão para deletar esse usuario");
        }

        usuarioRepository.delete(usuario);
        return;
    }
}
