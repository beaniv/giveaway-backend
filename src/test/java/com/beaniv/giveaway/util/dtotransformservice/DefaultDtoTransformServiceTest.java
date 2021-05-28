package com.beaniv.giveaway.util.dtotransformservice;

import com.beaniv.giveaway.model.dto.post.DetailedPostDto;
import com.beaniv.giveaway.model.dto.post.HomescreenPostDto;
import com.beaniv.giveaway.model.dto.post.RegisterPostDto;
import com.beaniv.giveaway.model.dto.user.UserDto;
import com.beaniv.giveaway.model.entity.Post;
import com.beaniv.giveaway.model.entity.User;
import com.beaniv.giveaway.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class DefaultDtoTransformServiceTest {

    @Autowired
    private DefaultDtoTransformService defaultDtoTransformService;

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private UserRepository userRepository;

    @Test
    void convertToUser() {
        UserDto userDto = new UserDto();
        User user = new User();

        Mockito.when(modelMapper.map(userDto, User.class)).thenReturn(user);

        User userReturned = defaultDtoTransformService.convertToUser(userDto);

        Mockito.verify(modelMapper, Mockito.times(1)).map(userDto, User.class);
        assertThat(userReturned, is(equalTo(user)));
    }

    @Test
    void convertToPost() {
        RegisterPostDto registerPostDto = new RegisterPostDto();
        Post post = new Post();

        Mockito.when(modelMapper.map(registerPostDto, Post.class)).thenReturn(post);

        Post postReturned = defaultDtoTransformService.convertToPost(registerPostDto);

        Mockito.verify(modelMapper, Mockito.times(1)).map(registerPostDto, Post.class);
        assertThat(postReturned, is(equalTo(post)));
    }

    @Test
    void convertToDetailedPostDto() {
        DetailedPostDto detailedPostDto = new DetailedPostDto();

        Post post = new Post();
        Timestamp timestamp = new Timestamp(0);
        int creatorId = 0;
        post.setFinishTime(timestamp);
        post.setCreatorId(creatorId);
        post.setUsers(new HashSet<>());

        int userId = 1;

        Mockito.when(modelMapper.map(post, DetailedPostDto.class)).thenReturn(detailedPostDto);
        Mockito.when(userRepository.findById(userId)).thenReturn(new User());

        DetailedPostDto detailedPostDtoReturned = defaultDtoTransformService.convertToDetailedPostDto(post, userId);

        Mockito.verify(modelMapper, Mockito.times(1)).map(post, DetailedPostDto.class);
        Mockito.verify(userRepository, Mockito.times(1)).findById(userId);
        assertThat(detailedPostDtoReturned.isFinished(), is(true));
        assertThat(detailedPostDtoReturned.isCreator(), is(false));
        assertThat(detailedPostDtoReturned.isParticipant(), is(false));
    }

    @Test
    void convertToSetHomescreenPostDto() {
        final int SET_SIZE = 10;

        Set<Post> postSet = new HashSet<>();
        for (int i = 0; i < SET_SIZE; i++) {
            postSet.add(new Post());
        }

        HomescreenPostDto homescreenPostDto = new HomescreenPostDto();
        Mockito.when(modelMapper.map(ArgumentMatchers.any(Post.class), ArgumentMatchers.eq(HomescreenPostDto.class)))
                .thenReturn(homescreenPostDto);

        Set<HomescreenPostDto> homescreenPostDtoSet = defaultDtoTransformService.convertToSetHomescreenPostDto(postSet);

        Mockito.verify(modelMapper, Mockito.times(SET_SIZE))
                .map(ArgumentMatchers.any(Post.class),  ArgumentMatchers.eq(HomescreenPostDto.class));
        assertThat(homescreenPostDtoSet.size(), is(equalTo(1)));
    }
}
