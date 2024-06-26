package gamecritic.gamecritic_sergiolopez.configuracion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class ConfiguracionSeguridad {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.authorizeRequests(authorize -> {
            authorize.requestMatchers("/").permitAll();
            authorize.requestMatchers("/irSobreMi").permitAll();
            authorize.requestMatchers("/irRegistro").permitAll();
            authorize.requestMatchers("/irPerfil/**").permitAll();
            authorize.requestMatchers("/verMisListas").permitAll();
            authorize.requestMatchers("/crearLista").permitAll();
            authorize.requestMatchers("/verLista/**").permitAll();
            authorize.requestMatchers("/borrarLista/**").permitAll();
            authorize.requestMatchers("/juegos/irlistaJuegos").permitAll();
            authorize.requestMatchers("/juegos/filtrar").permitAll();
            authorize.requestMatchers("/juegos/votar").permitAll();
            authorize.requestMatchers("/juegos/eliminarVoto").permitAll();
            authorize.requestMatchers("/juegos/comentar").permitAll();
            authorize.requestMatchers("/juegos/irFichaJuego/**").permitAll();
            authorize.requestMatchers("/juegos/borrarComentario/**").permitAll();
            authorize.requestMatchers("/juegos/agregarJuego/**").permitAll();
            authorize.requestMatchers("/juegos/buscarJuegos").permitAll();
            authorize.requestMatchers("/registro").permitAll();
            authorize.requestMatchers("/login").permitAll();
            authorize.requestMatchers("/logout").permitAll();
            authorize.requestMatchers("/imagenes/**").permitAll();
            authorize.requestMatchers("/estilos/**").permitAll();
            authorize.requestMatchers("/portadaJuegos/**").permitAll();
            authorize.requestMatchers("/fotoUsuarios/**").permitAll();
            authorize.anyRequest().authenticated();
        }).build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
