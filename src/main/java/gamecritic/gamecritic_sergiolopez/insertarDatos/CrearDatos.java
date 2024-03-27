package gamecritic.gamecritic_sergiolopez.insertarDatos;

import gamecritic.gamecritic_sergiolopez.entidades.*;
import gamecritic.gamecritic_sergiolopez.repositorios.*;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

        SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MM-yyyy");
        Date fechaLanzamiento1 = null;
        try {
            fechaLanzamiento1 = formatter1.parse("19-06-2020");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Set<Genero> generosJuego1 = new HashSet<>();
        generosJuego1.add(accion);
        generosJuego1.add(aventura);
        Set<Plataforma> plataformasJuego1 = new HashSet<>();
        plataformasJuego1.add(playStation);
        Juego juego1 = new Juego("The Last of Us Part II", "The Last of Us Part II es un videojuego de terror, acción y aventuras de 2020 desarrollado por Naughty Dog y publicado por Sony Interactive Entertainment para PlayStation", "tlou2.jpg", fechaLanzamiento1, generosJuego1, plataformasJuego1);
        if (juegoRepository.findByTitulo(juego1.getTitulo()) == null) {
            juegoRepository.save(juego1);
        }

        SimpleDateFormat formatter2 = new SimpleDateFormat("dd-MM-yyyy");
        Date fechaLanzamiento2 = null;
        try {
            fechaLanzamiento2 = formatter2.parse("19-05-2015");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Set<Genero> generosJuego2 = new HashSet<>();
        generosJuego2.add(rol);
        Set<Plataforma> plataformasJuego2 = new HashSet<>();
        plataformasJuego2.add(pc);
        plataformasJuego2.add(xbox);
        plataformasJuego2.add(playStation);
        Juego juego2 = new Juego("The Witcher 3: Wild Hunt", "The Witcher 3: Wild Hunt es un juego de rol de acción con una perspectiva en tercera persona. Los jugadores controlan a Geralt de Rivia, un cazador de monstruos.", "tw3.jpg", fechaLanzamiento2, generosJuego2, plataformasJuego2);
        if (juegoRepository.findByTitulo(juego2.getTitulo()) == null) {
            juegoRepository.save(juego2);
        }

        SimpleDateFormat formatter3 = new SimpleDateFormat("dd-MM-yyyy");
        Date fechaLanzamiento3 = null;
        try {
            fechaLanzamiento3 = formatter3.parse("26-10-2018");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Set<Genero> generosJuego3 = new HashSet<>();
        generosJuego3.add(accion);
        generosJuego3.add(aventura);
        generosJuego3.add(rol);
        Set<Plataforma> plataformasJuego3 = new HashSet<>();
        plataformasJuego3.add(xbox);
        plataformasJuego3.add(playStation);
        plataformasJuego3.add(pc);
        Juego juego3 = new Juego("Red Dead Redemption 2", "Red Dead Redemption 2 es un videojuego de acción-aventura de mundo abierto desarrollado y publicado por Rockstar Games. El juego es la tercera entrada de la serie Red Dead y una precuela del juego de 2010 Red Dead Redemption.", "rd2.jpg", fechaLanzamiento3, generosJuego3, plataformasJuego3);
        if (juegoRepository.findByTitulo(juego3.getTitulo()) == null) {
            juegoRepository.save(juego3);
        }

        // Juego 4
        SimpleDateFormat formatter4 = new SimpleDateFormat("dd-MM-yyyy");
        Date fechaLanzamiento4 = null;
        try {
            fechaLanzamiento4 = formatter4.parse("03-03-2017");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Set<Genero> generosJuego4 = new HashSet<>();
        generosJuego4.add(aventura);
        generosJuego4.add(estrategia);
        Set<Plataforma> plataformasJuego4 = new HashSet<>();
        plataformasJuego4.add(nintendo);
        Juego juego4 = new Juego("The Legend of Zelda: Breath of the Wild", "The Legend of Zelda: Breath of the Wild es un videojuego de acción-aventura de 2017 de la serie The Legend of Zelda, desarrollado por la filial Nintendo EPD en colaboración con Monolith Soft y publicado por Nintendo para las consolas Nintendo.", "zelda.jpg", fechaLanzamiento4, generosJuego4, plataformasJuego4);
        if (juegoRepository.findByTitulo(juego4.getTitulo()) == null) {
            juegoRepository.save(juego4);
        }

        // Juego 5
        SimpleDateFormat formatter5 = new SimpleDateFormat("dd-MM-yyyy");
        Date fechaLanzamiento5 = null;
        try {
            fechaLanzamiento5 = formatter5.parse("12-04-2016");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Set<Genero> generosJuego5 = new HashSet<>();
        generosJuego5.add(accion);
        generosJuego5.add(rol);
        Set<Plataforma> plataformasJuego5 = new HashSet<>();
        plataformasJuego5.add(playStation);
        plataformasJuego5.add(xbox);
        plataformasJuego5.add(pc);
        Juego juego5 = new Juego("Dark Souls III", "Dark Souls III es un videojuego de rol de acción desarrollado por FromSoftware y publicado por Bandai Namco Entertainment para PlayStation, Xbox y PC. Es la tercera entrega en la saga Souls, Dark Souls III fue lanzado en Japón en marzo de 2016, y de manera mundial en abril del mismo año", "ds3.jpg", fechaLanzamiento5, generosJuego5, plataformasJuego5);
        if (juegoRepository.findByTitulo(juego5.getTitulo()) == null) {
            juegoRepository.save(juego5);
        }

        // Juego 6
        SimpleDateFormat formatter6 = new SimpleDateFormat("dd-MM-yyyy");
        Date fechaLanzamiento6 = null;
        try {
            fechaLanzamiento6 = formatter6.parse("01-10-2023");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Set<Genero> generosJuego6 = new HashSet<>();
        generosJuego6.add(deporte);
        Set<Plataforma> plataformasJuego6 = new HashSet<>();
        plataformasJuego6.add(playStation);
        plataformasJuego6.add(xbox);
        plataformasJuego6.add(pc);
        Juego juego6 = new Juego("FC 24", "EA Sports FC 24 es un videojuego de fútbol, desarrollado por EA, y publicado por EA Sports. Este juego marca la primera entrega de la serie EA Sports FC, tras la conclusión de la asociación de EA con FIFA.", "f24.jpg", fechaLanzamiento6, generosJuego6, plataformasJuego6);
        if (juegoRepository.findByTitulo(juego6.getTitulo()) == null) {
            juegoRepository.save(juego6);
        }


        SimpleDateFormat formatter7 = new SimpleDateFormat("dd-MM-yyyy");
        Date fechaLanzamiento7 = null;
        try {
            fechaLanzamiento7 = formatter7.parse("27-10-2017");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Set<Genero> generosJuego7 = new HashSet<>();
        generosJuego7.add(aventura);
        Set<Plataforma> plataformasJuego7 = new HashSet<>();
        plataformasJuego7.add(nintendo);
        Juego juego7 = new Juego("Super Mario Odyssey", "Super Mario Odyssey es un videojuego de la serie Mario desarrollado por Nintendo EPD y distribuido por Nintendo para la Nintendo Switch en 2017. Es un videojuego de plataformas en 3D con una jugabilidad similar a la de Super Mario 64 y Super Mario Sunshine, que te permite explorar libremente los mundos abiertos", "smo.jpg", fechaLanzamiento7, generosJuego7, plataformasJuego7);
        if (juegoRepository.findByTitulo(juego7.getTitulo()) == null) {
            juegoRepository.save(juego7);
        }

        // Juego 8
        SimpleDateFormat formatter8 = new SimpleDateFormat("dd-MM-yyyy");
        Date fechaLanzamiento8 = null;
        try {
            fechaLanzamiento8 = formatter8.parse("10-11-2020");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Set<Genero> generosJuego8 = new HashSet<>();
        generosJuego8.add(accion);
        generosJuego8.add(aventura);
        generosJuego8.add(estrategia);
        Set<Plataforma> plataformasJuego8 = new HashSet<>();
        plataformasJuego8.add(xbox);
        plataformasJuego8.add(playStation);
        plataformasJuego8.add(pc);
        Juego juego8 = new Juego("Assassin's Creed Valhalla", "Assassin's Creed Valhalla es un videojuego de rol de acción desarrollado por Ubisoft Montreal y publicado por Ubisoft. Es el decimosegundo en importancia y el vigesimosegundo lanzado dentro de la saga de Assassin Creed, y sucesor al juego del 2018 Assassin's Creed Odyssey.", "acv.jpg", fechaLanzamiento8, generosJuego8, plataformasJuego8);
        if (juegoRepository.findByTitulo(juego8.getTitulo()) == null) {
            juegoRepository.save(juego8);
        }

        // Juego 9
        SimpleDateFormat formatter9 = new SimpleDateFormat("dd-MM-yyyy");
        Date fechaLanzamiento9 = null;
        try {
            fechaLanzamiento9 = formatter9.parse("04-03-2022");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Set<Genero> generosJuego9 = new HashSet<>();
        generosJuego9.add(carreras);
        Set<Plataforma> plataformasJuego9 = new HashSet<>();
        plataformasJuego9.add(playStation);
        Juego juego9 = new Juego("Gran Turismo 7", "Gran Turismo 7 es un videojuego de simulación de carreras de 2022 desarrollado por Polyphony Digital y publicado por Sony Interactive Entertainment. El juego es la octava entrega principal de la serie Gran Turismo.", "gt7.jpg", fechaLanzamiento9, generosJuego9, plataformasJuego9);
        if (juegoRepository.findByTitulo(juego9.getTitulo()) == null) {
            juegoRepository.save(juego9);
        }

        // Juego 10
        SimpleDateFormat formatter10 = new SimpleDateFormat("dd-MM-yyyy");
        Date fechaLanzamiento10 = null;
        try {
            fechaLanzamiento10 = formatter10.parse("10-12-2020");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Set<Genero> generosJuego10 = new HashSet<>();
        generosJuego10.add(accion);
        generosJuego10.add(aventura);
        generosJuego10.add(rol);
        Set<Plataforma> plataformasJuego10 = new HashSet<>();
        plataformasJuego10.add(playStation);
        plataformasJuego10.add(xbox);
        plataformasJuego10.add(pc);
        Juego juego10 = new Juego("Cyberpunk 2077", "Cyberpunk 2077 es un videojuego de rol de acción de disparos en primera persona desarrollado por CD Projekt RED y publicado por CD Projekt que se lanzó para Microsoft Windows, PlayStation 4 y Xbox One el 10 de diciembre de 2020.", "cyber.jpg", fechaLanzamiento10, generosJuego10, plataformasJuego10);
        if (juegoRepository.findByTitulo(juego10.getTitulo()) == null) {
            juegoRepository.save(juego10);
        }

        SimpleDateFormat formatterEldenRing = new SimpleDateFormat("dd-MM-yyyy");
        Date fechaLanzamientoEldenRing = null;
        try {
            fechaLanzamientoEldenRing = formatterEldenRing.parse("25-02-2022");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Set<Genero> generosEldenRing = new HashSet<>();
        generosEldenRing.add(accion);
        generosEldenRing.add(aventura);
        Set<Plataforma> plataformasEldenRing = new HashSet<>();
        plataformasEldenRing.add(playStation);
        plataformasEldenRing.add(xbox);
        plataformasEldenRing.add(pc);
        Juego juego11 = new Juego("Elden Ring", "Descubre un vasto mundo de fantasía lleno de misterios y desafíos épicos en esta obra maestra de FromSoftware.", "elden.jpg", fechaLanzamientoEldenRing, generosEldenRing, plataformasEldenRing);
        if (juegoRepository.findByTitulo(juego11.getTitulo()) == null) {
            juegoRepository.save(juego11);
        }

// Resident Evil 2 Remake
        SimpleDateFormat formatterRE2 = new SimpleDateFormat("dd-MM-yyyy");
        Date fechaLanzamientoRE2 = null;
        try {
            fechaLanzamientoRE2 = formatterRE2.parse("25-01-2019");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Set<Genero> generosRE2 = new HashSet<>();
        generosRE2.add(accion);
        Set<Plataforma> plataformasRE2 = new HashSet<>();
        plataformasRE2.add(playStation);
        plataformasRE2.add(xbox);
        plataformasRE2.add(pc);
        Juego juego12 = new Juego("Resident Evil 2 Remake", "Revive el horror clásico en esta recreación impresionante del icónico juego de survival horror, con gráficos impresionantes y una atmósfera aterradora.", "re2.jpg", fechaLanzamientoRE2, generosRE2, plataformasRE2);
        if (juegoRepository.findByTitulo(juego12.getTitulo()) == null) {
            juegoRepository.save(juego12);
        }

// God of War Ragnarok
        SimpleDateFormat formatterGodOfWar = new SimpleDateFormat("dd-MM-yyyy");
        Date fechaLanzamientoGodOfWar = null;
        try {
            fechaLanzamientoGodOfWar = formatterGodOfWar.parse("30-09-2022");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Set<Genero> generosGodOfWar = new HashSet<>();
        generosGodOfWar.add(accion);
        generosGodOfWar.add(aventura);
        Set<Plataforma> plataformasGodOfWar = new HashSet<>();
        plataformasGodOfWar.add(playStation);
        Juego juego13 = new Juego("God of War Ragnarok", "Únete a Kratos en otra épica aventura llena de acción y mitología nórdica en este esperado título de la franquicia God of War.", "gow.jpg", fechaLanzamientoGodOfWar, generosGodOfWar, plataformasGodOfWar);
        if (juegoRepository.findByTitulo(juego13.getTitulo()) == null) {
            juegoRepository.save(juego13);
        }

// Uncharted 4
        SimpleDateFormat formatterUncharted4 = new SimpleDateFormat("dd-MM-yyyy");
        Date fechaLanzamientoUncharted4 = null;
        try {
            fechaLanzamientoUncharted4 = formatterUncharted4.parse("10-05-2016");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Set<Genero> generosUncharted4 = new HashSet<>();
        generosUncharted4.add(accion);
        generosUncharted4.add(aventura);
        Set<Plataforma> plataformasUncharted4 = new HashSet<>();
        plataformasUncharted4.add(playStation);
        Juego juego14 = new Juego("Uncharted 4: A Thief's End", "Únete a Nathan Drake en su última aventura en busca de tesoros perdidos y misterios ancestrales en este emocionante juego de acción y aventuras.", "u4.jpg", fechaLanzamientoUncharted4, generosUncharted4, plataformasUncharted4);
        if (juegoRepository.findByTitulo(juego14.getTitulo()) == null) {
            juegoRepository.save(juego14);
        }

// Hollow Knight
        SimpleDateFormat formatterHollowKnight = new SimpleDateFormat("dd-MM-yyyy");
        Date fechaLanzamientoHollowKnight = null;
        try {
            fechaLanzamientoHollowKnight = formatterHollowKnight.parse("24-02-2017");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Set<Genero> generosHollowKnight = new HashSet<>();
        generosHollowKnight.add(accion);
        generosHollowKnight.add(aventura);
        Set<Plataforma> plataformasHollowKnight = new HashSet<>();
        plataformasHollowKnight.add(pc);
        plataformasHollowKnight.add(playStation);
        plataformasHollowKnight.add(xbox);
        Juego juego15 = new Juego("Hollow Knight", "Embárcate en una emocionante aventura en un oscuro mundo subterráneo lleno de misterios y criaturas fascinantes en este cautivador juego de acción y plataformas.", "hk.jpg", fechaLanzamientoHollowKnight, generosHollowKnight, plataformasHollowKnight);
        if (juegoRepository.findByTitulo(juego15.getTitulo()) == null) {
            juegoRepository.save(juego15);
        }

        SimpleDateFormat formatter11 = new SimpleDateFormat("dd-MM-yyyy");
        Date fechaNacimiento1 = null;
        try {
            fechaNacimiento1 = formatter11.parse("01-01-1990");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Usuario usuario1 = new Usuario("usuario1", "usuario1@example.com",
                passwordEncoder.encode("1234"), fechaNacimiento1,
                "perfil1.jpg", "USER");

        if (usuarioRepository.findByNombreUsuario(usuario1.getNombreUsuario()) == null) {
            usuarioRepository.save(usuario1);
        }

        // Usuario 2
        SimpleDateFormat formatter21 = new SimpleDateFormat("dd-MM-yyyy");
        Date fechaNacimiento2 = null;
        try {
            fechaNacimiento2 = formatter21.parse("02-02-1995");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Usuario usuario2 = new Usuario("usuario2", "usuario2@example.com",
                passwordEncoder.encode("1234"), fechaNacimiento2,
                "perfil2.jpg", "USER");

        if (usuarioRepository.findByNombreUsuario(usuario2.getNombreUsuario()) == null) {
            usuarioRepository.save(usuario2);
        }

        // Usuario 3
        SimpleDateFormat formatter31 = new SimpleDateFormat("dd-MM-yyyy");
        Date fechaNacimiento3 = null;
        try {
            fechaNacimiento3 = formatter31.parse("03-03-1985");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Usuario usuario3 = new Usuario("admin", "admin@gmail.com",
                passwordEncoder.encode("admin"), fechaNacimiento3,
                "perfil3.jpg", "ADMIN");

        if (usuarioRepository.findByNombreUsuario(usuario3.getNombreUsuario()) == null) {
            usuarioRepository.save(usuario3);
        }

        // Usuario 4
        SimpleDateFormat formatter41 = new SimpleDateFormat("dd-MM-yyyy");
        Date fechaNacimiento4 = null;
        try {
            fechaNacimiento4 = formatter41.parse("04-04-1988");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Usuario usuario4 = new Usuario("usuario4", "usuario4@example.com",
                passwordEncoder.encode("admin"), fechaNacimiento4,
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
            juegos.add(juego11);
            juegos.add(juego12);
            juegos.add(juego13);
            juegos.add(juego14);
            juegos.add(juego15);

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
