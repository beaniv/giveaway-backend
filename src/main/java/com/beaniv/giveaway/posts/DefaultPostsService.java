package com.beaniv.giveaway.posts;

import com.beaniv.giveaway.model.dto.post.DetailedPostDto;
import com.beaniv.giveaway.model.entity.Post;
import com.beaniv.giveaway.model.entity.User;
import com.beaniv.giveaway.repository.PostRepository;
import com.beaniv.giveaway.repository.UserRepository;
import com.beaniv.giveaway.util.dtotransformservice.DtoTransformService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class DefaultPostsService implements PostsService {

    private final EntityManager entityManager;

    private final DtoTransformService dtoTransformService;

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    @Override
    public Set<DetailedPostDto> getPosts() {
        Set<Post> posts = postRepository.findAllBy();
        return dtoTransformService.convertToSetPostInfoDto(posts);
    }

    @Override
    public Post addPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public User addUserToPost(int userId, int postId) {
        User user = userRepository.findById(userId);
        Post post = postRepository.findById(postId);

        Set<Post> posts = user.getPosts();
        posts.add(post);
        user.setPosts(posts);

        return userRepository.save(user);
    }

    @Override
    public User removeUserFromPost(int userId, int postId) {
        User user = userRepository.findById(userId);
        Post post = postRepository.findById(postId);

        Set<Post> posts = user.getPosts();
        posts.remove(post);
        user.setPosts(posts);

        return userRepository.save(user);
    }

    @Override
    public Set<DetailedPostDto> getUserPosts(int userId) {
        User user = userRepository.findById(userId);
        Set<Post> posts = user.getPosts();

        return dtoTransformService.convertToSetPostInfoDto(posts);
    }
}
