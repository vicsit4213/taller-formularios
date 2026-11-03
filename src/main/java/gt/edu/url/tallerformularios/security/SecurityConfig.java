package gt.edu.url.tallerformularios.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/acceso-denegado").setViewName("acceso-denegado");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filtros(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(reglas -> reglas
                // Permisos públicos para login, recursos estáticos y errores
                .requestMatchers("/login", "/css/**", "/js/**", "/images/**", "/error").permitAll()
                // Permitir ver y registrar jugadores tanto a ADMIN como a ENTRENADOR
                .requestMatchers("/jugadores/nuevo", "/jugadores/*/editar", "/registro/**").hasAnyRole("ADMIN", "ENTRENADOR")
                .requestMatchers(HttpMethod.POST, "/jugadores/**", "/registro/**").hasAnyRole("ADMIN", "ENTRENADOR")
                .anyRequest().authenticated())
            .formLogin(login -> login
                .loginPage("/login")
                .defaultSuccessUrl("/jugadores", true)
                .permitAll())
            .logout(logout -> logout
                .logoutSuccessUrl("/login?salio")
                .permitAll())
            .exceptionHandling(errores -> errores
                .accessDeniedPage("/acceso-denegado"));
        return http.build();
    }
}