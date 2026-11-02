package gt.edu.url.tallerformularios.controller;

import jakarta.validation.Valid;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import gt.edu.url.tallerformularios.model.Jugador;
import gt.edu.url.tallerformularios.service.JugadorService;

@Controller
public class JugadorController {

    private final JugadorService jugadorService;

    public JugadorController(JugadorService jugadorService) {
        this.jugadorService = jugadorService;
    }

    // Método auxiliar para proveer las listas que usa tu HTML (registro.html)
    private void cargarListas(Model model) {
        model.addAttribute("listaPosiciones", Arrays.asList("Portero", "Defensa", "Mediocampista", "Delantero"));
        model.addAttribute("listaPlataformas", Arrays.asList("PC", "PlayStation", "Xbox", "Nintendo Switch"));
        model.addAttribute("listaNiveles", Arrays.asList("Principiante", "Intermedio", "Avanzado", "Profesional"));
    }

    @GetMapping({"/", "/jugadores"})
    public String listar(Model model) {
        model.addAttribute("jugadores", jugadorService.listarTodos());
        model.addAttribute("total", jugadorService.contar());
        return "lista";
    }

    @GetMapping("/jugadores/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("jugador", new Jugador());
        cargarListas(model);
        return "registro";
    }

    @GetMapping("/jugadores/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("jugador", jugadorService.buscarPorId(id));
        cargarListas(model);
        return "registro";
    }

    @PostMapping("/jugadores")
    public String guardar(@Valid @ModelAttribute("jugador") Jugador jugador,
                          BindingResult resultado, Model model) {
        if (resultado.hasErrors()) {
            cargarListas(model); // Recarga las listas si hay error para que no falle la vista
            return "registro";
        }
        jugadorService.guardar(jugador);
        return "redirect:/jugadores";
    }

    @PostMapping("/jugadores/{id}/eliminar")
    public String eliminar(@PathVariable Long id) {
        jugadorService.eliminar(id);
        return "redirect:/jugadores";
    }
}