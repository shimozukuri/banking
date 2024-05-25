package project.shimozukuri.banking.services.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import project.shimozukuri.banking.dtos.authorization.JwtRequestDto;
import project.shimozukuri.banking.dtos.authorization.JwtResponseDto;
import project.shimozukuri.banking.dtos.user.UserDto;
import project.shimozukuri.banking.entities.user.User;
import project.shimozukuri.banking.exceptions.AccessDeniedException;
import project.shimozukuri.banking.services.AuthService;
import project.shimozukuri.banking.utils.JwtTokenUtil;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserServiceImpl userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtResponseDto createAuthToken(JwtRequestDto authRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new AccessDeniedException("Некорректный логин или пароль");
        }

        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);

        return new JwtResponseDto(token);
    }

    @Override
    public User createNewUser(UserDto userDto) {
        if (!userDto.getPassword().equals(userDto.getConfirmPassword())) {
            throw new AccessDeniedException("Пароли не совпадают");
        }
        if (userService.getByUsername(userDto.getUsername()).isPresent()) {
            throw new IllegalStateException(
                    String.format("Пользователь %s уже существует", userDto.getUsername())
            );
        }

        return userService.createNewUser(userDto);
    }
}
