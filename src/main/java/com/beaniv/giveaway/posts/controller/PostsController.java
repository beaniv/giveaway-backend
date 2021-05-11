package com.beaniv.giveaway.posts.controller;

import com.beaniv.giveaway.authentication.security.jwt.JwtUser;
import com.beaniv.giveaway.model.dto.post.HomescreenPostDto;
import com.beaniv.giveaway.model.dto.post.PostIdDto;
import com.beaniv.giveaway.model.dto.post.DetailedPostDto;
import com.beaniv.giveaway.posts.PostsService;
import com.beaniv.giveaway.util.dtotransformservice.DtoTransformService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/v1/posts")
@RequiredArgsConstructor
@Slf4j
public class PostsController {
    private final PostsService postsService;

    private final DtoTransformService dtoTransformService;

    @GetMapping("/get-posts")
    @ApiOperation(value = "Получить все посты", response = HomescreenPostDto.class, responseContainer = "Set")
    public Set<HomescreenPostDto> getPosts() {
        return postsService.getPosts();
    }

    @GetMapping("/get-my-posts")
    @ApiOperation(value = "Получить посты в которых участвует пользователь", response = HomescreenPostDto.class, responseContainer = "Set")
    public Set<HomescreenPostDto> getMyPosts(@AuthenticationPrincipal JwtUser user) {
        int userId = user.getId();
        return postsService.getUserPosts(userId);
    }

    @PostMapping("/add-post")
    @ApiOperation("Добавление поста")
    public void addPost(
            @ApiParam(required = true, value = "Информация о посте")
            @AuthenticationPrincipal JwtUser user,
            @RequestBody DetailedPostDto detailedPostDto) {
        int userId = user.getId();
        var post = dtoTransformService.convertToPost(detailedPostDto);
        post.setCreatorId(userId);
        postsService.addPost(post);
    }

    @PostMapping("/add-user-to-post")
    @ApiOperation("Добавление участника в конкурс")
    public void addUserToPost(
            @ApiParam(required = true, value = "ID пользователя и ID поста")
            @AuthenticationPrincipal JwtUser user,
            @RequestBody PostIdDto postIdDto) {
        int userId = user.getId();
        int postId = postIdDto.getPostID();
        postsService.addUserToPost(userId, postId);
    }

    @DeleteMapping("/remove-user-from-post")
    @ApiOperation("Удаление участника из конкурса")
    public void removeUserFromPost(
            @ApiParam(required = true, value = "ID пользователя и ID поста")
            @AuthenticationPrincipal JwtUser user,
            @RequestBody PostIdDto postIdDto) {
        int userId = user.getId();
        int postId = postIdDto.getPostID();
        postsService.removeUserFromPost(userId, postId);
    }
}
