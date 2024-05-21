package br.com.leandrokhalel.smarkhis.exception;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private MessageSource messageSource;

    public GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handlerMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        List<MensagemDeErroDTO> mensagensDeErro = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(err -> {
            String mensagem = messageSource.getMessage(err, LocaleContextHolder.getLocale());
            var mensagemDeErro = MensagemDeErroDTO.builder()
                    .mensagem(mensagem)
                    .campo(err.getField())
                    .build();
            mensagensDeErro.add(mensagemDeErro);
        });
        return ResponseEntity.badRequest().body(mensagensDeErro);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<?> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
