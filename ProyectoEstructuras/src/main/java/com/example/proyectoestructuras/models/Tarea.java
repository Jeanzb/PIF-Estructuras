package com.example.proyectoestructuras.models;

/**
 * Clase que representa una tarea en el sistema de gestión de tareas.
 */
public class Tarea {
    private String nombre;
    private String fechaEntrega;
    private int prioridad;
    private String descripcion;
    private String categoria;
    private String estado;

    /**
     * Constructor por defecto
     */
    public Tarea() {
        this.categoria = "Sin categoría";
        this.estado = "Pendiente";
    }

    /**
     * Constructor con parámetros
     * @param nombre Nombre de la tarea
     * @param fechaEntrega Fecha de entrega de la tarea
     * @param prioridad Prioridad de la tarea (1: Alta, 2: Media, 3: Baja)
     * @param descripcion Descripción de la tarea
     */
    public Tarea(String nombre, String fechaEntrega, int prioridad, String descripcion) {
        this.nombre = nombre;
        this.fechaEntrega = fechaEntrega;
        this.prioridad = prioridad;
        this.descripcion = descripcion;
        this.categoria = "Sin categoría";
        this.estado = "Pendiente";
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Obtiene la prioridad en formato texto
     * @return Texto representando la prioridad
     */
    public String obtenerPrioridadTexto() {
        return switch (prioridad) {
            case 1 -> "Alta";
            case 2 -> "Media";
            case 3 -> "Baja";
            default -> "Desconocida";
        };
    }
}