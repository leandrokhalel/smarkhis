package br.com.leandrokhalel.smarkhis.controllers;

import br.com.leandrokhalel.smarkhis.dtos.CreateUserRequestDTO;
import br.com.leandrokhalel.smarkhis.entities.User;
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
@RequestMapping("/user")
public class UserController {

    private final UsuarioService usuarioService;

    @Autowired
    public UserController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/")
    public ResponseEntity<User> save(@RequestBody @Valid CreateUserRequestDTO createUserRequestDTO) {
            User user = usuarioService.save(createUserRequestDTO);
            return ResponseEntity
                    .created(ServletUriComponentsBuilder
                            .fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(user.getId())
                            .toUri())
                    .body(user);
    }
}
