package com.beaniv.giveaway.posts;

import com.beaniv.giveaway.model.dto.post.DetailedPostDto;
import com.beaniv.giveaway.model.entity.Post;
import com.beaniv.giveaway.model.entity.User;

import java.util.Set;

public interface PostsService {
    Set<DetailedPostDto> getPosts();

    Post addPost(Post post);

    User addUserToPost(int userID, int postID);

    User removeUserFromPost(int userID, int postID);

    Set<DetailedPostDto> getUserPosts(int userID);
}
