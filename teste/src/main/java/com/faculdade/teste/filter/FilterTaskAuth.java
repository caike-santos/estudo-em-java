package com.faculdade.teste.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.faculdade.teste.model.Usuario;
import com.faculdade.teste.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
@Slf4j 
@Component
public class FilterTaskAuth extends OncePerRequestFilter{
    private UsuarioRepository usuarioRepository;
    private PasswordEncoder passwordEncoder;

    public FilterTaskAuth(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder){
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
            var servletPath = request.getServletPath();
                if(servletPath.startsWith("/tarefa")){
                    var authorization = request.getHeader("Authorization");
                    if(authorization == null || !authorization.startsWith("Basic ")){
                        log.warn("Tentativa de acesso não autorizada na rota: {}", servletPath);
                        response.sendError(401);
                        return;
                    }

                    var codigoLimpo = authorization.substring("Basic".length()).trim();

                    byte[] decodificado = Base64.getDecoder().decode(codigoLimpo);

                    var codigoString = new String(decodificado);

                    String[] campos = codigoString.split(":");
                    String nome = campos[0];
                    String senha = campos[1];
                    
                    Usuario user = usuarioRepository.findByNome(nome);
                    if(user == null){
                        log.warn("Tentativa de autenticação com usuário inexistente: {}", nome);
                        response.sendError(401);
                    }else{
                        

                        if(passwordEncoder.matches(senha, user.getSenha())){
                            log.info("Usuário [{}] autenticado com sucesso para {}", nome, servletPath);
                            request.setAttribute("idUsuario", user.getId());
                            filterChain.doFilter(request, response);
                        }else{
                            log.warn("Falha de autenticação: senha incorreta para usuário [{}]", nome);
                            response.sendError(401);
                        }
                    }

    }else{
        log.debug("Rota pública acessada, passando direto pelo filtro: {}", servletPath);
        filterChain.doFilter(request, response);
    }
    }
    
}
