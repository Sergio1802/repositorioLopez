package gamecritic.gamecritic_sergiolopez.entidades;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Table(name = "Votacion")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Votacion {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "juego_id", referencedColumnName = "id")
    private Juego juego;

    private int puntaje;

    public Votacion(Usuario usuario, Juego juego, int puntaje) {
        this.usuario = usuario;
        this.juego = juego;
        this.puntaje = puntaje;
    }
}

