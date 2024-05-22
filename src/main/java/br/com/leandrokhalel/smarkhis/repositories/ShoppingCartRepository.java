package br.com.leandrokhalel.smarkhis.repositories;

import br.com.leandrokhalel.smarkhis.entities.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, UUID> {
}
