package com.beaniv.giveaway.posts;

import com.beaniv.giveaway.model.dto.post.DetailedPostDto;
import com.beaniv.giveaway.model.dto.post.HomescreenPostDto;
import com.beaniv.giveaway.model.entity.Post;
import com.beaniv.giveaway.model.entity.User;
import com.beaniv.giveaway.repository.PostRepository;
import com.beaniv.giveaway.repository.UserRepository;
import com.beaniv.giveaway.util.dtotransformservice.DtoTransformService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class DefaultPostsServiceTest {

    @Autowired
    private DefaultPostsService defaultPostsService;

    @MockBean
    private DtoTransformService dtoTransformService;

    @MockBean
    private PostRepository postRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private Random random;

    @Test
    void getPosts() {
        Set<Post> posts = new HashSet<>();
        Set<HomescreenPostDto> homescreenPostDtoSet = new HashSet<>();

        Mockito.when(postRepository.findAllBy()).thenReturn(posts);
        Mockito.when(dtoTransformService.convertToSetHomescreenPostDto(posts))
                .thenReturn(homescreenPostDtoSet);

        Set<HomescreenPostDto> homescreenPostDtoSetReturned = defaultPostsService.getPosts();

        assertThat(homescreenPostDtoSetReturned, is(equalTo(homescreenPostDtoSet)));

        Mockito.verify(postRepository, Mockito.times(1))
                .findAllBy();
        Mockito.verify(dtoTransformService, Mockito.times(1))
                .convertToSetHomescreenPostDto(posts);
    }

    @Test
    void getUserPosts() {
        User user = new User();
        Set<Post> posts = new HashSet<>();
        user.setPosts(posts);
        Set<HomescreenPostDto> homescreenPostDtoSet = new HashSet<>();

        Mockito.when(userRepository.findById(ArgumentMatchers.anyInt())).thenReturn(user);
        Mockito.when(dtoTransformService.convertToSetHomescreenPostDto(posts)).thenReturn(homescreenPostDtoSet);

        Set<HomescreenPostDto> homescreenPostDtoSetReturned = defaultPostsService.getUserPosts(0);

        assertThat(homescreenPostDtoSetReturned, is(equalTo(homescreenPostDtoSet)));

        Mockito.verify(userRepository, Mockito.times(1))
                .findById(ArgumentMatchers.anyInt());
        Mockito.verify(dtoTransformService, Mockito.times(1))
                .convertToSetHomescreenPostDto(posts);
    }

    @Test
    void getPost() {
        Post post = new Post();
        Set<User> users = new HashSet<>();
        post.setUsers(users);
        post.setFinishTime(new Timestamp(0));
        DetailedPostDto detailedPostDto = new DetailedPostDto();

        int userId = 0;
        int postId = 0;

        Mockito.when(postRepository.findById(postId)).thenReturn(post);
        Mockito.when(dtoTransformService
                .convertToDetailedPostDto(ArgumentMatchers.any(Post.class), ArgumentMatchers.eq(userId)))
                .thenReturn(detailedPostDto);

        DetailedPostDto detailedPostDtoReturned = defaultPostsService.getPost(userId, postId);

        assertThat(detailedPostDtoReturned, is(equalTo(detailedPostDto)));

        Mockito.verify(postRepository, Mockito.times(1))
                .findById(postId);
        Mockito.verify(postRepository, Mockito.times(1))
                .save(ArgumentMatchers.any(Post.class));
        Mockito.verify(random, Mockito.times(0))
                .nextInt(ArgumentMatchers.anyInt());
        Mockito.verify(dtoTransformService, Mockito.times(1))
                .convertToDetailedPostDto(ArgumentMatchers.any(Post.class), ArgumentMatchers.eq(userId));
    }

    @Test
    void addPost() {
        Post post = new Post();

        defaultPostsService.addPost(post);

        Mockito.verify(postRepository, Mockito.times(1)).save(post);
    }

    @Test
    void addUserToPost() {
        User user = new User();
        Set<Post> posts = new HashSet<>();
        user.setPosts(posts);
        Post post = new Post();

        int userId = 0;
        int postId = 0;

        Mockito.when(userRepository.findById(userId)).thenReturn(user);
        Mockito.when(postRepository.findById(postId)).thenReturn(post);

        defaultPostsService.addUserToPost(userId, postId);

        assertThat(user.getPosts().size(), is(equalTo(1)));
        Mockito.verify(userRepository, Mockito.times(1)).findById(userId);
        Mockito.verify(postRepository, Mockito.times(1)).findById(postId);
        Mockito.verify(userRepository, Mockito.times(1)).save(user);
    }

    @Test
    void removeUserFromPost() {
        User user = new User();
        Set<Post> posts = new HashSet<>();
        Post post = new Post();
        posts.add(post);
        user.setPosts(posts);

        int userId = 0;
        int postId = 0;

        Mockito.when(userRepository.findById(userId)).thenReturn(user);
        Mockito.when(postRepository.findById(postId)).thenReturn(post);

        defaultPostsService.removeUserFromPost(userId, postId);

        assertThat(user.getPosts().size(), is(equalTo(0)));
        Mockito.verify(userRepository, Mockito.times(1)).findById(userId);
        Mockito.verify(postRepository, Mockito.times(1)).findById(postId);
        Mockito.verify(userRepository, Mockito.times(1)).save(user);

        userRepository.save(user);
    }
}
