package com.beaniv.giveaway.posts;

import com.beaniv.giveaway.model.dto.post.DetailedPostDto;
import com.beaniv.giveaway.model.dto.post.HomescreenPostDto;
import com.beaniv.giveaway.model.entity.Post;

import java.util.Set;

public interface PostsService {
    Set<HomescreenPostDto> getPosts();

    Set<HomescreenPostDto> getUserPosts(int userId);

    DetailedPostDto getPost(int postId, int userId);

    void addPost(Post post);

    void addUserToPost(int userId, int postId);

    void removeUserFromPost(int userId, int postId);
}
