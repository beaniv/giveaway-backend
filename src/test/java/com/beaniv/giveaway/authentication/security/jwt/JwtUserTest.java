package com.beaniv.giveaway.authentication.security.jwt;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JwtUserTest {
    private JwtUser jwtUser;

    @BeforeEach
    void setUo() {
        jwtUser = new JwtUser(1, "simple_password", "ivan@mail.ru", null);
    }

    @Test
    void getUsername() {
        Assertions.assertNull(jwtUser.getUsername());
    }

    @Test
    void isAccountNonExpired() {
        Assertions.assertTrue(jwtUser.isAccountNonExpired());
    }

    @Test
    void isAccountNonLocked() {
        Assertions.assertTrue(jwtUser.isAccountNonLocked());
    }

    @Test
    void isCredentialsNonExpired() {
        Assertions.assertTrue(jwtUser.isCredentialsNonExpired());
    }
}