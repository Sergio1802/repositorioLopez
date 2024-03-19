package gamecritic.gamecritic_sergiolopez.servicios;

import gamecritic.gamecritic_sergiolopez.entidades.Usuario;
import gamecritic.gamecritic_sergiolopez.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * El UserDetailsService es una interfaz de Spring Security que se utiliza
 * para cargar los detalles del usuario necesarios durante la autenticación.
 * Esta interfaz tiene un único método loadUserByUsername(String username) que
 * es implementado para cargar los detalles del usuario basados en el nombre
 * de usuario proporcionado.
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByNombreUsuario(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        return User.withUsername(usuario.getNombreUsuario())
                .password(usuario.getPassword())
                .roles(usuario.getRol())
                .build();
    }
}
