package com.numble.performancereservation.user;

import static org.springframework.http.HttpStatus.*;

import com.numble.performancereservation.user.security.CustomUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class UserController {

    private final UserService userService;

    @ResponseStatus(CREATED)
    @PostMapping("/join")
    public void join(@RequestBody UserDto userDto) {
        userService.join(userDto);
    }

    @GetMapping("/users")
    public ResponseEntity<UserDto> findUserInfo(@AuthenticationPrincipal CustomUser customUser) {
        return new ResponseEntity<>(new UserDto(userService.findById(customUser.getUserId())), OK);
    }
}
