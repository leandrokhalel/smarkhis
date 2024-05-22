package br.com.leandrokhalel.smarkhis.dtos;

public record CreateUserRequestDTO(
        String name,
        String email,
        String password,
        String username
) {
}
