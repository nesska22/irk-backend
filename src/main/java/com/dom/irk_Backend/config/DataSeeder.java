package com.dom.irk_Backend.config;

import com.dom.irk_Backend.model.Administrator;
import com.dom.irk_Backend.repository.AdministratorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataSeeder {

    @Bean
    public CommandLineRunner initAdmin(AdministratorRepository adminRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (adminRepository.count() == 0) {

                Administrator admin = new Administrator();
                admin.setEmail("admin@admin.com");
                admin.setPasswordHash(passwordEncoder.encode("admin123"));
                admin.setFirstName("Adam");
                admin.setLastName("Adminowy");


                adminRepository.save(admin);
                System.out.println("======== UTWORZONO DOMYŚLNE KONTO ADMINISTRATORA ========");
            }
        };
    }
}