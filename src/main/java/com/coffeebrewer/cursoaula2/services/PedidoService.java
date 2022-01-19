package com.coffeebrewer.cursoaula2.services;

import com.coffeebrewer.cursoaula2.domain.Pedido;
import com.coffeebrewer.cursoaula2.repositories.PedidoRepository;
import com.coffeebrewer.cursoaula2.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repo;

    public Pedido find(Integer id) {

        Optional<Pedido> obj = repo.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException( "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
    }
}
