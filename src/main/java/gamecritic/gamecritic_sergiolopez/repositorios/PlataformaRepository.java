package gamecritic.gamecritic_sergiolopez.repositorios;

import gamecritic.gamecritic_sergiolopez.entidades.Genero;
import gamecritic.gamecritic_sergiolopez.entidades.Plataforma;
import gamecritic.gamecritic_sergiolopez.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlataformaRepository extends JpaRepository<Plataforma,Integer> {
    Plataforma findByNombre(String nombre);
}
