package br.com.leandrokhalel.smarkhis.services;

import br.com.leandrokhalel.smarkhis.dtos.CarrinhoDadosCriacao;
import br.com.leandrokhalel.smarkhis.entities.Carrinho;
import br.com.leandrokhalel.smarkhis.entities.Item;
import br.com.leandrokhalel.smarkhis.repositories.CarrinhoRepository;
import br.com.leandrokhalel.smarkhis.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CarrinhoService {

    private final CarrinhoRepository carrinhoRepository;
    private final ItemRepository itemRepository;

    @Autowired
    public CarrinhoService(CarrinhoRepository carrinhoRepository, ItemRepository itemRepository) {
        this.carrinhoRepository = carrinhoRepository;
        this.itemRepository = itemRepository;
    }

    public Carrinho salvar(CarrinhoDadosCriacao dadosCriacao) {
        List<Item> itens = itemRepository.findAllById(dadosCriacao.itensIds());
        var carrinho = Carrinho.builder()
                .itens(itens)
                .build();
        return carrinhoRepository.save(carrinho);
    }

    public Carrinho listaPorId(UUID id) {
        return carrinhoRepository.findById(id).get();
    }

    public Page<Carrinho> listarTodos(Pageable pageable) {
        return carrinhoRepository.findAll(pageable);
    }
}
