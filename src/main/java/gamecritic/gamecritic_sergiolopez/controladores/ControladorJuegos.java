package gamecritic.gamecritic_sergiolopez.controladores;

import gamecritic.gamecritic_sergiolopez.entidades.Juego;
import gamecritic.gamecritic_sergiolopez.entidades.Plataforma;
import gamecritic.gamecritic_sergiolopez.repositorios.JuegoRepository;
import gamecritic.gamecritic_sergiolopez.servicios.JuegoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/juegos")
@Controller
public class ControladorJuegos {
    @Autowired
    private JuegoRepository juegoRepository;
    @Autowired
    private JuegoServicio juegoServicio;

    @GetMapping("/irlistaJuegos")
    public String irPaginaListaJuegos(Model modelo) {
        List<Juego> listaJuegos = juegoServicio.obtenerTodosJuegos();
        modelo.addAttribute("listaJuegos", listaJuegos);
        return "listaJuegos";
    }

    @GetMapping("/irFichaJuego")
    public String irPaginaJuego(Model modelo) {
        return "fichaJuego";
    }

    @GetMapping("/filtrar")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> filtrarJuegos(@RequestParam(value = "fechaDesde") String fechaDesde,
                                                             @RequestParam(value = "fechaHasta") String fechaHasta,
                                                             @RequestParam(value = "plataformas") List<String> plataformas,
                                                             @RequestParam(value = "generos") List<String> generos) {
        List<Juego> juegosFiltrados = juegoServicio.filtrarJuegos(fechaDesde, fechaHasta, plataformas, generos);
        Map<String, Object> response = new HashMap<>();
        response.put("resultados", juegosFiltrados);
        return ResponseEntity.ok().body(response);

    }
}


