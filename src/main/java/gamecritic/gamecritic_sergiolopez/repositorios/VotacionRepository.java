package gamecritic.gamecritic_sergiolopez.repositorios;

import gamecritic.gamecritic_sergiolopez.entidades.Juego;
import gamecritic.gamecritic_sergiolopez.entidades.Usuario;
import gamecritic.gamecritic_sergiolopez.entidades.Votacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VotacionRepository extends JpaRepository<Votacion,Integer> {

    List<Votacion> findByJuego(Juego juego);
    Votacion findByUsuarioAndJuego(Usuario usuario,Juego juego);

}
