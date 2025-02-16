package com.shop.altenshop;

import com.shop.altenshop.entities.Role;
import com.shop.altenshop.entities.User;
import com.shop.altenshop.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@SpringBootApplication
@RequiredArgsConstructor
public class AltenShopApplication {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public static void main(String[] args) {
        SpringApplication.run(AltenShopApplication.class, args);
    }

    @Bean
    public CommandLineRunner createAdminUser() {
        return args -> {
            Optional<User> userOptional = userRepository.findByEmail("admin@admin.com");

            if (userOptional.isEmpty()) {
                User admin = User.builder()
                        .firstname("Admin")
                        .username("admin")
                        .email("admin@admin.com")
                        .password(passwordEncoder.encode("admin"))
                        .role(Role.ADMIN)
                        .build();
                userRepository.save(admin);
                System.out.println("Admin user created successfully!");
            }
        };
    }

}
