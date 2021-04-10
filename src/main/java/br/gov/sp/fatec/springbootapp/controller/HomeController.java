package br.gov.sp.fatec.springbootapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;


@RestController
@CrossOrigin
public class HomeController {
    
    @GetMapping
    public String welcome() {
        return "Olá...";
    }
}