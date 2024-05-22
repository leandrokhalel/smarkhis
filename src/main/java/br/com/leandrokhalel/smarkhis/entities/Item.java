package br.com.leandrokhalel.smarkhis.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity(name = "item_table")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "O campo [name] não pode estar em branco")
    @Pattern(regexp = "^.{4,50}$", message = "o campo [name] deve ter entre 4-50 caracteres")
    @Column(nullable = false, length = 50)
    private String name;

    @ManyToMany(mappedBy = "items")
    @JsonIgnore
    private List<ShoppingCart> shoppingCarts;
}
