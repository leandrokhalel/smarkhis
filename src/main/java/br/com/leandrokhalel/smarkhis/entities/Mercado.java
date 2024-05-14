package br.com.leandrokhalel.smarkhis.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity(name = "mercado")
public class Mercado {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome;

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
