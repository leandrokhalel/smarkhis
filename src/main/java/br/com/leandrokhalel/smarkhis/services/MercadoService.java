package br.com.leandrokhalel.smarkhis.services;

import br.com.leandrokhalel.smarkhis.entities.Mercado;
import br.com.leandrokhalel.smarkhis.repositories.MercadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MercadoService {

    private final MercadoRepository mercadoRepository;

    @Autowired
    public MercadoService(MercadoRepository mercadoRepository) {
        this.mercadoRepository = mercadoRepository;
    }

    public Mercado save(Mercado mercado) {
        return this.mercadoRepository.save(mercado);
    }
}
