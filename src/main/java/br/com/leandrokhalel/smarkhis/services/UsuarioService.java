package br.com.leandrokhalel.smarkhis.services;

import br.com.leandrokhalel.smarkhis.dtos.CreateUserRequestDTO;
import br.com.leandrokhalel.smarkhis.entities.User;
import br.com.leandrokhalel.smarkhis.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UserRepository userRepository;

    @Autowired
    public UsuarioService(UserRepository usuarioRepository) {
        this.userRepository = usuarioRepository;
    }

    public User save(CreateUserRequestDTO createUserRequestDTO) {
        return userRepository.save(User.builder()
                .name(createUserRequestDTO.name())
                .email(createUserRequestDTO.email())
                .password(createUserRequestDTO.password())
                .username(createUserRequestDTO.username())
                .build());
    }
}
