package com.beaniv.giveaway.authentication.security.user.service;

import com.beaniv.giveaway.model.entity.User;
import com.beaniv.giveaway.repository.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;


    @Test
    public void testRegister() {
        User user = new User();
        user.setPassword("password");

        Mockito.when(passwordEncoder.encode("password")).thenReturn("password");

        User repositoryUser = new User();
        repositoryUser.setPassword(passwordEncoder.encode("password"));

        Mockito.when(userRepository.save(Mockito.any())).thenReturn(repositoryUser);

        User actualUser = userService.register(user);
        Assertions.assertEquals(repositoryUser, actualUser);
    }

    @Test
    public void testGetAll() {
        List<User> repositoryUserList = new ArrayList<>();

        User repositoryUser = new User();
        repositoryUser.setId(1);
        repositoryUser.setEmail("ivan@gmail.com");
        repositoryUser.setPassword("password");

        repositoryUserList.add(repositoryUser);

        Mockito.when(userRepository.findAll()).thenReturn(repositoryUserList);
        Assertions.assertEquals(userService.getAll(), repositoryUserList);
    }

    @Test
    public void testFindByEmail() {
        User repositoryUser = new User();
        repositoryUser.setId(1);
        repositoryUser.setEmail("ivan@gmail.com");
        repositoryUser.setPassword("password");

        Mockito.when(userRepository.findByEmail("ivan@gmail.com")).thenReturn(repositoryUser);
        Assertions.assertEquals(userService.findByEmail("ivan@gmail.com"), repositoryUser);
    }

//    @Test
//    public void testFindByUserId() {
//        User repositoryUser = new User();
//        repositoryUser.setId(1);
//        repositoryUser.setEmail("ivan@gmail.com");
//        repositoryUser.setPassword("password");
//
//        Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(repositoryUser));
//        Assertions.assertEquals(userService.findByUserId(1), repositoryUser);
//
//        Assertions.assertNull(userService.findByUserId(2));
//    }

    @Test
    public void testDelete() {
        Integer id = 1;

        userService.delete(id);

        Mockito.verify(userRepository, Mockito.times(1)).deleteById(id);
    }
}
