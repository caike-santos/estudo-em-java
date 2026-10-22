package com.faculdade.teste.filter;

import com.faculdade.teste.service.TokenService;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
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
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;
    private final Map<String, AtomicInteger> tentativasLoginPorIp = new ConcurrentHashMap<>();

    public SecurityFilter(UsuarioRepository usuarioRepository, TokenService tokenService) {
        this.usuarioRepository = usuarioRepository;
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String correlationId = request.getHeader("X-Correlation-Id");
        if (correlationId == null || correlationId.isBlank()) {
            correlationId = UUID.randomUUID().toString().substring(0, 8);
        }
        MDC.put("correlationId", correlationId);
        response.setHeader("X-Correlation-ID", correlationId);
        try {
            if ("/usuarios/login".equals(request.getRequestURI()) && "POST".equalsIgnoreCase(request.getMethod())) {
                String ip = request.getRemoteAddr();
                int tentativas = tentativasLoginPorIp.computeIfAbsent(ip, k -> new AtomicInteger(0)).incrementAndGet();
                if (tentativas > 5) {
                    log.warn("Rate limit excedido para o IP [{}] na rota de login", ip);
                    response.setStatus(429);
                    response.setContentType("application/json");
                    response.getWriter().write("{erro: Muitas tentativas de login. Tente novamente em 1 minuto.}");
                    return;
                }
            }
            String token = recuperarToken(request);
            if (token != null) {
                String subject = tokenService.validarToken(token);
                if (subject != null) {
                    UUID idUsuario = UUID.fromString(subject);
                    Usuario usuario = usuarioRepository.findById(idUsuario).orElse(null);
                    if (usuario != null) {
                        var authentication = new UsernamePasswordAuthenticationToken(usuario, null,
                                Collections.emptyList());
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                        log.info("Usuário [{}] autenticado com sucesso ", usuario.getNome());
                        request.setAttribute("idUsuario", usuario.getId());
                    }
                }
            }
            filterChain.doFilter(request, response);
        } finally {
            MDC.remove("correlationId");
        }
    }

    private String recuperarToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null;
        }

        return authHeader.replace("Bearer ", "").trim();
    }

    @Scheduled(fixedRate = 60000)
    public void limparRateLimit() {
        tentativasLoginPorIp.clear();
    }

}
