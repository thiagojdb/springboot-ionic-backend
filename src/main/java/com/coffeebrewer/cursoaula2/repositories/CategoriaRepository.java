package com.coffeebrewer.cursoaula2.repositories;

import com.coffeebrewer.cursoaula2.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{

}