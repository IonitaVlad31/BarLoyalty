package com.barloyalty.gateway.service;

import com.barloyalty.gateway.dto.RegisterRequest;
import com.barloyalty.gateway.entity.Role;
import com.barloyalty.gateway.entity.User;
import com.barloyalty.gateway.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String register(RegisterRequest request) {
        // 1. Verificam daca userul exista deja
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Username-ul este deja folosit!");
        }

        // 2. Setam rolul default
        Role userRole = Role.CLIENT;
        if (request.getRole() != null && "BAR_ADMIN".equalsIgnoreCase(request.getRole())) {
            userRole = Role.BAR_ADMIN;
        }

        // 3. Cream userul
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword())) // Criptam parola
                .role(userRole)
                .currentPoints(0)
                .build();

        // 4. Salvam in baza de date
        userRepository.save(user);

        return "Utilizator inregistrat cu succes: " + user.getUsername();
    }
}
