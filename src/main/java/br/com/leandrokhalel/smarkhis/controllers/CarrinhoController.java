package br.com.leandrokhalel.smarkhis.controllers;

import br.com.leandrokhalel.smarkhis.dtos.DadosCriacaoCarrinho;
import br.com.leandrokhalel.smarkhis.dtos.DadosCriacaoUsuario;
import br.com.leandrokhalel.smarkhis.entities.Carrinho;
import br.com.leandrokhalel.smarkhis.services.CarrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/carrinho")
public class CarrinhoController {

    private final CarrinhoService carrinhoService;

    @Autowired
    public CarrinhoController(final CarrinhoService carrinhoService) {
        this.carrinhoService = carrinhoService;
    }

    @PostMapping("/")
    public ResponseEntity<Carrinho> salvar(@RequestBody DadosCriacaoCarrinho dadosCarrinho) {
        var carrinho = carrinhoService.salvar(dadosCarrinho);
        return ResponseEntity
                .created(ServletUriComponentsBuilder
                        .fromCurrentRequest().path("/{id}")
                        .buildAndExpand(carrinho.getId())
                        .toUri())
                .body(carrinho);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carrinho> listarPorId(final UUID id) {
        return ResponseEntity.ok(carrinhoService.listaPorId(id));
    }

    @GetMapping("/")
    public ResponseEntity<Page<Carrinho>>listarTodos(Pageable pageable) {
        return ResponseEntity.ok(carrinhoService.listarTodos(pageable));
    }

    void atualizarPorId(UUID id) {
    }

    void deletarPorId(UUID id) {
    }
}
