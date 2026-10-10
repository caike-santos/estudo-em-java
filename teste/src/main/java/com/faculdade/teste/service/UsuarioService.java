package com.faculdade.teste.service;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.faculdade.teste.dto.UsuarioRequestDto;
import com.faculdade.teste.dto.UsuarioResponseDto;
import com.faculdade.teste.model.Usuario;
import com.faculdade.teste.repository.UsuarioRepository;


@Service 
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder){
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UsuarioResponseDto cadastrar(UsuarioRequestDto dto){
        if(usuarioRepository.findByNome(dto.nome()) != null){
            throw new IllegalArgumentException("Nome já utilizado");
        }

        String senhaCriptografada = passwordEncoder.encode(dto.senha());

        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setSenha(senhaCriptografada);
        usuario.setDataNascimento(dto.dataNascimento());

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return UsuarioResponseDto.fromEntity(usuarioSalvo);
    }
}
