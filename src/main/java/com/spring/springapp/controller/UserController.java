package com.spring.springapp.controller;

import com.spring.springapp.domain.user.User;
import com.spring.springapp.service.UserService;
import com.spring.springapp.service.authentication.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public boolean signUp(@RequestBody User user) {
        return userService.signUp(user);
    }

    @PostMapping("/signIn")
    @ResponseStatus(HttpStatus.OK)
    public void signIn(@RequestBody User user) {
        long userId = userService.signIn(user);
        authenticationService.setUserId(userId);
    }

    @PostMapping("/bySubscription")
    @ResponseStatus(HttpStatus.OK)
    public void buySubscription(){
        userService.buySubscription(authenticationService.getUserId());
    }
}
