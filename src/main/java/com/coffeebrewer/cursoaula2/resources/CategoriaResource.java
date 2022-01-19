package com.coffeebrewer.cursoaula2.resources;

import com.coffeebrewer.cursoaula2.domain.Categoria;
import com.coffeebrewer.cursoaula2.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    public CategoriaService service;

    @RequestMapping(value = "{id}", method= RequestMethod.GET)
    public ResponseEntity<?> listar(@PathVariable Integer id){
            Categoria obj = service.buscar(id);
            return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody Categoria obj){
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
                path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

}