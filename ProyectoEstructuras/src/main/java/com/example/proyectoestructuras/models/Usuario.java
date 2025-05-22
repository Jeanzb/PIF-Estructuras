package com.example.proyectoestructuras.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa a un usuario del sistema de gestión de tareas.
 */
public class Usuario {
    private String correo;
    private String contraseña;
    private List<Tarea> tareas;

    /**
     * Constructor por defecto
     */
    public Usuario() {
        this.tareas = new ArrayList<>();
    }

    /**
     * Constructor con parámetros
     * @param correo Correo electrónico del usuario
     * @param contraseña Contraseña del usuario
     */
    public Usuario(String correo, String contraseña) {
        this.correo = correo;
        this.contraseña = contraseña;
        this.tareas = new ArrayList<>();
    }

    // Getters y setters
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public List<Tarea> getTareas() {
        return tareas;
    }

    public void setTareas(List<Tarea> tareas) {
        this.tareas = tareas;
    }

    /**
     * Añade una tarea a la lista de tareas del usuario
     * @param tarea Tarea a añadir
     */
    public void addTarea(Tarea tarea) {
        this.tareas.add(tarea);
    }
}