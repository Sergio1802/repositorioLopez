package gamecritic.gamecritic_sergiolopez.servicios;

import gamecritic.gamecritic_sergiolopez.entidades.*;
import gamecritic.gamecritic_sergiolopez.repositorios.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class JuegoServicio {

    @Autowired
    private JuegoRepository juegoRepository;

    @Autowired
    private VotacionRepository votacionRepository;

    public List<Juego> obtenerMejoresJuegos(int cantidad) {

        List<Juego> juegos = juegoRepository.findAll();

        for (Juego juego : juegos) {
            List<Votacion> votaciones = votacionRepository.findByJuego(juego);
            double sumaPuntajes = 0;

            for (Votacion votacion : votaciones) {
                sumaPuntajes += votacion.getNota();
            }

            double notaMedia = votaciones.isEmpty() ? 0 : sumaPuntajes / votaciones.size();
            juego.setNotaMedia(notaMedia);
            juegoRepository.save(juego);
        }
        juegos.sort(Comparator.comparing(Juego::getNotaMedia).reversed());

        return juegos.subList(0, Math.min(cantidad, juegos.size()));
    }

    public List<Juego> obtenerTodosJuegos() {

        List<Juego> juegos = juegoRepository.findAll();

        for (Juego juego : juegos) {
            List<Votacion> votaciones = votacionRepository.findByJuego(juego);
            double sumaPuntajes = 0;

            for (Votacion votacion : votaciones) {
                sumaPuntajes += votacion.getNota();
            }

            double notaMedia = votaciones.isEmpty() ? 0 : sumaPuntajes / votaciones.size();
            juego.setNotaMedia(notaMedia);
            juegoRepository.save(juego);
        }
        juegos.sort(Comparator.comparing(Juego::getNotaMedia).reversed());

        return juegos;
    }

    public List<Juego> filtrarJuegos(String fechaDesdeStr, String fechaHastaStr, List<String> plataformas, List<String> generos) {
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);

        Date fechaDesde = null;
        Date fechaHasta = null;

        try {
            if (fechaDesdeStr != null && !fechaDesdeStr.isEmpty()) {
                int yearDesde = Integer.parseInt(fechaDesdeStr);
                if (yearDesde >= 1000 && yearDesde <= 9999) {
                    cal.set(Calendar.YEAR, yearDesde);
                    cal.set(Calendar.MONTH, Calendar.JANUARY);
                    cal.set(Calendar.DAY_OF_MONTH, 1);
                    fechaDesde = cal.getTime();
                }
            }
            if (fechaHastaStr != null && !fechaHastaStr.isEmpty()) {
                int yearHasta = Integer.parseInt(fechaHastaStr);
                if (yearHasta >= 1000 && yearHasta <= 9999) {
                    cal.set(Calendar.YEAR, yearHasta);
                    cal.set(Calendar.MONTH, Calendar.DECEMBER);
                    cal.set(Calendar.DAY_OF_MONTH, 31);
                    fechaHasta = cal.getTime();
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        List<Juego> juegosFiltrados = new ArrayList<>();
        List<Juego> juegos = juegoRepository.findAll();

        for (Juego juego : juegos) {
            boolean cumpleFecha = (fechaDesde == null || juego.getFechaLanzamiento().after(fechaDesde))
                    && (fechaHasta == null || juego.getFechaLanzamiento().before(fechaHasta));

            boolean contienePlataformas = plataformas.isEmpty() || juego.getPlataformas().stream()
                    .map(Plataforma::getNombre)
                    .collect(Collectors.toList())
                    .containsAll(plataformas);

            boolean contieneGeneros = generos.isEmpty() || juego.getGeneros().stream()
                    .map(Genero::getNombre)
                    .collect(Collectors.toList())
                    .containsAll(generos);

            if (cumpleFecha && contienePlataformas && contieneGeneros) {
                juegosFiltrados.add(juego);
            }
        }

        return juegosFiltrados;
    }

    @Transactional
    public Juego votar(Integer juegoId, int nota, Usuario usuario) {
        Juego juego = juegoRepository.findById(juegoId).orElse(null);

        if (juego != null) {
            Votacion votacionAnterior = votacionRepository.findByUsuarioAndJuego(usuario, juego);

            if (votacionAnterior != null) {
                votacionRepository.delete(votacionAnterior);
            }

            Votacion nuevaVotacion = new Votacion();
            nuevaVotacion.setJuego(juego);
            nuevaVotacion.setNota(nota);
            nuevaVotacion.setUsuario(usuario);
            nuevaVotacion.setFechaCreacion(new Date());

            votacionRepository.save(nuevaVotacion);
            juego.setNotaMedia(nuevaNotaMedia(juego));
            juego = juegoRepository.save(juego);
        }
        return juego;
    }

    @Transactional
    public Juego borrarVoto(Integer juegoId, Usuario usuario) {
        Juego juego = juegoRepository.findById(juegoId).orElse(null);

        if (juego != null) {
            Votacion votacion = votacionRepository.findByUsuarioAndJuego(usuario, juego);

            if (votacion != null) {
                votacionRepository.delete(votacion);
                juego.setNotaMedia(nuevaNotaMedia(juego));
                juego = juegoRepository.save(juego);
            }
        }
        return juego;
    }


    private double nuevaNotaMedia(Juego juego) {
        List<Votacion> votaciones = votacionRepository.findByJuego(juego);
        double sumaPuntajes = 0;

        for (Votacion votacion : votaciones) {
            sumaPuntajes += votacion.getNota();
        }

        double notaMedia = votaciones.isEmpty() ? 0 : sumaPuntajes / votaciones.size();

        return notaMedia;
    }


}
