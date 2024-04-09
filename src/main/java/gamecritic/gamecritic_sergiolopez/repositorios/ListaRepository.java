package gamecritic.gamecritic_sergiolopez.repositorios;

import gamecritic.gamecritic_sergiolopez.entidades.Lista;
import gamecritic.gamecritic_sergiolopez.entidades.Plataforma;
import gamecritic.gamecritic_sergiolopez.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListaRepository extends JpaRepository<Lista, Integer> {
    List<Lista> findByUsuario(Usuario usuario);
}
