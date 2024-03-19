package gamecritic.gamecritic_sergiolopez.servicios;

import gamecritic.gamecritic_sergiolopez.entidades.*;
import gamecritic.gamecritic_sergiolopez.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
