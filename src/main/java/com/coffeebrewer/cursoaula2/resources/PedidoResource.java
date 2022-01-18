package com.coffeebrewer.cursoaula2.resources;

import com.coffeebrewer.cursoaula2.domain.Pedido;
import com.coffeebrewer.cursoaula2.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

    @Autowired
    public PedidoService service;

    @RequestMapping(value = "{id}", method= RequestMethod.GET)
    public ResponseEntity<?> listar(@PathVariable Integer id){
            Pedido obj = service.buscar(id);
            return ResponseEntity.ok().body(obj);
    }
}