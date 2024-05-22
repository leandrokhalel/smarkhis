package br.com.leandrokhalel.smarkhis.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity(name = "shopping_cart_table")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "O campo [nickname] não pode estar em branco")
    @Pattern(regexp = "^.{4,50}$", message = "o campo [nickname] deve ter entre 4-50 caracteres")
    @Column(nullable = false)
    private String nickname;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @JsonProperty(value = "user_id")
    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @ManyToMany
    @JoinTable(name = "shopping_cart_items_table",
            joinColumns = @JoinColumn(name = "shopping_cart_table_id"),
            inverseJoinColumns = @JoinColumn(name = "item_table_id"))
    private List<Item> items;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant createdAt;
}
