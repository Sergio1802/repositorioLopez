package gamecritic.gamecritic_sergiolopez.configuracion;

import ch.qos.logback.core.model.Model;
import gamecritic.gamecritic_sergiolopez.entidades.Usuario;
import gamecritic.gamecritic_sergiolopez.repositorios.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class SessionHandler {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private HttpSession session;

    @ModelAttribute
    public void checkSession(HttpServletRequest request, Model model) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("usuarioId")) {
                    String usuarioId = cookie.getValue();
                    Usuario usuario = usuarioRepository.findById(Integer.parseInt(usuarioId)).orElse(null);
                    if (usuario != null) {
                        session.setAttribute("usuarioLogueado", usuario);
                        session.setAttribute("userId", usuario.getId());
                        break;
                    }
                }
            }
        }
    }
}
