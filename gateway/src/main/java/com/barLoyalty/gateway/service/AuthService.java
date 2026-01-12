package com.barLoyalty.gateway.service;

import com.barLoyalty.gateway.dto.LoginRequest;
import com.barLoyalty.gateway.dto.RegisterRequest;
import com.barLoyalty.gateway.entity.Role;
import com.barLoyalty.gateway.entity.User;
import com.barLoyalty.gateway.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String register(RegisterRequest request) {
        // 1. Verificam daca userul exista deja
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Username-ul este deja folosit!");
        }

        // 2. Setam rolul default
        Role userRole = Role.CLIENT;
        if ("BAR_ADMIN".equalsIgnoreCase(request.getRole())) {
            userRole = Role.BAR_ADMIN;
        }

        // 3. Cream userul
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword())) // Criptam parola!
                .role(userRole)
                .currentPoints(0)
                .build();

        // 4. Salvam in baza de date
        userRepository.save(user);

        return "Utilizator inregistrat cu succes: " + user.getUsername();
    }

    public String login(LoginRequest request) {
        // 1. Cautam userul dupa username
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Utilizatorul nu exista!"));
        // 2. Verificam parola
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Parola incorecta!");
        }

        return "Autentificare reusita! Rol:" + user.getRole();
    }
}