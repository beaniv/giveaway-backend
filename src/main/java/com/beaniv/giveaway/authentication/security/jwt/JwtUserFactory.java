package com.beaniv.giveaway.authentication.security.jwt;

import com.beaniv.giveaway.model.entity.User;

public final class JwtUserFactory {
    private JwtUserFactory() { }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getPassword(),
                user.getEmail(),
                user.getUpdated()
        );
    }
}
