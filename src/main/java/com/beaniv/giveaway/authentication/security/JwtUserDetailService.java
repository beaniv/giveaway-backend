package com.beaniv.giveaway.authentication.security;

import com.beaniv.giveaway.authentication.security.jwt.JwtUserFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.beaniv.giveaway.authentication.security.user.service.UserService;

@Service("JwtUserDetailService")
@RequiredArgsConstructor
@Slf4j
public class JwtUserDetailService implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String login) {
        var user = userService.findByEmail(login);

        if (user == null) {
            log.info("IN loadUserByUsername - user with login {} not found", login);

            throw new UsernameNotFoundException("User with login " + login + " not found");
        }

        var jwtUser = JwtUserFactory.create(user);
        log.info("IN loadUserByUsername - user with login: {} successfully loaded", login);
        return jwtUser;
    }
}
