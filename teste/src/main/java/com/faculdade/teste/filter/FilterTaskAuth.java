package com.faculdade.teste.filter;

import java.io.IOException;
import java.util.Base64;


import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.faculdade.teste.model.UsuarioTeste;
import com.faculdade.teste.repository.UsuarioRepository;
import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterTaskAuth extends OncePerRequestFilter{
    private UsuarioRepository usuarioRepository;

    public FilterTaskAuth(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
            var servletPath = request.getServletPath();
                if(servletPath.startsWith("/tarefa")){
                    var authorization = request.getHeader("Authorization");

                    var codigoLimpo = authorization.substring("Basic".length()).trim();

                    byte[] decodificado = Base64.getDecoder().decode(codigoLimpo);

                    var codigoString = new String(decodificado);

                    String[] campos = codigoString.split(":");
                    String nome = campos[0];
                    String senha = campos[1];
                    
                    UsuarioTeste user = usuarioRepository.findByNome(nome);
                    if(user == null){
                        response.sendError(401);
                    }else{
                        var verificaçaoSenha = BCrypt.verifyer().verify(senha.toCharArray(), user.getSenha());

                        if(verificaçaoSenha.verified){
                            System.out.println("passou");
                            request.setAttribute("idUser", user.getId());
                            filterChain.doFilter(request, response);
                        }else{
                            response.sendError(401);
                        }
                    }

    }else{
        System.out.println("passou direto");
        filterChain.doFilter(request, response);
    }
    }
    
}
