package com.coffeebrewer.cursoaula2.repositories;

import com.coffeebrewer.cursoaula2.domain.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer>{

}