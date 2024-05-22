package br.com.leandrokhalel.smarkhis.services;

import br.com.leandrokhalel.smarkhis.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoUsuarioService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public AutenticacaoUsuarioService(UserRepository usuarioRepository) {
        this.userRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }
}
