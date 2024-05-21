package br.com.leandrokhalel.smarkhis.services;

import br.com.leandrokhalel.smarkhis.dtos.DadosCriacaoUsuario;
import br.com.leandrokhalel.smarkhis.entities.Usuario;
import br.com.leandrokhalel.smarkhis.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario salvar(DadosCriacaoUsuario dadosCriacaoUsuario) {
        return usuarioRepository.save(Usuario.builder()
                .nome(dadosCriacaoUsuario.nome())
                .email(dadosCriacaoUsuario.email())
                .senha(dadosCriacaoUsuario.senha())
                .username(dadosCriacaoUsuario.username())
                .build());
    }
}
