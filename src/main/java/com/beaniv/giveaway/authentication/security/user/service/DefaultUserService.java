package com.beaniv.giveaway.authentication.security.user.service;

import com.beaniv.giveaway.model.entity.User;
import com.beaniv.giveaway.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DefaultUserService implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        var registeredUser = userRepository.save(user);

        log.info("IN register - user {} was successfully registered", registeredUser);

        return registeredUser;
    }

    @Override
    public List<User> getAll() {
        List<User> allUsers = userRepository.findAll();

        log.info("IN getAll - {} users found", allUsers.size());

        return allUsers;
    }

    @Override
    public User findByEmail(String email) {
        var user = userRepository.findByEmail(email);

        log.info("IN findByUsername - user: {} found by email {}", user, email);

        return user;
    }

    @Override
    public User findByUserId(int id) {
        var user = userRepository.findById(id);

        if (user == null) {
            log.warn("IN findById - no user found by id: {}", id);
        }

        return user;
    }

    @Override
    public void delete(int id) {
        userRepository.deleteById(id);

        log.info("IN delete - user with id: {} successfully deleted", id);
    }
}
