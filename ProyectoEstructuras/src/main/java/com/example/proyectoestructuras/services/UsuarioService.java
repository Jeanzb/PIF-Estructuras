package com.example.proyectoestructuras.services;

import com.example.proyectoestructuras.models.Usuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Servicio para gestionar operaciones relacionadas con usuarios
 */
@Service
public class
UsuarioService {
    
    private final List<Usuario> usuarios = new ArrayList<>();
    
    /**
     * Registra un nuevo usuario si el correo es institucional y la contraseña es válida
     * @param correo Correo del usuario
     * @param contraseña Contraseña del usuario
     * @return true si el registro fue exitoso, false en caso contrario
     */
    public boolean registrarUsuario(String correo, String contraseña) {
        // Validar que el correo sea institucional
        if (!correo.endsWith("@poligran.edu.co")) {
            return false;
        }
        
        // Validar que la contraseña sea válida
        if (!esContraseñaValida(contraseña)) {
            return false;
        }
        
        // Verificar que el usuario no exista
        if (buscarUsuarioPorCorreo(correo).isPresent()) {
            return false;
        }
        
        // Crear y guardar el usuario
        Usuario usuario = new Usuario(correo, contraseña);
        usuarios.add(usuario);
        return true;
    }
    
    /**
     * Verifica si una contraseña es válida
     * @param contraseña Contraseña a validar
     * @return true si la contraseña es válida, false en caso contrario
     */
    private boolean esContraseñaValida(String contraseña) {
        return contraseña.length() >= 6;
    }
    
    /**
     * Busca un usuario por su correo
     * @param correo Correo del usuario a buscar
     * @return Optional con el usuario si existe, vacío en caso contrario
     */
    public Optional<Usuario> buscarUsuarioPorCorreo(String correo) {
        return usuarios.stream()
                .filter(u -> u.getCorreo().equals(correo))
                .findFirst();
    }
    
    /**
     * Obtiene todos los usuarios registrados
     * @return Lista de usuarios
     */
    public List<Usuario> obtenerTodosLosUsuarios() {
        return new ArrayList<>(usuarios);
    }
}