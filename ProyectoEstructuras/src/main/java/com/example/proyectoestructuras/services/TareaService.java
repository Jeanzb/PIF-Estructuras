package com.example.proyectoestructuras.services;

import com.example.proyectoestructuras.models.Tarea;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Servicio para gestionar operaciones relacionadas con tareas
 */
@Service
public class TareaService {

    private final List<Tarea> tareas = new ArrayList<>();

    /**
     * Crea una nueva tarea
     * @param nombre Nombre de la tarea
     * @param fechaEntrega Fecha de entrega
     * @param prioridad Prioridad (1: Alta, 2: Media, 3: Baja)
     * @param descripcion Descripción de la tarea
     * @return La tarea creada
     */
    public Tarea crearTarea(String nombre, String fechaEntrega, int prioridad, String descripcion) {
        Tarea tarea = new Tarea(nombre, fechaEntrega, prioridad, descripcion);
        tareas.add(tarea);
        return tarea;
    }

    /**
     * Obtiene todas las tareas ordenadas por prioridad (alta a baja)
     * @return Lista de todas las tareas ordenadas por prioridad
     */
    public List<Tarea> obtenerTodasLasTareas() {
        List<Tarea> tareasOrdenadas = new ArrayList<>(tareas);
        tareasOrdenadas.sort((t1, t2) -> Integer.compare(t1.getPrioridad(), t2.getPrioridad()));
        return tareasOrdenadas;
    }

    /**
     * Obtiene las tareas pendientes ordenadas por prioridad (alta a baja)
     * @return Lista de tareas pendientes ordenadas por prioridad
     */
    public List<Tarea> obtenerTareasPendientes() {
        return tareas.stream()
                .filter(t -> t.getEstado().equals("Pendiente"))
                .sorted((t1, t2) -> Integer.compare(t1.getPrioridad(), t2.getPrioridad()))
                .collect(Collectors.toList());
    }

    /**
     * Busca una tarea por su nombre
     * @param nombre Nombre de la tarea a buscar
     * @return Optional con la tarea si existe, vacío en caso contrario
     */
    public Optional<Tarea> buscarTareaPorNombre(String nombre) {
        return tareas.stream()
                .filter(t -> t.getNombre().equalsIgnoreCase(nombre))
                .findFirst();
    }

    /**
     * Categoriza una tarea
     * @param nombreTarea Nombre de la tarea
     * @param categoria Nueva categoría
     * @return true si la tarea fue categorizada, false en caso contrario
     */
    public boolean categorizarTarea(String nombreTarea, String categoria) {
        Optional<Tarea> tareaOpt = buscarTareaPorNombre(nombreTarea);
        if (tareaOpt.isPresent()) {
            Tarea tarea = tareaOpt.get();
            tarea.setCategoria(categoria);
            return true;
        }
        return false;
    }

    /**
     * Marca una tarea como completada
     * @param nombreTarea Nombre de la tarea
     * @return true si la tarea fue marcada como completada, false en caso contrario
     */
    public boolean marcarTareaCompletada(String nombreTarea) {
        Optional<Tarea> tareaOpt = buscarTareaPorNombre(nombreTarea);
        if (tareaOpt.isPresent()) {
            Tarea tarea = tareaOpt.get();
            tarea.setEstado("Completada");
            return true;
        }
        return false;
    }

    /**
     * Elimina una tarea
     * @param nombreTarea Nombre de la tarea a eliminar
     * @return true si la tarea fue eliminada, false en caso contrario
     */
    public boolean eliminarTarea(String nombreTarea) {
        Optional<Tarea> tareaOpt = buscarTareaPorNombre(nombreTarea);
        if (tareaOpt.isPresent()) {
            tareas.remove(tareaOpt.get());
            return true;
        }
        return false;
    }

    /**
     * Edita una tarea
     * @param nombreTarea Nombre de la tarea a editar
     * @param nuevoNombre Nuevo nombre (null si no se cambia)
     * @param nuevaFecha Nueva fecha (null si no se cambia)
     * @param nuevaPrioridad Nueva prioridad (0 si no se cambia)
     * @param nuevaDescripcion Nueva descripción (null si no se cambia)
     * @return true si la tarea fue editada, false en caso contrario
     */
    public boolean editarTarea(String nombreTarea, String nuevoNombre, String nuevaFecha, 
                              int nuevaPrioridad, String nuevaDescripcion) {
        Optional<Tarea> tareaOpt = buscarTareaPorNombre(nombreTarea);
        if (tareaOpt.isPresent()) {
            Tarea tarea = tareaOpt.get();

            if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
                tarea.setNombre(nuevoNombre);
            }

            if (nuevaFecha != null && !nuevaFecha.isEmpty()) {
                tarea.setFechaEntrega(nuevaFecha);
            }

            if (nuevaPrioridad > 0) {
                tarea.setPrioridad(nuevaPrioridad);
            }

            if (nuevaDescripcion != null) {
                tarea.setDescripcion(nuevaDescripcion);
            }

            return true;
        }
        return false;
    }

    /**
     * Obtiene tareas por categoría ordenadas por prioridad (alta a baja)
     * @param categoria Categoría a filtrar
     * @return Lista de tareas de la categoría especificada ordenadas por prioridad
     */
    public List<Tarea> obtenerTareasPorCategoria(String categoria) {
        return tareas.stream()
                .filter(t -> t.getCategoria().equalsIgnoreCase(categoria))
                .sorted((t1, t2) -> Integer.compare(t1.getPrioridad(), t2.getPrioridad()))
                .collect(Collectors.toList());
    }

    /**
     * Calcula el progreso de las tareas
     * @return Objeto con información del progreso
     */
    public ProgresoTareas calcularProgreso() {
        int totalTareas = tareas.size();
        long completadas = tareas.stream()
                .filter(t -> t.getEstado().equals("Completada"))
                .count();

        double porcentaje = (totalTareas > 0) ? ((double) completadas / totalTareas) * 100 : 0;

        return new ProgresoTareas(totalTareas, (int) completadas, porcentaje);
    }

    /**
     * Clase interna para representar el progreso de las tareas
     */
    public static class ProgresoTareas {
        private final int totalTareas;
        private final int tareasCompletadas;
        private final double porcentajeCompletado;

        public ProgresoTareas(int totalTareas, int tareasCompletadas, double porcentajeCompletado) {
            this.totalTareas = totalTareas;
            this.tareasCompletadas = tareasCompletadas;
            this.porcentajeCompletado = porcentajeCompletado;
        }

        public int getTotalTareas() {
            return totalTareas;
        }

        public int getTareasCompletadas() {
            return tareasCompletadas;
        }

        public double getPorcentajeCompletado() {
            return porcentajeCompletado;
        }
    }
}
