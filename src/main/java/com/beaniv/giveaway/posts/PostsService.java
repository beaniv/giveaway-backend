package com.beaniv.giveaway.posts;

import com.beaniv.giveaway.model.dto.post.HomescreenPostDto;
import com.beaniv.giveaway.model.entity.Post;

import java.util.Set;

public interface PostsService {
    Set<HomescreenPostDto> getPosts();

    Set<HomescreenPostDto> getUserPosts(int userID);

    void addPost(Post post);

    void addUserToPost(int userID, int postID);

    void removeUserFromPost(int userID, int postID);
}
