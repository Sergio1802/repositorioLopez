package gamecritic.gamecritic_sergiolopez.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Scope("prototype")
@Entity
@Table(name = "Usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @NotEmpty
    @Size(max = 20)
    @Pattern(regexp = ".*")
    private String nombreUsuario;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private String password;

    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;

    private String fotoPerfil;

    @NotEmpty
    private String rol;

    public Usuario(String nombreUsuario, String email, String password, Date fechaNacimiento, String fotoPerfil, String rol) {
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.password = password;
        this.fechaNacimiento = fechaNacimiento;
        this.fotoPerfil = fotoPerfil;
        this.rol = rol;
    }
}
