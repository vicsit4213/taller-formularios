package gt.edu.url.tallerformularios.security;

import gt.edu.url.tallerformularios.model.Usuario;
import gt.edu.url.tallerformularios.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class CargadorUsuarios {

    @Bean
    CommandLineRunner inicializarUsuarios(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            String adminPassword = System.getenv("ADMIN_PASSWORD");
            String entrenadorPassword = System.getenv("ENTRENADOR_PASSWORD");

            if (adminPassword != null && usuarioRepository.findByUsername("admin").isEmpty()) {
                Usuario admin = new Usuario();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode(adminPassword));
                admin.setRol("ROLE_ADMIN");
                usuarioRepository.save(admin);
            }

            if (entrenadorPassword != null && usuarioRepository.findByUsername("entrenador").isEmpty()) {
                Usuario entrenador = new Usuario();
                entrenador.setUsername("entrenador");
                entrenador.setPassword(passwordEncoder.encode(entrenadorPassword));
                entrenador.setRol("ROLE_ENTRENADOR");
                usuarioRepository.save(entrenador);
            }
        };
    }
}