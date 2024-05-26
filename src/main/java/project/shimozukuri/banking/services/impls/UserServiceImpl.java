package project.shimozukuri.banking.services.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.shimozukuri.banking.dtos.user.EmailDto;
import project.shimozukuri.banking.dtos.user.PhoneNumberDto;
import project.shimozukuri.banking.dtos.user.UserDto;
import project.shimozukuri.banking.entities.enums.Role;
import project.shimozukuri.banking.entities.user.User;
import project.shimozukuri.banking.exceptions.ResourceNotFoundException;
import project.shimozukuri.banking.mappers.UserMapper;
import project.shimozukuri.banking.repositories.UserRepository;
import project.shimozukuri.banking.services.MoneyAccountService;

import java.util.List;
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
        User user = getUserByUsername(username);

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.name()))
                        .collect(Collectors.toList())
        );
    }

    @Transactional
    public Optional<User> getOptionalUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional
    public User getUserByUsername(String username) {
        return getOptionalUserByUsername(username).orElseThrow(() ->
                new ResourceNotFoundException(String.format("User '%s' not found.", username))
        );
    }

    @Transactional
    public User createNewUser(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRoles(Set.of(Role.ROLE_USER));
        if (!userRepository.existsEmail(userDto.getEmail())) {
            user.setEmails(List.of(userDto.getEmail()));
        } else {
            throw new IllegalStateException("Email address already exists.");
        }
        if (!userRepository.existsPhoneNumber(userDto.getPhoneNumber())) {
            user.setPhoneNumbers(List.of(userDto.getPhoneNumber()));
        } else {
            throw new IllegalStateException("Phone number already exists.");
        }
        user.setMoneyAccount(moneyAccountService.create(userDto.getBalance()));

        return userRepository.save(user);
    }

    @Transactional
    public User addEmail(String username, EmailDto emailDto) {
        User user = getUserByUsername(username);

        if (!userRepository.existsEmail(emailDto.getEmail())) {
            user.addEmail(emailDto.getEmail());
            return userRepository.save(user);
        } else {
            throw new IllegalStateException("Email address already exists.");
        }

    }

    @Transactional
    public User addPhoneNumber(String username, PhoneNumberDto phoneNumberDto) {
        User user = getUserByUsername(username);

        if (!userRepository.existsPhoneNumber(phoneNumberDto.getPhoneNumber())) {
            user.addPhoneNumber(phoneNumberDto.getPhoneNumber());
            return userRepository.save(user);
        } else {
            throw new IllegalStateException("Phone number already exists.");
        }
    }

    @Transactional
    public User deleteEmail(String username, EmailDto emailDto) {
        User user = getUserByUsername(username);
        boolean exists = userRepository.existsEmail(emailDto.getEmail());

        if (exists && userRepository.findAllEmailsById(user.getId()).size() > 1) {
            user.getEmails().remove(emailDto.getEmail());
            return user;
        } else if (!exists) {
            throw new IllegalStateException("Email not found.");
        } else {
            throw new IllegalStateException("Must be at least one email address.");
        }
    }

    @Transactional
    public User deletePhoneNumber(String username, PhoneNumberDto phoneNumberDto) {
        User user = getUserByUsername(username);
        boolean exists = userRepository.existsPhoneNumber(phoneNumberDto.getPhoneNumber());

        if (exists && userRepository.findAllPhoneNumbersById(user.getId()).size() > 1) {
            user.getPhoneNumbers().remove(phoneNumberDto.getPhoneNumber());
            return user;
        } else if (!exists) {
            throw new IllegalStateException("Phone number not found.");
        } else {
            throw new IllegalStateException("Must be at least one phone number.");
        }
    }

    @Transactional
    public void deleteByUsername(String username) {
        userRepository.deleteByUsername(username);
    }
}
