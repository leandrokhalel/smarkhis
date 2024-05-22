package br.com.leandrokhalel.smarkhis.controllers;

import br.com.leandrokhalel.smarkhis.dtos.CreateShoppingCartRequestDTO;
import br.com.leandrokhalel.smarkhis.entities.ShoppingCart;
import br.com.leandrokhalel.smarkhis.services.ShoppingCartService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

    private ShoppingCartService shoppingCartService;

    @Autowired
    public ShoppingCartController(ShoppingCartService carrinhoService) {
        this.shoppingCartService = carrinhoService;
    }

    @PostMapping("/")
    public ResponseEntity<ShoppingCart> save(@RequestBody CreateShoppingCartRequestDTO createShoppingCartRequestDTO, HttpServletRequest request) {
        String userId = request.getAttribute("userId").toString().replace("\"", "");
        ShoppingCart shoppingCart = shoppingCartService.save(createShoppingCartRequestDTO, UUID.fromString(userId));
        return ResponseEntity
                .created(ServletUriComponentsBuilder
                        .fromCurrentRequest().path("/{id}")
                        .buildAndExpand(shoppingCart.getId())
                        .toUri())
                .body(shoppingCart);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShoppingCart> findById(UUID id) {
        return ResponseEntity.ok(shoppingCartService.findById(id));
    }

    @GetMapping("/")
    public ResponseEntity<Page<ShoppingCart>> findAll(Pageable pageable) {
        return ResponseEntity.ok(shoppingCartService.findAll(pageable));
    }
}
