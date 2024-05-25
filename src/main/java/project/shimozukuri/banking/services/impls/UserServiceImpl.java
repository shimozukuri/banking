package project.shimozukuri.banking.services.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.shimozukuri.banking.dtos.user.UserDto;
import project.shimozukuri.banking.entities.enums.Role;
import project.shimozukuri.banking.entities.user.User;
import project.shimozukuri.banking.mappers.UserMapper;
import project.shimozukuri.banking.repositories.UserRepository;
import project.shimozukuri.banking.services.MoneyAccountService;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final MoneyAccountService moneyAccountService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {
        User user = getByUsername(username).orElseThrow(() -> new IllegalStateException(
                String.format("Пользователь %s не существует", username)
        ));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.name()))
                        .collect(Collectors.toList())
        );
    }

    @Transactional
    public Optional<User> getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional
    public User createNewUser(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRoles(Set.of(Role.ROLE_USER));
        user.setMoneyAccount(moneyAccountService.create(userDto.getBalance(), user));

        return userRepository.save(user);
    }
}
