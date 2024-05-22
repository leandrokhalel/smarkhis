package br.com.leandrokhalel.smarkhis.security;

import br.com.leandrokhalel.smarkhis.repositories.UserRepository;
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
public class UserSecurityFilter extends OncePerRequestFilter {

    private JwtTokenService jwtTokenService;
    private UserRepository usuarioRepository;

    @Autowired
    public UserSecurityFilter(JwtTokenService jwtTokenService, UserRepository usuarioRepository) {
        this.jwtTokenService = jwtTokenService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getToken(request);
        if (token != null) {
            var tokenValidado = jwtTokenService.validateToken(token);
            var usuario = usuarioRepository.findByUsername(tokenValidado.getSubject());
            var id = tokenValidado.getClaim("userId");
            if (usuario != null) {
                request.setAttribute("userId", id);
                var auth = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
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
