package com.apitopi.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloControler {

    @GetMapping("/")
    public String hello() {
        return ("<h1>Deu erro pow, confia</h1>");
    }
}
