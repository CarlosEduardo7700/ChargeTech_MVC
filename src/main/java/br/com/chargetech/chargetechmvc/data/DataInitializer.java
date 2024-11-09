//package br.com.chargetech.chargetechmvc.data;
//
//import br.com.chargetech.chargetechmvc.models.Genero;
//import br.com.chargetech.chargetechmvc.models.Role;
//import br.com.chargetech.chargetechmvc.models.Usuario;
//import br.com.chargetech.chargetechmvc.repositories.GeneroRepository;
//import br.com.chargetech.chargetechmvc.repositories.RoleRepository;
//import br.com.chargetech.chargetechmvc.repositories.UsuarioRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.time.LocalDate;
//import java.util.Set;
//
//@Configuration
//public class DataInitializer {
//    @Bean
//    CommandLineRunner initDatabase(UsuarioRepository userRepository, GeneroRepository generoRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
//        return args -> {
//            Role adminRole = new Role();
//            adminRole.setNome("ROLE_ADMIN");
//            adminRole.setLabel("Administrador");
//            roleRepository.save(adminRole);
//
//            Role userRole = new Role();
//            userRole.setNome("ROLE_USER");
//            userRole.setLabel("Usuário");
//            roleRepository.save(userRole);
//
//            Genero feminoGenero = new Genero();
//            feminoGenero.setNome("FEMININO");
//            feminoGenero.setLabel("Feminino");
//            feminoGenero.setDescricao("Alguém que se identifica com mulher.");
//            generoRepository.save(feminoGenero);
//
//            Genero masculinoGenero = new Genero();
//            masculinoGenero.setNome("MASCULINO");
//            masculinoGenero.setLabel("Masculino");
//            masculinoGenero.setDescricao("Alguém que se identifica com homem.");
//            generoRepository.save(masculinoGenero);
//
//            Usuario admin = new Usuario();
//            admin.setNome("Fabia Pereira");
//            admin.setDataDeNascimento(LocalDate.now().minusYears(20));
//            admin.setEmail("fabia@email.com");
//            admin.setSenha(passwordEncoder.encode("Admin@123"));
//            admin.setGenero(feminoGenero);
//            admin.setRoles(Set.of(adminRole));
//            userRepository.save(admin);
//
//            Usuario user = new Usuario();
//            user.setNome("Jorge Mendes");
//            user.setDataDeNascimento(LocalDate.now().minusYears(20));
//            user.setEmail("lucas@email.com");
//            user.setSenha(passwordEncoder.encode("User@123"));
//            user.setGenero(masculinoGenero);
//            user.setRoles(Set.of(userRole));
//            userRepository.save(user);
//        };
//    }
//}