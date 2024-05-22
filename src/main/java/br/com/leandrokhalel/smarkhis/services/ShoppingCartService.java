package br.com.leandrokhalel.smarkhis.services;

import br.com.leandrokhalel.smarkhis.dtos.CreateShoppingCartRequestDTO;
import br.com.leandrokhalel.smarkhis.entities.ShoppingCart;
import br.com.leandrokhalel.smarkhis.entities.Item;
import br.com.leandrokhalel.smarkhis.repositories.ShoppingCartRepository;
import br.com.leandrokhalel.smarkhis.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ShoppingCartService {

    private final ShoppingCartRepository carrinhoRepository;
    private final ItemRepository itemRepository;

    @Autowired
    public ShoppingCartService(ShoppingCartRepository carrinhoRepository, ItemRepository itemRepository) {
        this.carrinhoRepository = carrinhoRepository;
        this.itemRepository = itemRepository;
    }

    public ShoppingCart save(CreateShoppingCartRequestDTO createShoppingCartRequestDTO, UUID userId) {
        List<Item> shoppingCartItems = itemRepository.findAllById(createShoppingCartRequestDTO.itemsIds());
        var shoppingCart = ShoppingCart.builder()
                .items(shoppingCartItems)
                .nickname(createShoppingCartRequestDTO.nickname())
                .userId(userId)
                .build();
        return carrinhoRepository.save(shoppingCart);
    }

    public ShoppingCart findById(UUID id) {
        return carrinhoRepository.findById(id).get();
    }

    public Page<ShoppingCart> findAll(Pageable pageable) {
        return carrinhoRepository.findAll(pageable);
    }
}
