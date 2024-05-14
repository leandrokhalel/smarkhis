package br.com.leandrokhalel.smarkhis.controllers;

import br.com.leandrokhalel.smarkhis.entities.Mercado;
import br.com.leandrokhalel.smarkhis.services.MercadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/mercado")
public class MercadoController {

    private final MercadoService mercadoService;

    @Autowired
    public MercadoController(MercadoService mercadoService) {
        this.mercadoService = mercadoService;
    }

    @PostMapping("/")
    public ResponseEntity<Mercado> create(@RequestBody Mercado mercado) {
        mercado = mercadoService.save(mercado);
        return ResponseEntity
                .created(ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(mercado.getId()).toUri())
                .body(mercado);
    }
}
