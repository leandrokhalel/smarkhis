package br.com.leandrokhalel.smarkhis.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity(name = "carrinho")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Carrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToMany
    @JoinTable(
            name = "carrinho_item",
            joinColumns = @JoinColumn(name = "carrinho_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))    private List<Item> itens;

    @CreationTimestamp
    private Instant criadoEm;

}
