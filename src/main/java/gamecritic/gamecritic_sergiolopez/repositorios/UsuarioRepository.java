package gamecritic.gamecritic_sergiolopez.repositorios;

import gamecritic.gamecritic_sergiolopez.entidades.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
    Usuario findByEmail(String emailUsuario);

    Usuario findByNombreUsuario(String nombreUsuario);

}
