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
@Table(name = "Plataforma")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Plataforma {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Column(unique = true)
    private String nombre;

    public Plataforma(String nombre) {
        this.nombre = nombre;
    }
}

