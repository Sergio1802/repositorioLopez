package gamecritic.gamecritic_sergiolopez.repositorios;

import gamecritic.gamecritic_sergiolopez.entidades.Comentario;
import gamecritic.gamecritic_sergiolopez.entidades.Juego;
import gamecritic.gamecritic_sergiolopez.entidades.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {
    List<Comentario> findByJuego(Juego juego);

    List<Comentario> findByUsuario(Usuario usuario);

    List<Comentario> findByUsuarioOrderByFechaCreacionDesc(Usuario usuario);

    Page<Comentario> findByJuego(Juego juego, Pageable pageable);

    Comentario findByUsuarioAndJuego(Usuario usuario, Juego juego);
}
