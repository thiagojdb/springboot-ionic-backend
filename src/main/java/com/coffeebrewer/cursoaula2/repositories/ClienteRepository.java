package com.coffeebrewer.cursoaula2.repositories;

import com.coffeebrewer.cursoaula2.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

    @Transactional(readOnly = true)
    Cliente findByEmail(String email);

}