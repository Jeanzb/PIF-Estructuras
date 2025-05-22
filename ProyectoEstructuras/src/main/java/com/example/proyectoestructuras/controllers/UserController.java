package com.example.proyectoestructuras.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {


    @GetMapping("/view")
    public String index(Model model) {
        model.addAttribute("title", "Hola mundo Spring");
        model.addAttribute("message", "Einyel sapa hpta");
        return "index";
    }
}
