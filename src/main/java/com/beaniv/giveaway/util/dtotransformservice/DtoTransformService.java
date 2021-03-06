package com.beaniv.giveaway.util.dtotransformservice;

import com.beaniv.giveaway.model.dto.post.DetailedPostDto;
import com.beaniv.giveaway.model.dto.post.HomescreenPostDto;
import com.beaniv.giveaway.model.dto.post.RegisterPostDto;
import com.beaniv.giveaway.model.dto.user.UserDto;
import com.beaniv.giveaway.model.entity.Post;
import com.beaniv.giveaway.model.entity.User;

import java.util.Set;

public interface DtoTransformService {
    User convertToUser(UserDto userDto);

    Post convertToPost(RegisterPostDto registerPostDto);

    DetailedPostDto convertToDetailedPostDto(Post post, int userId);

    Set<HomescreenPostDto> convertToSetHomescreenPostDto(Set<Post> posts);
}