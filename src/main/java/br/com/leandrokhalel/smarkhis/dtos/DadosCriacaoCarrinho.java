package br.com.leandrokhalel.smarkhis.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.UUID;

public record DadosCriacaoCarrinho(

        @JsonProperty("itens")
        List<UUID> itensIds
) {
}
