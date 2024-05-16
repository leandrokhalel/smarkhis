package br.com.leandrokhalel.smarkhis.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Length(min = 4, max = 50, message = "o campo [nome] deve ter entre 4-50 caracteres")
    @NotBlank(message = "o campo [nome] não pode estar em branco")
    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    @NotBlank
    @Email(message = "O campo [email] deve conter um e-mal válido")
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Length(min = 8, message = "o campo [senha] deve conter no mínimo 8 caracteres")
    @Column(name = "senha", nullable = false)
    private String senha;

    @CreationTimestamp
    @Column(name = "criado_em", nullable = false, updatable = false)
    private Instant criadoEm;
}
