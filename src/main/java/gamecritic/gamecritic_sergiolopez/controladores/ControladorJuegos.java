package gamecritic.gamecritic_sergiolopez.controladores;

import gamecritic.gamecritic_sergiolopez.entidades.*;
import gamecritic.gamecritic_sergiolopez.repositorios.ComentarioRepository;
import gamecritic.gamecritic_sergiolopez.repositorios.JuegoRepository;
import gamecritic.gamecritic_sergiolopez.repositorios.VotacionRepository;
import gamecritic.gamecritic_sergiolopez.servicios.JuegoServicio;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequestMapping("/juegos")
@Controller
public class ControladorJuegos {
    @Autowired
    private JuegoRepository juegoRepository;
    @Autowired
    private JuegoServicio juegoServicio;
    @Autowired
    private VotacionRepository votacionRepository;
    @Autowired
    private ComentarioRepository comentarioRepository;

    @GetMapping("/irlistaJuegos")
    public String irPaginaListaJuegos(Model modelo) {
        List<Juego> listaJuegos = juegoServicio.obtenerTodosJuegos();
        modelo.addAttribute("listaJuegos", listaJuegos);
        return "listaJuegos";
    }

    @GetMapping("/irFichaJuego/{id}")
    public String irPaginaJuego(@PathVariable("id") Integer id, Model modelo, HttpSession session) {
        Optional<Juego> optionalJuego = juegoRepository.findById(id);
        if (optionalJuego.isPresent()) {
            Juego juego = optionalJuego.get();
            List<Comentario> listaComentarios = comentarioRepository.findByJuego(juego);
            Collections.sort(listaComentarios, Comparator.comparing(Comentario::getFechaCreacion));

            Map<String, String> iconosPlataformas = new HashMap<>();
            iconosPlataformas.put("PC", "fas fa-desktop");
            iconosPlataformas.put("PlayStation", "fab fa-playstation");
            iconosPlataformas.put("Xbox", "fab fa-xbox");
            iconosPlataformas.put("Nintendo", "fas fa-gamepad");
            modelo.addAttribute("iconosPlataformas", iconosPlataformas);
            Map<String, String> iconosGeneros = new HashMap<>();
            iconosGeneros.put("Acción", "fas fa-fist-raised");
            iconosGeneros.put("Aventura", "fas fa-compass");
            iconosGeneros.put("Rol", "fas fa-dice");
            iconosGeneros.put("Estrategia", "fas fa-chess");
            iconosGeneros.put("Deporte", "fas fa-futbol");
            iconosGeneros.put("Carreras", "fas fa-car");

            Double mediaVotaciones = juego.getNotaMedia();
            String mediaFormateada = String.format("%.1f", mediaVotaciones);
            modelo.addAttribute("mediaVotaciones", mediaFormateada);
            modelo.addAttribute("iconosGeneros", iconosGeneros);
            modelo.addAttribute("juego", juego);
            modelo.addAttribute("listaComentarios", listaComentarios);

            Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");
            if (usuarioLogueado != null) {
                Votacion votacionUsuario = votacionRepository.findByUsuarioAndJuego(usuarioLogueado, juego);
                Comentario comentarioUsuario = comentarioRepository.findByUsuarioAndJuego(usuarioLogueado, juego);
                modelo.addAttribute("votacionUsuario", votacionUsuario);
                modelo.addAttribute("comentarioUsuario", comentarioUsuario);
            }
        }
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


    @GetMapping("/votar")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> votar(@RequestParam("nota") int nota,
                                                     @RequestParam("juegoId") Integer juegoId, HttpSession session) {
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");

        Juego juegoActualizado = juegoServicio.votar(juegoId, nota, usuarioLogueado);

        Map<String, Object> response = new HashMap<>();

        if (juegoActualizado != null) {
            response.put("nuevaNota", nota);
            response.put("nuevaNotaMedia", juegoActualizado.getNotaMedia());
            return ResponseEntity.ok().body(response);
        } else {
            response.put("error", "Error al votar por el juego.");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/eliminarVoto")
    @ResponseBody
    public ResponseEntity<?> eliminarVoto(@RequestParam("juegoId") Integer juegoId, HttpSession session) {
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");

        Juego juegoActualizado = juegoServicio.borrarVoto(juegoId, usuarioLogueado);

        Map<String, Object> response = new HashMap<>();

        if (juegoActualizado != null) {
            response.put("nuevaNotaMedia", juegoActualizado.getNotaMedia());
            return ResponseEntity.ok().body(response);
        } else {
            response.put("error", "Error al votar por el juego.");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/comentar")
    public String comentar(@RequestParam("juegoId") Integer juegoId,
                           @RequestParam("comentario") String contenido,
                           HttpSession session) {
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuarioLogueado == null) {
            return "redirect:/login";
        }

        if (contenido == null || contenido.isEmpty()) {
            return "redirect:/juegos/irFichaJuego/" + juegoId;
        }

        Optional<Juego> optionalJuego = juegoRepository.findById(juegoId);

        if (optionalJuego.isPresent()) {
            Juego juego = optionalJuego.get();

            Comentario comentario = new Comentario();
            comentario.setUsuario(usuarioLogueado);
            comentario.setJuego(juego);
            comentario.setContenido(contenido);
            comentario.setFechaCreacion(new Date());

            comentarioRepository.save(comentario);

            return "redirect:/juegos/irFichaJuego/" + juegoId;
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/borrarComentario/{id}")
    public String borrarComentario(@PathVariable("id") Integer comentarioId, HttpSession session) {
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuarioLogueado == null) {
            return "redirect:/login";
        }

        Optional<Comentario> optionalComentario = comentarioRepository.findById(comentarioId);

        if (optionalComentario.isPresent()) {
            Comentario comentario = optionalComentario.get();

            if (comentario.getUsuario().equals(usuarioLogueado)) {
                comentarioRepository.delete(comentario);
            } else { }

            return "redirect:/irPerfil/" + comentario.getUsuario().getId();
        } else {
            return "redirect:/";
        }
    }


}


