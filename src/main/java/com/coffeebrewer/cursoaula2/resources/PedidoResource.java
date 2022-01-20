package com.coffeebrewer.cursoaula2.resources;

import com.coffeebrewer.cursoaula2.domain.Categoria;
import com.coffeebrewer.cursoaula2.domain.Pedido;
import com.coffeebrewer.cursoaula2.dto.CategoriaDTO;
import com.coffeebrewer.cursoaula2.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

    @Autowired
    public PedidoService service;

    @RequestMapping(value = "{id}", method= RequestMethod.GET)
    public ResponseEntity<Pedido> listar(@PathVariable Integer id){
            Pedido obj = service.find(id);
            return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody Pedido obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
                path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}