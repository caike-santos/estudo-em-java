package com.faculdade.teste.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/index")
public class TestantoController {
    
    @GetMapping()
    public String getRotaDefault() {
        return "Bem vindo ";
    }
    
}
