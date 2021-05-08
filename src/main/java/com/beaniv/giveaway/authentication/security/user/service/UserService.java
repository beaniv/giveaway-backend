package com.beaniv.giveaway.authentication.security.user.service;

import com.beaniv.giveaway.model.entity.User;

import java.util.List;

public interface UserService {
    User register(User user);

    List<User> getAll();

    User findByEmail(String email);

    User findByUserId(Integer id);

    void delete(Integer id);
}
