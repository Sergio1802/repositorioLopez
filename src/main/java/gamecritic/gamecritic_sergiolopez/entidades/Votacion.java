package gamecritic.gamecritic_sergiolopez.entidades;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.util.Date;

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

    private int nota;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    public Votacion(Usuario usuario, Juego juego, int nota) {
        this.usuario = usuario;
        this.juego = juego;
        this.nota = nota;
        this.fechaCreacion = new Date();
    }
}

