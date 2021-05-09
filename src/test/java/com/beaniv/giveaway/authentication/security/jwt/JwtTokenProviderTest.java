package com.beaniv.giveaway.authentication.security.jwt;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class JwtTokenProviderTest {
    private static final String LOGIN = "ivanov_ivan@gmail.com";

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Test
    void testJwtTokenProviderConstructor() {
        Assertions.assertEquals("jwtgiveaway", jwtTokenProvider.getSecret());
        Assertions.assertEquals(3600000, jwtTokenProvider.getValidityInMilliSeconds());
    }

    @Test
    void testGetUsername() {
        String token = jwtTokenProvider.createToken(LOGIN);

        Assertions.assertEquals(LOGIN, jwtTokenProvider.getUsername(token));
    }

    @Test
    void testResolveToken() {
        String expectedBearerToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJpdmFuX2l2YW5pY2gxMzM3QGdtYWlsLmNvbSIsInJvbGVzIjpbXSwiaWF0IjoxNTkxMjUxNTcwLCJleHAiOjE1OTEyNTUxNzB9.nSsZmxIHDLPRBxRpUewaJcSiYvelfnAVQwqd6uwnYWZ9lYajuWU5OdhU6-T_zBjHmiN-jRtrnRxqtsdJ9n2Ztg";

        HttpServletRequestTokenWithToken httpServletRequestTokenWithToken = new HttpServletRequestTokenWithToken();
        Assertions.assertEquals(expectedBearerToken, jwtTokenProvider.resolveToken(httpServletRequestTokenWithToken));

        HttpServletRequestTokenWithoutToken httpServletRequestTokenWithoutToken = new HttpServletRequestTokenWithoutToken();
        assertNull(jwtTokenProvider.resolveToken(httpServletRequestTokenWithoutToken));

        HttpServletRequestWithBadToken httpServletRequestWithBadToken = new HttpServletRequestWithBadToken();
        assertNull(jwtTokenProvider.resolveToken(httpServletRequestWithBadToken));
    }

    @Test
    void testValidateToken() {
        String token = jwtTokenProvider.createToken(LOGIN);

        Assertions.assertTrue(jwtTokenProvider.validateToken(token));

        Assertions.assertThrows(JwtAuthenticationException.class, () -> jwtTokenProvider.validateToken(UUID.randomUUID().toString()));

        jwtTokenProvider = spy(new JwtTokenProvider("secret", (long) -1, null));

        String token2 = jwtTokenProvider.createToken(LOGIN);

        Assertions.assertThrows(JwtAuthenticationException.class, () -> jwtTokenProvider.validateToken(token2));
    }
}