package com.coffeebrewer.cursoaula2.resources;

import com.coffeebrewer.cursoaula2.domain.Product;
import com.coffeebrewer.cursoaula2.dto.ProductDTO;
import com.coffeebrewer.cursoaula2.resources.utils.URL;
import com.coffeebrewer.cursoaula2.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {

    @Autowired
    public ProductService service;

    @RequestMapping(value = "{id}", method= RequestMethod.GET)
    public ResponseEntity<Product> listar(@PathVariable Integer id){
            Product obj = service.find(id);
            return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<ProductDTO>> findPage(
            @RequestParam(value = "nome", defaultValue = "") String nome,
            @RequestParam(value = "categorias", defaultValue = "") String categorias,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        String urlNomeDecoded = URL.decodeParam(nome);
        List<Integer> ids = URL.decodeIntList(categorias);
        Page<Product> list = service.search(urlNomeDecoded,ids, page, linesPerPage, orderBy, direction);
        Page<ProductDTO> listDTO = list.map(obj -> new ProductDTO(obj));
        return ResponseEntity.ok().body(listDTO);

    }

}