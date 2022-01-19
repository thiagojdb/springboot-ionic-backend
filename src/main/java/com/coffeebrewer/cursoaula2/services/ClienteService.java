package com.coffeebrewer.cursoaula2.services;

import com.coffeebrewer.cursoaula2.domain.Cliente;
import com.coffeebrewer.cursoaula2.repositories.ClienteRepository;
import com.coffeebrewer.cursoaula2.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;

    public Cliente find(Integer id){

        Optional<Cliente> obj = repo.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException( "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }

}
