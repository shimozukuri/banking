package project.shimozukuri.banking.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.shimozukuri.banking.entities.user.User;
import project.shimozukuri.banking.exceptions.ResourceNotFoundException;
import project.shimozukuri.banking.services.impls.UserServiceImpl;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userService;

    @GetMapping("/{username}")
    public User getByUsername(@PathVariable(value = "username") String username) {
        return userService.getByUsername(username).orElseThrow(() ->
                new ResourceNotFoundException(String.format("User '%s' not found", username))
        );
    }
}
