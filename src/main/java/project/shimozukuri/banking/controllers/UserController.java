package project.shimozukuri.banking.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import project.shimozukuri.banking.dtos.user.EmailDto;
import project.shimozukuri.banking.dtos.user.PhoneNumberDto;
import project.shimozukuri.banking.entities.user.User;
import project.shimozukuri.banking.services.impls.UserServiceImpl;

@RestController
@RequestMapping("/profile/{username}")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userService;

    @GetMapping
    @PreAuthorize("@cse.canAccess(#username)")
    public User getByUsername(
            @PathVariable(value = "username") String username
    ) {
        return userService.getUserByUsername(username);
    }

    @PostMapping("/emails")
    @PreAuthorize("@cse.canAccess(#username)")
    public User addEmail(
            @PathVariable(value = "username") String username,
            @RequestBody @Valid EmailDto emailDto
            ) {
        return userService.addEmail(username, emailDto);
    }

    @DeleteMapping("/emails")
    @PreAuthorize("@cse.canAccess(#username)")
    public User deleteEmail(
            @PathVariable(value = "username") String username,
            @RequestBody @Valid EmailDto emailDto
            ) {
        return userService.deleteEmail(username, emailDto);
    }

    @PostMapping("/phone-numbers")
    @PreAuthorize("@cse.canAccess(#username)")
    public User addEmail(
            @PathVariable(value = "username") String username,
            @RequestBody @Valid PhoneNumberDto phoneNumberDto
    ) {
        return userService.addPhoneNumber(username, phoneNumberDto);
    }

    @DeleteMapping("/phone-numbers")
    @PreAuthorize("@cse.canAccess(#username)")
    public User deleteEmail(
            @PathVariable(value = "username") String username,
            @RequestBody @Valid PhoneNumberDto phoneNumberDto
    ) {
        return userService.deletePhoneNumber(username, phoneNumberDto);
    }
}
