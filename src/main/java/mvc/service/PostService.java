package mvc.service;

import mvc.entiity.Post;

public interface PostService {
    Post findPostById(Long id);

    String updatePostById(Long id, Post newPost);

    void addUserToPost(Long postId, Long userId);
}
