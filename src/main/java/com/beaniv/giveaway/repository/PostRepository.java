package com.beaniv.giveaway.repository;

import com.beaniv.giveaway.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface PostRepository extends JpaRepository<Post, Integer> {
    Set<Post> findAllBy();

    Post findById(int id);
}
