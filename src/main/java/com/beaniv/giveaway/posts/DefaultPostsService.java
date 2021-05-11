package com.beaniv.giveaway.posts;

import com.beaniv.giveaway.model.dto.post.HomescreenPostDto;
import com.beaniv.giveaway.model.entity.Post;
import com.beaniv.giveaway.repository.PostRepository;
import com.beaniv.giveaway.repository.UserRepository;
import com.beaniv.giveaway.util.dtotransformservice.DtoTransformService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class DefaultPostsService implements PostsService {

    private final DtoTransformService dtoTransformService;

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    @Override
    public Set<HomescreenPostDto> getPosts() {
        Set<Post> posts = postRepository.findAllBy();
        return dtoTransformService.convertToSetHomescreenPostDto(posts);
    }

    @Override
    public Set<HomescreenPostDto> getUserPosts(int userId) {
        var user = userRepository.findById(userId);
        Set<Post> posts = user.getPosts();

        return dtoTransformService.convertToSetHomescreenPostDto(posts);
    }

    @Override
    public void addPost(Post post) {
        postRepository.save(post);
    }

    @Override
    public void addUserToPost(int userId, int postId) {
        var user = userRepository.findById(userId);
        var post = postRepository.findById(postId);

        Set<Post> posts = user.getPosts();
        posts.add(post);
        user.setPosts(posts);

        userRepository.save(user);
    }

    @Override
    public void removeUserFromPost(int userId, int postId) {
        var user = userRepository.findById(userId);
        var post = postRepository.findById(postId);

        Set<Post> posts = user.getPosts();
        posts.remove(post);
        user.setPosts(posts);

        userRepository.save(user);
    }
}
