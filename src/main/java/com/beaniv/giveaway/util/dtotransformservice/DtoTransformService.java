package com.beaniv.giveaway.util.dtotransformservice;

import com.beaniv.giveaway.model.dto.post.DetailedPostDto;
import com.beaniv.giveaway.model.dto.user.UserDto;
import com.beaniv.giveaway.model.entity.Post;
import com.beaniv.giveaway.model.entity.User;

import java.util.Set;

public interface DtoTransformService {
    User convertToUser(UserDto userDto);

    DetailedPostDto convertToPostInfoDto(Post post);

    Post convertToPost(DetailedPostDto detailedPostDto);

    Set<DetailedPostDto> convertToSetPostInfoDto(Set<Post> posts);
}