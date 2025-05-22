package com.example.proyectoestructuras.controllers;

import com.example.proyectoestructuras.models.Tarea;
import com.example.proyectoestructuras.services.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

/**
 * Controlador para gestionar las operaciones relacionadas con tareas
 */
@Controller
@RequestMapping("/tareas")
public class TareaController {

    private final TareaService tareaService;

    @Autowired
    public TareaController(TareaService tareaService) {
        this.tareaService = tareaService;
    }

    /**
     * Muestra la lista de tareas
     */
    @GetMapping("/lista")
    public String listarTareas(Model model) {
        List<Tarea> tareas = tareaService.obtenerTodasLasTareas();
        model.addAttribute("tareas", tareas);
        model.addAttribute("titulo", "Lista de Tareas");
        model.addAttribute("contenido", "tareas/lista");
        return "layout/layout";
    }

    /**
     * Muestra el formulario para crear una nueva tarea
     */
    @GetMapping("/nueva")
    public String mostrarFormularioNuevaTarea(Model model) {
        model.addAttribute("titulo", "Nueva Tarea");
        model.addAttribute("contenido", "tareas/formulario");
        return "layout/layout";
    }

    /**
     * Procesa la creación de una nueva tarea
     */
    @PostMapping("/nueva")
    public String crearTarea(@RequestParam String nombre,
                            @RequestParam String fechaEntrega,
                            @RequestParam int prioridad,
                            @RequestParam(required = false) String descripcion,
                            RedirectAttributes redirectAttributes) {
        Tarea tarea = tareaService.crearTarea(nombre, fechaEntrega, prioridad, descripcion);
        redirectAttributes.addFlashAttribute("mensaje", "Tarea creada con éxito");
        return "redirect:/tareas/lista";
    }

    /**
     * Muestra las tareas pendientes
     */
    @GetMapping("/pendientes")
    public String listarTareasPendientes(Model model) {
        List<Tarea> tareasPendientes = tareaService.obtenerTareasPendientes();
        model.addAttribute("tareas", tareasPendientes);
        model.addAttribute("titulo", "Tareas Pendientes");
        model.addAttribute("contenido", "tareas/lista");
        return "layout/layout";
    }

    /**
     * Muestra el formulario para categorizar una tarea
     */
    @GetMapping("/categorizar/{nombre}")
    public String mostrarFormularioCategorizar(@PathVariable String nombre, Model model) {
        Optional<Tarea> tareaOpt = tareaService.buscarTareaPorNombre(nombre);
        if (tareaOpt.isPresent()) {
            model.addAttribute("tarea", tareaOpt.get());
            model.addAttribute("titulo", "Categorizar Tarea");
            model.addAttribute("contenido", "tareas/categorizar");
            return "layout/layout";
        }
        return "redirect:/tareas/lista";
    }

    /**
     * Procesa la categorización de una tarea
     */
    @PostMapping("/categorizar")
    public String categorizarTarea(@RequestParam String nombreTarea,
                                  @RequestParam String categoria,
                                  RedirectAttributes redirectAttributes) {
        boolean exito = tareaService.categorizarTarea(nombreTarea, categoria);
        if (exito) {
            redirectAttributes.addFlashAttribute("mensaje", "Tarea categorizada como: " + categoria);
        } else {
            redirectAttributes.addFlashAttribute("error", "No se pudo categorizar la tarea");
        }
        return "redirect:/tareas/lista";
    }

    /**
     * Muestra el progreso de las tareas
     */
    @GetMapping("/progreso")
    public String mostrarProgreso(Model model) {
        TareaService.ProgresoTareas progreso = tareaService.calcularProgreso();
        model.addAttribute("progreso", progreso);
        model.addAttribute("titulo", "Progreso de Tareas");
        model.addAttribute("contenido", "tareas/progreso");
        return "layout/layout";
    }

    /**
     * Marca una tarea como completada
     */
    @PostMapping("/completar")
    public String marcarTareaCompletada(@RequestParam String nombreTarea,
                                       RedirectAttributes redirectAttributes) {
        boolean exito = tareaService.marcarTareaCompletada(nombreTarea);
        if (exito) {
            redirectAttributes.addFlashAttribute("mensaje", "Tarea marcada como completada");
        } else {
            redirectAttributes.addFlashAttribute("error", "No se pudo marcar la tarea como completada");
        }
        return "redirect:/tareas/lista";
    }

    /**
     * Elimina una tarea
     */
    @PostMapping("/eliminar")
    public String eliminarTarea(@RequestParam String nombreTarea,
                               RedirectAttributes redirectAttributes) {
        boolean exito = tareaService.eliminarTarea(nombreTarea);
        if (exito) {
            redirectAttributes.addFlashAttribute("mensaje", "Tarea eliminada con éxito");
        } else {
            redirectAttributes.addFlashAttribute("error", "No se pudo eliminar la tarea");
        }
        return "redirect:/tareas/lista";
    }

    /**
     * Muestra el formulario para editar una tarea
     */
    @GetMapping("/editar/{nombre}")
    public String mostrarFormularioEditar(@PathVariable String nombre, Model model) {
        Optional<Tarea> tareaOpt = tareaService.buscarTareaPorNombre(nombre);
        if (tareaOpt.isPresent()) {
            model.addAttribute("tarea", tareaOpt.get());
            model.addAttribute("titulo", "Editar Tarea");
            model.addAttribute("contenido", "tareas/editar");
            return "layout/layout";
        }
        return "redirect:/tareas/lista";
    }

    /**
     * Procesa la edición de una tarea
     */
    @PostMapping("/editar")
    public String editarTarea(@RequestParam String nombreTarea,
                             @RequestParam(required = false) String nuevoNombre,
                             @RequestParam(required = false) String nuevaFecha,
                             @RequestParam(required = false, defaultValue = "0") int nuevaPrioridad,
                             @RequestParam(required = false) String nuevaDescripcion,
                             RedirectAttributes redirectAttributes) {
        boolean exito = tareaService.editarTarea(nombreTarea, nuevoNombre, nuevaFecha, nuevaPrioridad, nuevaDescripcion);
        if (exito) {
            redirectAttributes.addFlashAttribute("mensaje", "Tarea editada con éxito");
        } else {
            redirectAttributes.addFlashAttribute("error", "No se pudo editar la tarea");
        }
        return "redirect:/tareas/lista";
    }

    /**
     * Muestra las tareas por categoría
     */
    @GetMapping("/categoria/{categoria}")
    public String listarTareasPorCategoria(@PathVariable String categoria, Model model) {
        List<Tarea> tareasPorCategoria = tareaService.obtenerTareasPorCategoria(categoria);
        model.addAttribute("tareas", tareasPorCategoria);
        model.addAttribute("titulo", "Tareas en categoría: " + categoria);
        model.addAttribute("contenido", "tareas/lista");
        return "layout/layout";
    }
}
