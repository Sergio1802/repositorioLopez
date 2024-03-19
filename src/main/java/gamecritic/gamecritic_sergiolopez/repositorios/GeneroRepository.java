package gamecritic.gamecritic_sergiolopez.repositorios;

import gamecritic.gamecritic_sergiolopez.entidades.Genero;

import gamecritic.gamecritic_sergiolopez.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface GeneroRepository extends JpaRepository<Genero,Integer> {

    Genero findByNombre(String nombre);
}
