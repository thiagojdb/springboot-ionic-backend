package com.coffeebrewer.cursoaula2.services;

import com.coffeebrewer.cursoaula2.domain.Categoria;
import com.coffeebrewer.cursoaula2.repositories.CategoriaRepository;
import com.coffeebrewer.cursoaula2.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repo;

    public Categoria buscar(Integer id) {

        Optional<Categoria> obj = repo.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException( "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
    }
}
