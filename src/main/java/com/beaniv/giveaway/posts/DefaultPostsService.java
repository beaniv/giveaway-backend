package com.beaniv.giveaway.posts;

import com.beaniv.giveaway.model.dto.post.DetailedPostDto;
import com.beaniv.giveaway.model.dto.post.HomescreenPostDto;
import com.beaniv.giveaway.model.entity.Post;
import com.beaniv.giveaway.model.entity.User;
import com.beaniv.giveaway.repository.PostRepository;
import com.beaniv.giveaway.repository.UserRepository;
import com.beaniv.giveaway.util.dtotransformservice.DtoTransformService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class DefaultPostsService implements PostsService {

    private final DtoTransformService dtoTransformService;

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    private final Random random = new Random();

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
    public DetailedPostDto getPost(int postId, int userId) {
        var post = postRepository.findById(postId);

        var currentTime = new Timestamp(System.currentTimeMillis());
        if(currentTime.after(post.getFinishTime()) && post.getWinnerId() == 0) {
            Set<User> users = post.getUsers();

            var usersArray = new User[users.size()];
            users.toArray(usersArray);

            if(users.isEmpty()) {
                post.setWinnerId(-1);
            } else post.setWinnerId(usersArray[random.nextInt(users.size())].getId());

            postRepository.save(post);
        }

        return dtoTransformService.convertToDetailedPostDto(post, userId);
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
