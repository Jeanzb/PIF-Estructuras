package com.example.proyectoestructuras.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controlador principal para la página de inicio
 */
@Controller
public class HomeController {

    /**
     * Página de inicio
     */
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("titulo", "Gestor de Tareas - Inicio");
        model.addAttribute("contenido", "index");
        return "layout/layout";
    }

    /**
     * Redirección a la página de tareas
     */
    @GetMapping("/tareas")
    public String redirectToTareas() {
        return "redirect:/tareas/lista";
    }

}
