package project.shimozukuri.banking.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.shimozukuri.banking.dtos.authorization.JwtRequestDto;
import project.shimozukuri.banking.dtos.authorization.JwtResponseDto;
import project.shimozukuri.banking.dtos.user.UserDto;
import project.shimozukuri.banking.entities.user.User;
import project.shimozukuri.banking.services.AuthService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public JwtResponseDto createAuthToken(
            @RequestBody @Valid JwtRequestDto authRequest
    ) {
        return authService.createAuthToken(authRequest);
    }

    @PostMapping("/register")
    public User createNewUser(
            @RequestBody @Valid UserDto userDto
    ) {
        return authService.createNewUser(userDto);
    }
}