package gamecritic.gamecritic_sergiolopez.controladores;

import gamecritic.gamecritic_sergiolopez.servicios.JuegoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import gamecritic.gamecritic_sergiolopez.entidades.*;
import gamecritic.gamecritic_sergiolopez.repositorios.*;
import java.util.*;

@Controller
public class controladorUsuarios {

    @Autowired
    private JuegoRepository juegoRepository;
    @Autowired
    private JuegoServicio juegoServicio;

    @Autowired
    private GeneroRepository generoRepository;

    @Autowired
    private PlataformaRepository plataformaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private VotacionRepository votacionRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String irPaginaInicio(Model modelo) {
        List<Juego> mejoresJuegos = juegoServicio.obtenerMejoresJuegos(6);

        // Agregar los juegos al modelo
        modelo.addAttribute("mejoresJuegos", mejoresJuegos);
        return "index";
    }
}
