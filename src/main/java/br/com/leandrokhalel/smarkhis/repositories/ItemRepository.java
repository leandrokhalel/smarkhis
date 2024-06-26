package br.com.leandrokhalel.smarkhis.repositories;

import br.com.leandrokhalel.smarkhis.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ItemRepository extends JpaRepository<Item, UUID> {
}
