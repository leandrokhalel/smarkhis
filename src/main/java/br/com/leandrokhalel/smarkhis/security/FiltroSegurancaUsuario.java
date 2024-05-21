package br.com.leandrokhalel.smarkhis.security;

import br.com.leandrokhalel.smarkhis.repositories.UsuarioRepository;
import br.com.leandrokhalel.smarkhis.services.JwtTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class FiltroSegurancaUsuario extends OncePerRequestFilter {

    private JwtTokenService jwtTokenService;
    private UsuarioRepository usuarioRepository;

    @Autowired
    public FiltroSegurancaUsuario(JwtTokenService jwtTokenService, UsuarioRepository usuarioRepository) {
        this.jwtTokenService = jwtTokenService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getToken(request);
        if (token != null) {
            var subject = jwtTokenService.getSubject(token);
            var usuario = usuarioRepository.findByEmail(subject);

            var auth = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }
}
