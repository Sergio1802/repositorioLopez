package gamecritic.gamecritic_sergiolopez.repositorios;

import gamecritic.gamecritic_sergiolopez.entidades.Juego;
import gamecritic.gamecritic_sergiolopez.entidades.Usuario;
import gamecritic.gamecritic_sergiolopez.entidades.Votacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VotacionRepository extends JpaRepository<Votacion, Integer> {

    List<Votacion> findByJuego(Juego juego);

    List<Votacion> findByUsuario(Usuario usuario);

    List<Votacion> findByUsuarioOrderByFechaCreacionDesc(Usuario usuario);

    Votacion findByUsuarioAndJuego(Usuario usuario, Juego juego);

    @Query("SELECT COUNT(v) FROM Votacion v WHERE v.juego.id = :juegoId")
    int countByJuegoId(@Param("juegoId") Integer juegoId);

}
