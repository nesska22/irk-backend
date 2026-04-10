package com.dom.irk_Backend.service;

import com.dom.irk_Backend.model.Administrator;
import com.dom.irk_Backend.repository.AdministratorRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdministratorService {

    private final AdministratorRepository administratorRepository;
    private final PasswordEncoder passwordEncoder;

    public AdministratorService(AdministratorRepository administratorRepository, PasswordEncoder passwordEncoder){
        this.administratorRepository = administratorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Administrator authenticate(String email, String password){
        Optional<Administrator> adminOptional = administratorRepository.findByEmail(email);

        if(adminOptional.isPresent()){
            Administrator admin = adminOptional.get();

            if(passwordEncoder.matches(password, admin.getPasswordHash())){
                return admin;
            }
        }
        return null;
    }
}