package br.com.leandrokhalel.smarkhis.controllers;

import br.com.leandrokhalel.smarkhis.dtos.DadosCriacaoUsuario;
import br.com.leandrokhalel.smarkhis.entities.Usuario;
import br.com.leandrokhalel.smarkhis.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/")
    public ResponseEntity<Object> salvar(@RequestBody @Valid DadosCriacaoUsuario dadosCriacaoUsuario) {
            Usuario usuario = usuarioService.salvar(dadosCriacaoUsuario);

            return ResponseEntity
                    .created(ServletUriComponentsBuilder
                            .fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(usuario.getId())
                            .toUri())
                    .body(usuario);
    }
}
