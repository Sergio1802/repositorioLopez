package gamecritic.gamecritic_sergiolopez.controladores;

import gamecritic.gamecritic_sergiolopez.entidades.Juego;
import gamecritic.gamecritic_sergiolopez.repositorios.JuegoRepository;
import gamecritic.gamecritic_sergiolopez.servicios.JuegoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/filtrar-juegos")
    public ResponseEntity<List<Juego>> filtrarJuegos(@RequestBody Map<String, Object> filtros) {
        String fechaDesde = (String) filtros.get("fechaDesde");
        String fechaHasta = (String) filtros.get("fechaHasta");
        List<String> plataformas = (List<String>) filtros.get("plataformas");
        List<String> generos = (List<String>) filtros.get("generos");

        // Luego puedes pasar estos filtros al servicio para obtener los juegos filtrados
        List<Juego> juegosFiltrados = juegoServicio.filtrarJuegos(fechaDesde,fechaHasta,plataformas,generos);
        return new ResponseEntity<>(juegosFiltrados, HttpStatus.OK);
    }
}
