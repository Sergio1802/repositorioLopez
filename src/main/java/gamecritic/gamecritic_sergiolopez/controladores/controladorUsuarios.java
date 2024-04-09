package gamecritic.gamecritic_sergiolopez.controladores;

import gamecritic.gamecritic_sergiolopez.servicios.JuegoServicio;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import gamecritic.gamecritic_sergiolopez.entidades.*;
import gamecritic.gamecritic_sergiolopez.repositorios.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Controller
public class controladorUsuarios {

    private static String RUTA_FOTOS_USUARIOS = "src/main/resources/static/fotoUsuarios/";

    @Autowired
    private JuegoRepository juegoRepository;
    @Autowired
    private JuegoServicio juegoServicio;

    @Autowired
    private GeneroRepository generoRepository;

    @Autowired
    private PlataformaRepository plataformaRepository;
    @Autowired
    private ComentarioRepository comentarioRepository;
    @Autowired
    private ListaRepository listaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private VotacionRepository votacionRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static String rolUsuario;

    @GetMapping("/")
    public String irPaginaInicio(Model modelo) {
        List<Juego> mejoresJuegos = juegoServicio.obtenerMejoresJuegos(6);
        int idGOW = juegoRepository.findByTitulo("God of War Ragnarok").getId();
        modelo.addAttribute("idGOW", idGOW);
        modelo.addAttribute("mejoresJuegos", mejoresJuegos);
        return "index";
    }

    @GetMapping("/irSobreMi")
    public String irPaginaSobreMi(Model modelo) {
        return "sobreMi";
    }

    @GetMapping("/irRegistro")
    public String irPaginaLogin(Model modelo) {
        return "registro";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam("nombreUsuario") String nombreUsuario,
                           @RequestParam("fechaNacimiento") String fechaNacimiento,
                           @RequestParam("email") String correoElectronico,
                           @RequestParam("password") String contraseña,
                           @RequestParam(value = "foto", required = false) MultipartFile foto,
                           Model model) {
        Usuario usuarioExistenteEmail = usuarioRepository.findByEmail(correoElectronico);
        if (usuarioExistenteEmail != null) {
            model.addAttribute("mensajeError", "Ya existe una cuenta con ese email");
            return "registro";
        }

        Usuario usuarioExistenteNombre = usuarioRepository.findByNombreUsuario(nombreUsuario);
        if (usuarioExistenteNombre != null) {
            model.addAttribute("mensajeError", "El nombre de usuario ya está en uso");
            return "registro";
        }

        LocalDate fechaNacimientoLocalDate = LocalDate.parse(fechaNacimiento);
        Date fechaNacimientoDate = Date.from(fechaNacimientoLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        LocalDate fechaHoy = LocalDate.now();
        LocalDate fechaHace14Anios = fechaHoy.minusYears(14);

        if (fechaNacimientoLocalDate.isAfter(fechaHace14Anios)) {
            model.addAttribute("mensajeError", "Debes ser mayor de 14 años para registrarte");
            return "registro";
        }

        String contraseñaEncriptada = passwordEncoder.encode(contraseña);

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombreUsuario(nombreUsuario);
        nuevoUsuario.setFechaNacimiento(fechaNacimientoDate);
        nuevoUsuario.setEmail(correoElectronico);
        nuevoUsuario.setPassword(contraseñaEncriptada);
        nuevoUsuario.setRol("USER");

        if (foto != null && !foto.isEmpty()) {
            try {
                String nombreFoto = foto.getOriginalFilename();
                Files.createDirectories(Paths.get(RUTA_FOTOS_USUARIOS));
                byte[] bytesFoto = foto.getBytes();
                Path rutaFoto = Paths.get(RUTA_FOTOS_USUARIOS + "/" + nombreFoto);
                Files.write(rutaFoto, bytesFoto);
                nuevoUsuario.setFotoPerfil(nombreFoto);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        usuarioRepository.save(nuevoUsuario);
        model.addAttribute("mensajeRegistro", "Te has registrado correctamente");
        return irPaginaInicio(model);
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        Model model, HttpSession session, HttpServletResponse response) {

        Usuario usuario = usuarioRepository.findByEmail(email);

        if (usuario != null && passwordEncoder.matches(password, usuario.getPassword())) {
            session.setAttribute("usuarioLogueado", usuario);
            session.setAttribute("userId", usuario.getId());

            Cookie cookie = new Cookie("usuarioId", String.valueOf(usuario.getId()));
            cookie.setMaxAge(60 * 60 * 24);
            cookie.setPath("/");
            response.addCookie(cookie);

            return irPaginaInicio(model);
        } else {
            model.addAttribute("mensajeError", "Credenciales inválidas. Por favor, inténtalo de nuevo.");
            return irPaginaInicio(model);
        }
    }

    @GetMapping("/irPerfil/{id}")
    public String verPerfilUsuario(@PathVariable("id") Integer id, Model modelo) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);

        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            List<Comentario> comentarios = comentarioRepository.findByUsuarioOrderByFechaCreacionDesc(usuario);
            List<Votacion> votaciones = votacionRepository.findByUsuarioOrderByFechaCreacionDesc(usuario);

            List<Lista> listas = listaRepository.findByUsuario(usuario);

            int totalComentarios = comentarios.size();
            int totalVotaciones = votaciones.size();
            int totalListas = listas.size();

            modelo.addAttribute("usuario", usuario);
            modelo.addAttribute("comentarios", comentarios);
            modelo.addAttribute("votaciones", votaciones);
            modelo.addAttribute("listas", listas);
            modelo.addAttribute("totalComentarios", totalComentarios);
            modelo.addAttribute("totalVotaciones", totalVotaciones);
            modelo.addAttribute("totalListas", totalListas);


        }
        return "perfilUsuario";
    }


}
