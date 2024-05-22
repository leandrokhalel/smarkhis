package br.com.leandrokhalel.smarkhis.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.UUID;

public record CreateShoppingCartRequestDTO(

        String nickname,

        @JsonProperty("items_ids")
        List<UUID> itemsIds
) {
}
