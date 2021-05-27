package com.beaniv.giveaway.authentication.security;

import com.beaniv.giveaway.authentication.security.JwtUserDetailService;
import com.beaniv.giveaway.authentication.security.jwt.JwtUser;
import com.beaniv.giveaway.authentication.security.user.service.UserService;
import com.beaniv.giveaway.model.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class JwtUserDetailServiceTest {
    @Autowired
    private JwtUserDetailService jwtUserDetailService;

    @MockBean
    private UserService userService;

    @Test
    void loadUserByUsername() {
        String wrongEmail = "ivan_wrong@gmail.com";

        String correctEmail = "ivan@gmail.com";

        User user = new User();
        user.setId(1);
        user.setEmail("ivan@gmail.com");
        user.setPassword("password");
        user.setConfirmed(false);

        Mockito.when(userService.findByEmail(wrongEmail)).thenReturn(null);
        Mockito.when(userService.findByEmail(correctEmail)).thenReturn(user);

        Exception exception = Assertions.assertThrows(UsernameNotFoundException.class, () -> {
            jwtUserDetailService.loadUserByUsername(wrongEmail);
        });

        Assertions.assertEquals("User with login " + wrongEmail + " not found", exception.getMessage());

        JwtUser jwtUser = (JwtUser) jwtUserDetailService.loadUserByUsername(correctEmail);

        Assertions.assertEquals(1, jwtUser.getId());
        Assertions.assertEquals("ivan@gmail.com", jwtUser.getEmail());
        Assertions.assertEquals("password", jwtUser.getPassword());
    }
}