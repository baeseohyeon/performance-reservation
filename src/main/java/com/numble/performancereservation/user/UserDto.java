package com.numble.performancereservation.user;

import java.util.Set;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserDto {

    private Long userId;
    private String email;
    private String password;
    private String username;
    private String businessLicense;
    private ProducerType type;
    private Set<Role> authorities;

    public UserDto(User user) {
        userId = user.getId();
        email = user.getEmail();
        username = user.getUsername();
        businessLicense = user.getBusinessLicense();
        type = user.getType();
        authorities = user.getAuthorities().stream().map((Authority::getRole))
            .collect(Collectors.toSet());
    }
}
