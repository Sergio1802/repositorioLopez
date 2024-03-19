package gamecritic.gamecritic_sergiolopez.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Entity
@Table(name = "Genero")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Genero {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Column(unique = true)
    private String nombre;

    public Genero(String nombre) {
        this.nombre = nombre;
    }
}
