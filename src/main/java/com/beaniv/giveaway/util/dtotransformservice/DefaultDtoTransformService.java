package com.beaniv.giveaway.util.dtotransformservice;

import com.beaniv.giveaway.model.dto.post.DetailedPostDto;
import com.beaniv.giveaway.model.dto.post.HomescreenPostDto;
import com.beaniv.giveaway.model.dto.post.RegisterPostDto;
import com.beaniv.giveaway.model.dto.user.UserDto;
import com.beaniv.giveaway.model.entity.Post;
import com.beaniv.giveaway.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefaultDtoTransformService implements DtoTransformService {
    private final ModelMapper modelMapper;

    @Override
    public User convertToUser(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    @Override
    public Post convertToPost(RegisterPostDto registerPostDto) {
        return modelMapper.map(registerPostDto, Post.class);
    }

    @Override
    public DetailedPostDto convertToDetailedPostDto(Post post) {
        return modelMapper.map(post, DetailedPostDto.class);
    }

    @Override
    public Set<HomescreenPostDto> convertToSetHomescreenPostDto(Set<Post> posts) {
        return posts.stream().map(e -> modelMapper.map(e, HomescreenPostDto.class)).collect(Collectors.toSet());
    }
}