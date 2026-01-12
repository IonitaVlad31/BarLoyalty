package com.barLoyalty.gateway.controller;

import com.barLoyalty.gateway.dto.MeResponse;
import com.barLoyalty.gateway.entity.User;
import com.barLoyalty.gateway.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/me")
    public MeResponse me(Authentication authentication) {
        User user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException("Utilizator negasit!"));

        return new MeResponse(user.getId(), user.getUsername(), user.getRole(), user.getCurrentPoints());
    }
}
