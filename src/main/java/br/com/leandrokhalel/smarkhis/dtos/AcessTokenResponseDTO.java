package br.com.leandrokhalel.smarkhis.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record AcessTokenResponseDTO(

        @JsonProperty("acess_token")
        String acessToken
) {
}
