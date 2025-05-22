package com.example.proyectoestructuras.controllers;

import com.example.proyectoestructuras.models.Usuario;
import com.example.proyectoestructuras.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Controlador para gestionar las operaciones relacionadas con usuarios
 */
@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * Muestra el formulario de registro de usuario
     */
    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("titulo", "Registro de Usuario");
        model.addAttribute("contenido", "usuarios/registro");
        return "layout/layout";
    }

    /**
     * Procesa el registro de un nuevo usuario
     */
    @PostMapping("/registro")
    public String registrarUsuario(@RequestParam String correo, 
                                  @RequestParam String contraseña,
                                  RedirectAttributes redirectAttributes) {
        boolean registroExitoso = usuarioService.registrarUsuario(correo, contraseña);

        if (registroExitoso) {
            redirectAttributes.addFlashAttribute("mensaje", "Usuario registrado con éxito");
            return "redirect:/tareas/lista";
        } else {
            redirectAttributes.addFlashAttribute("error", "Error al registrar usuario. Verifique que el correo sea institucional y la contraseña tenga al menos 6 caracteres.");
            return "redirect:/usuarios/registro";
        }
    }

    /**
     * Muestra la lista de usuarios registrados
     */
    @GetMapping("/lista")
    public String listarUsuarios(Model model) {
        List<Usuario> usuarios = usuarioService.obtenerTodosLosUsuarios();
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("titulo", "Lista de Usuarios");
        model.addAttribute("contenido", "usuarios/lista");
        return "layout/layout";
    }
}
