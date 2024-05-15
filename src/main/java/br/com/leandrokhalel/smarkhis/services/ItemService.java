package br.com.leandrokhalel.smarkhis.services;

import br.com.leandrokhalel.smarkhis.entities.Item;
import br.com.leandrokhalel.smarkhis.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item save(Item item) {
        return this.itemRepository.save(item);
    }
}
