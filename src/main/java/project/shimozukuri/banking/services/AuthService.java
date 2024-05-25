package project.shimozukuri.banking.services;

import project.shimozukuri.banking.dtos.authorization.JwtRequestDto;
import project.shimozukuri.banking.dtos.authorization.JwtResponseDto;
import project.shimozukuri.banking.dtos.user.UserDto;
import project.shimozukuri.banking.entities.user.User;

public interface AuthService {

    JwtResponseDto createAuthToken(JwtRequestDto authRequest);

    User createNewUser(UserDto userDto);
}
