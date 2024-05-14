package br.com.leandrokhalel.smarkhis.repositories;

import br.com.leandrokhalel.smarkhis.entities.Mercado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MercadoRepository extends JpaRepository<Mercado, UUID> {
}
