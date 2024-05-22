package br.com.leandrokhalel.smarkhis.dtos;

public record AuthenticateUserRequestDTO(
        String username,
        String senha) {
}
