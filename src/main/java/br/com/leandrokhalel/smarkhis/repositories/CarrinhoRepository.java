package br.com.leandrokhalel.smarkhis.repositories;

import br.com.leandrokhalel.smarkhis.entities.Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CarrinhoRepository extends JpaRepository<Carrinho, UUID> {
}
