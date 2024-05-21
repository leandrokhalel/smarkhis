package br.com.leandrokhalel.smarkhis.controllers;

import br.com.leandrokhalel.smarkhis.dtos.DadosAutenticacaoUsuario;
import br.com.leandrokhalel.smarkhis.entities.Usuario;
import br.com.leandrokhalel.smarkhis.services.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class AutenticacaoUsuarioController {

    private AuthenticationManager authenticationManager;
    private JwtTokenService jwtTokenService;

    @Autowired
    public AutenticacaoUsuarioController(AuthenticationManager authenticationManager, JwtTokenService jwtTokenService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
    }

    @PostMapping("/autenticar/")
    public ResponseEntity<?> autenticar(@RequestBody DadosAutenticacaoUsuario dadosAutenticacaoUsuario) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(dadosAutenticacaoUsuario.username(), dadosAutenticacaoUsuario.senha());

        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        return ResponseEntity.ok((jwtTokenService.gerarToken((Usuario) authentication.getPrincipal())));
    }
}
