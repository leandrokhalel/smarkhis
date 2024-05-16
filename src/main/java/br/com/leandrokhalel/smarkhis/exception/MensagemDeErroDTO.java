package br.com.leandrokhalel.smarkhis.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MensagemDeErroDTO {

    private String mensagem;
    private String campo;
}
