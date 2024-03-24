package gamecritic.gamecritic_sergiolopez.servicios;

import gamecritic.gamecritic_sergiolopez.entidades.*;
import gamecritic.gamecritic_sergiolopez.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
                sumaPuntajes += votacion.getPuntaje();
            }

            double notaMedia = votaciones.isEmpty() ? 0 : sumaPuntajes / votaciones.size();
            juego.setNotaMedia(notaMedia);
            juegoRepository.save(juego);
        }
        juegos.sort(Comparator.comparing(Juego::getNotaMedia).reversed());

        // Devolver la cantidad especificada de juegos con mejor nota media
        return juegos.subList(0, Math.min(cantidad, juegos.size()));
    }

    public List<Juego> obtenerTodosJuegos() {

        List<Juego> juegos = juegoRepository.findAll();

        for (Juego juego : juegos) {
            List<Votacion> votaciones = votacionRepository.findByJuego(juego);
            double sumaPuntajes = 0;

            for (Votacion votacion : votaciones) {
                sumaPuntajes += votacion.getPuntaje();
            }

            double notaMedia = votaciones.isEmpty() ? 0 : sumaPuntajes / votaciones.size();
            juego.setNotaMedia(notaMedia);
            juegoRepository.save(juego);
        }
        juegos.sort(Comparator.comparing(Juego::getNotaMedia).reversed());

        return juegos;
    }

    public List<Juego> filtrarJuegos(String fechaDesdeStr, String fechaHastaStr, List<String> plataformas, List<String> generos) {
        // Convertir fechas a objetos Date si están presentes en los filtros
        Date fechaDesde = null;
        Date fechaHasta = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if (fechaDesdeStr != null && !fechaDesdeStr.isEmpty()) {
                fechaDesde = dateFormat.parse(fechaDesdeStr);
            }
            if (fechaHastaStr != null && !fechaHastaStr.isEmpty()) {
                fechaHasta = dateFormat.parse(fechaHastaStr);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Lógica para filtrar juegos
        List<Juego> juegosFiltrados = new ArrayList<>();
        List<Juego> juegos = juegoRepository.findAll();

        for (Juego juego : juegos) {
            // Filtrar por fecha de lanzamiento
            if ((fechaDesde == null || juego.getFechaLanzamiento().after(fechaDesde))
                    && (fechaHasta == null || juego.getFechaLanzamiento().before(fechaHasta))) {
                // Filtrar por plataformas
                if (plataformas.isEmpty() || juego.getPlataformas().stream().anyMatch(plataforma -> plataformas.contains(plataforma.getNombre()))) {
                    // Filtrar por géneros
                    if (generos.isEmpty() || juego.getGeneros().stream().anyMatch(genero -> generos.contains(genero.getNombre()))) {
                        juegosFiltrados.add(juego);
                    }
                }
            }
        }

        return juegosFiltrados;
    }


}
