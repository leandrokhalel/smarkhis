package br.com.leandrokhalel.smarkhis.repositories;

import br.com.leandrokhalel.smarkhis.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    UserDetails findByUsername(String username);

}
