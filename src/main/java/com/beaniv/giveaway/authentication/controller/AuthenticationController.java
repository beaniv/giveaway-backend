package com.beaniv.giveaway.authentication.controller;

import com.beaniv.giveaway.authentication.service.AuthenticationService;
import com.beaniv.giveaway.model.dto.user.Credentials;
import com.beaniv.giveaway.model.dto.user.TokenDto;
import com.beaniv.giveaway.model.dto.user.UserRegistrationDto;
import com.beaniv.giveaway.repository.UserRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/auth/")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    private final UserRepository userRepository;

    @PostMapping("/sign-in")
    @ApiOperation("Авторизация")
    public TokenDto signIn(@ApiParam(required = true, value = "Информация для авторизации")
                           @RequestBody Credentials credentials) {
        System.out.println("[eq");
        String token = authenticationService.generateToken(credentials);
        return new TokenDto(token);
    }

    @PostMapping("/sign-up")
    @ApiOperation("Регистрация")
    public void signUp(@ApiParam(required = true, value = "Информация для регистрации")
                       @RequestBody UserRegistrationDto userRegistrationDto) {
        authenticationService.registerUser(userRegistrationDto);
    }
}
