package com.coffeebrewer.cursoaula2.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(value = "/home")
public class ApplicationReturn {

    @RequestMapping(method= GET)
    public String listar(){
        return "Essa ";
    }
}