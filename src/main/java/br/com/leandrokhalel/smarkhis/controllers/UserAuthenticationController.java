package br.com.leandrokhalel.smarkhis.controllers;

import br.com.leandrokhalel.smarkhis.dtos.AcessTokenResponseDTO;
import br.com.leandrokhalel.smarkhis.dtos.AuthenticateUserRequestDTO;
import br.com.leandrokhalel.smarkhis.entities.User;
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
@RequestMapping("/user")
public class UserAuthenticationController {

    private AuthenticationManager authenticationManager;
    private JwtTokenService jwtTokenService;

    @Autowired
    public UserAuthenticationController(AuthenticationManager authenticationManager, JwtTokenService jwtTokenService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
    }

    @PostMapping("/auth")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticateUserRequestDTO dto) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dto.username(), dto.senha());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        String acessToken = jwtTokenService.generateToken((User) authentication.getPrincipal());
        return ResponseEntity.ok(AcessTokenResponseDTO.builder()
                .acessToken(acessToken)
                .build());
    }
}
