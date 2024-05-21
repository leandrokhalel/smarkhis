package br.com.leandrokhalel.smarkhis.repositories;

import br.com.leandrokhalel.smarkhis.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    Usuario findByUsername(String username);

    UserDetails findByEmail(String subject);
}
