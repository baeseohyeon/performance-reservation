package com.numble.performancereservation.user;

import static com.numble.performancereservation.exception.ExceptionMessage.*;

import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class UserService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    public void join(UserDto userDto) {
        validateDuplicate(userDto);

        Authority authority = authorityRepository.findById(Role.USER)
            .orElse(authorityRepository.save(new Authority(Role.USER)));

        User user = User.builder()
            .email(userDto.getEmail())
            .password(passwordEncoder.encode(userDto.getPassword()))
            .username(userDto.getUsername())
            .authorities(Collections.singleton(authority)).build();

        userRepository.save(user);
    }

    private void validateDuplicate(UserDto userDto) {
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new IllegalArgumentException(DUPLICATE_EMAIL);
        }
        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new IllegalArgumentException(DUPLICATE_USERNAME);
        }
    }

    public User findProducerByIdAndType(Long userId, ProducerType type) {
        User user = userRepository.findByIdAndType(userId, type)
            .orElseThrow(() -> new IllegalArgumentException(INVALID_USERID));
        user.validateBusinessLicense();
        return user;
    }

    public User findById(Long userId) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException(INVALID_USERID));
    }
}
