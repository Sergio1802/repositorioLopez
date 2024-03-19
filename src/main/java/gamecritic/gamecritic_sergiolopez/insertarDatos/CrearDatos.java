package gamecritic.gamecritic_sergiolopez.insertarDatos;

import gamecritic.gamecritic_sergiolopez.entidades.*;
import gamecritic.gamecritic_sergiolopez.repositorios.*;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CrearDatos implements CommandLineRunner {

    @Autowired
    private JuegoRepository juegoRepository;

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

    @Override
    public void run(String... args) throws Exception {

        Genero accion = new Genero("Acción");
        Genero aventura = new Genero("Aventura");
        Genero rol = new Genero("Rol");
        Genero estrategia = new Genero("Estrategia");
        Genero deporte = new Genero("Deporte");
        Genero carreras = new Genero("Carreras");

        if (generoRepository.findByNombre(accion.getNombre()) == null) {
            generoRepository.save(accion);
        }
        if (generoRepository.findByNombre(aventura.getNombre()) == null) {
            generoRepository.save(aventura);
        }
        if (generoRepository.findByNombre(rol.getNombre()) == null) {
            generoRepository.save(rol);
        }
        if (generoRepository.findByNombre(estrategia.getNombre()) == null) {
            generoRepository.save(estrategia);
        }
        if (generoRepository.findByNombre(deporte.getNombre()) == null) {
            generoRepository.save(deporte);
        }
        if (generoRepository.findByNombre(carreras.getNombre()) == null) {
            generoRepository.save(carreras);
        }

        Plataforma playStation = new Plataforma("PlayStation");
        Plataforma xbox = new Plataforma("Xbox");
        Plataforma pc = new Plataforma("PC");
        Plataforma nintendo = new Plataforma("Nintendo");

        if (plataformaRepository.findByNombre(playStation.getNombre()) == null) {
            plataformaRepository.save(playStation);
        }
        if (plataformaRepository.findByNombre(xbox.getNombre()) == null) {
            plataformaRepository.save(xbox);
        }
        if (plataformaRepository.findByNombre(pc.getNombre()) == null) {
            plataformaRepository.save(pc);
        }
        if (plataformaRepository.findByNombre(nintendo.getNombre()) == null) {
            plataformaRepository.save(nintendo);
        }

        Set<Genero> generosJuego1 = new HashSet<>();
        generosJuego1.add(accion);
        generosJuego1.add(aventura);
        Set<Plataforma> plataformasJuego1 = new HashSet<>();
        plataformasJuego1.add(playStation);
        Juego juego1 = new Juego("The Last of Us Part II", "Descripción de The Last of Us Part II", "tlou2.jpg", "2020-06-19", generosJuego1, plataformasJuego1);
        if (juegoRepository.findByTitulo(juego1.getTitulo()) == null) {
            juegoRepository.save(juego1);
        }

        // Juego 2
        Set<Genero> generosJuego2 = new HashSet<>();
        generosJuego2.add(rol);
        Set<Plataforma> plataformasJuego2 = new HashSet<>();
        plataformasJuego2.add(pc);
        Juego juego2 = new Juego("The Witcher 3: Wild Hunt", "Descripción de The Witcher 3: Wild Hunt", "tw3.jpg", "2015-05-19", generosJuego2, plataformasJuego2);
        if (juegoRepository.findByTitulo(juego2.getTitulo()) == null) {
            juegoRepository.save(juego2);
        }

        // Juego 3
        Set<Genero> generosJuego3 = new HashSet<>();
        generosJuego3.add(accion);
        generosJuego3.add(aventura);
        generosJuego3.add(rol);
        Set<Plataforma> plataformasJuego3 = new HashSet<>();
        plataformasJuego3.add(xbox);
        plataformasJuego3.add(playStation);
        plataformasJuego3.add(pc);
        Juego juego3 = new Juego("Red Dead Redemption 2", "Descripción de Red Dead Redemption 2", "rd2.jpg", "2018-10-26", generosJuego3, plataformasJuego3);
        if (juegoRepository.findByTitulo(juego3.getTitulo()) == null) {
            juegoRepository.save(juego3);
        }

        // Juego 4
        Set<Genero> generosJuego4 = new HashSet<>();
        generosJuego4.add(aventura);
        generosJuego4.add(estrategia);
        Set<Plataforma> plataformasJuego4 = new HashSet<>();
        plataformasJuego4.add(nintendo);
        Juego juego4 = new Juego("The Legend of Zelda: Breath of the Wild", "Descripción de The Legend of Zelda: Breath of the Wild", "zelda.jpg", "2017-03-03", generosJuego4, plataformasJuego4);
        if (juegoRepository.findByTitulo(juego4.getTitulo()) == null) {
            juegoRepository.save(juego4);
        }

        // Juego 5
        Set<Genero> generosJuego5 = new HashSet<>();
        generosJuego5.add(accion);
        generosJuego5.add(rol);
        Set<Plataforma> plataformasJuego5 = new HashSet<>();
        plataformasJuego5.add(playStation);
        plataformasJuego5.add(xbox);
        plataformasJuego5.add(pc);
        Juego juego5 = new Juego("Dark Souls III", "Descripción de Dark Souls III", "ds3.jpg", "2016-04-12", generosJuego5, plataformasJuego5);
        if (juegoRepository.findByTitulo(juego5.getTitulo()) == null) {
            juegoRepository.save(juego5);
        }

        // Juego 6
        Set<Genero> generosJuego6 = new HashSet<>();
        generosJuego6.add(deporte);
        Set<Plataforma> plataformasJuego6 = new HashSet<>();
        plataformasJuego6.add(playStation);
        plataformasJuego6.add(xbox);
        plataformasJuego6.add(pc);
        Juego juego6 = new Juego("FC 24", "Descripción de FC 24", "f24.jpg", "2023-10-01", generosJuego6, plataformasJuego6);
        if (juegoRepository.findByTitulo(juego6.getTitulo()) == null) {
            juegoRepository.save(juego6);
        }

        // Juego 7
        Set<Genero> generosJuego7 = new HashSet<>();
        generosJuego7.add(aventura);
        Set<Plataforma> plataformasJuego7 = new HashSet<>();
        plataformasJuego7.add(nintendo);
        Juego juego7 = new Juego("Super Mario Odyssey", "Descripción de Super Mario Odyssey", "smo.jpg", "2017-10-27", generosJuego7, plataformasJuego7);
        if (juegoRepository.findByTitulo(juego7.getTitulo()) == null) {
            juegoRepository.save(juego7);
        }

        Set<Genero> generosJuego8 = new HashSet<>();
        generosJuego8.add(accion);
        generosJuego8.add(aventura);
        generosJuego8.add(estrategia);
        Set<Plataforma> plataformasJuego8 = new HashSet<>();
        plataformasJuego8.add(xbox);
        plataformasJuego8.add(playStation);
        plataformasJuego8.add(pc);
        Juego juego8 = new Juego("Assassin's Creed Valhalla", "Descripción de Assassin's Creed Valhalla", "acv.jpg", "2020-11-10", generosJuego8, plataformasJuego8);
        if (juegoRepository.findByTitulo(juego8.getTitulo()) == null) {
            juegoRepository.save(juego8);
        }

        // Juego 9
        Set<Genero> generosJuego9 = new HashSet<>();
        generosJuego9.add(carreras);
        Set<Plataforma> plataformasJuego9 = new HashSet<>();
        plataformasJuego9.add(playStation);
        Juego juego9 = new Juego("Gran Turismo 7", "Descripción de Gran Turismo 7", "gt7.jpg", "2022-03-04", generosJuego9, plataformasJuego9);
        if (juegoRepository.findByTitulo(juego9.getTitulo()) == null) {
            juegoRepository.save(juego9);
        }

// Juego 10
        Set<Genero> generosJuego10 = new HashSet<>();
        generosJuego10.add(accion);
        generosJuego10.add(aventura);
        generosJuego10.add(rol);
        Set<Plataforma> plataformasJuego10 = new HashSet<>();
        plataformasJuego10.add(playStation);
        plataformasJuego10.add(xbox);
        plataformasJuego10.add(pc);
        Juego juego10 = new Juego("Cyberpunk 2077", "Descripción de Cyberpunk 2077", "cyber.jpg", "2020-12-10", generosJuego10, plataformasJuego10);
        if (juegoRepository.findByTitulo(juego10.getTitulo()) == null) {
            juegoRepository.save(juego10);
        }


        Usuario usuario1 = new Usuario("usuario1", "Usuario Uno", "usuario1@example.com",
                passwordEncoder.encode("1234"), "1990-01-01",
                "perfil1.jpg", "USER");


        if (usuarioRepository.findByNombreUsuario(usuario1.getNombreUsuario()) == null) {
            usuarioRepository.save(usuario1);
        }

        Usuario usuario2 = new Usuario("usuario2", "Usuario Dos", "usuario2@example.com",
                passwordEncoder.encode("1234"), "1995-02-02",
                "perfil2.jpg", "USER");

        if (usuarioRepository.findByNombreUsuario(usuario2.getNombreUsuario()) == null) {
            usuarioRepository.save(usuario2);
        }

        Usuario usuario3 = new Usuario("usuario3", "Usuario Tres", "usuario3@example.com",
                passwordEncoder.encode("admin"), "1985-03-03",
                "perfil3.jpg", "ADMIN");

        if (usuarioRepository.findByNombreUsuario(usuario3.getNombreUsuario()) == null) {
            usuarioRepository.save(usuario3);
        }

        Usuario usuario4 = new Usuario("usuario4", "Usuario Cuatro", "usuario4@example.com",
                passwordEncoder.encode("admin"), "1988-04-04",
                "perfil4.jpg", "USER");

        if (usuarioRepository.findByNombreUsuario(usuario4.getNombreUsuario()) == null) {
            usuarioRepository.save(usuario4);
        }

        List<Votacion> votaciones = votacionRepository.findAll();
        if (votaciones.size() == 0) {
            List<Juego> juegos = new ArrayList<>();
            juegos.add(juego1);
            juegos.add(juego2);
            juegos.add(juego3);
            juegos.add(juego4);
            juegos.add(juego5);
            juegos.add(juego6);
            juegos.add(juego7);
            juegos.add(juego8);
            juegos.add(juego9);
            juegos.add(juego10);

            for (Juego juego : juegos) {

                Votacion votacionUsuario1 = new Votacion(usuario1, juego, (int) (Math.random() * 10) + 1);
                Votacion votacionUsuario2 = new Votacion(usuario2, juego, (int) (Math.random() * 10) + 1);
                Votacion votacionUsuario3 = new Votacion(usuario3, juego, (int) (Math.random() * 10) + 1);
                Votacion votacionUsuario4 = new Votacion(usuario4, juego, (int) (Math.random() * 10) + 1);

                // Verificar si la votación ya existe antes de guardarla
                if (votacionRepository.findByUsuarioAndJuego(usuario1, juego) == null) {
                    votacionRepository.save(votacionUsuario1);
                }
                if (votacionRepository.findByUsuarioAndJuego(usuario2, juego) == null) {
                    votacionRepository.save(votacionUsuario2);
                }
                if (votacionRepository.findByUsuarioAndJuego(usuario3, juego) == null) {
                    votacionRepository.save(votacionUsuario3);
                }
                if (votacionRepository.findByUsuarioAndJuego(usuario4, juego) == null) {
                    votacionRepository.save(votacionUsuario4);
                }
            }
        }
    }


}
