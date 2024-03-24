package gamecritic.gamecritic_sergiolopez.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
@Scope("prototype")
@Entity
@Table(name = "Juego")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Juego {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    private String titulo;

    @Column(length = 4000)
    private String descripcion;
    private String foto;

    @Column(name = "fecha_lanzamiento")
    private Date fechaLanzamiento;

    @ManyToMany
    @JoinTable(
            name = "Juego_Genero",
            joinColumns = @JoinColumn(name = "juego_id"),
            inverseJoinColumns = @JoinColumn(name = "genero_id"))
    private Set<Genero> generos = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "Juego_Plataforma",
            joinColumns = @JoinColumn(name = "juego_id"),
            inverseJoinColumns = @JoinColumn(name = "plataforma_id"))
    private Set<Plataforma> plataformas = new HashSet<>();

    private Double notaMedia;

    public Juego(String titulo, String descripcion, String foto, Date fechaLanzamiento, Set<Genero> generos, Set<Plataforma> plataformas) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.foto = foto;
        this.fechaLanzamiento = fechaLanzamiento;
        this.generos = generos;
        this.plataformas = plataformas;
    }
}

