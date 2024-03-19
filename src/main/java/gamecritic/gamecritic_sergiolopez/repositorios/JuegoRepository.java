package gamecritic.gamecritic_sergiolopez.repositorios;

import gamecritic.gamecritic_sergiolopez.entidades.Genero;
import gamecritic.gamecritic_sergiolopez.entidades.Juego;
import gamecritic.gamecritic_sergiolopez.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JuegoRepository extends JpaRepository<Juego,Integer> {
    Juego findByTitulo(String titulo);
}
