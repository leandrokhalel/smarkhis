package br.com.leandrokhalel.smarkhis.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity(name = "user_table")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Pattern(regexp = "^.{4,50}$", message = "o campo [name] deve ter entre 4-50 caracteres")
    @NotBlank(message = "o campo [name] não pode estar em branco")
    @Column(nullable = false, length = 50)
    private String name;

    @NotBlank(message = "O campo [email] não pode estar em branco")
    @Email(message = "O campo [email] deve conter um e-mal válido")
    @Column(unique = true, nullable = false)
    private String email;

    @Pattern(regexp = "^.{4,30}$", message = "o campo [username] deve ter entre 4-30 caracteres")
    @NotBlank(message = "O campo [username] não pode estar em branco")
    @Column(unique = true, nullable = false, length = 20)
    private String username;

    @Pattern(regexp = "^.{8,}$", message = "o campo [password] deve ter no mínimo 8 caracteres")
    @NotBlank(message = "O campo [password] não pode estar em branco")
    @Column(nullable = false)
    private String password;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
