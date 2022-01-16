package com.coffeebrewer.cursoaula2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@SpringBootApplication
@RestController
public class Cursoaula2Application {

    public static void main(String[] args) {
        SpringApplication.run(Cursoaula2Application.class, args);
    }

    @RequestMapping("/")
    String home()
    {
        return "Hello World!";
    }
}
