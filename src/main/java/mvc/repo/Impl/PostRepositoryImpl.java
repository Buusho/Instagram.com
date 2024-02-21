package mvc.repo.Impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mvc.entiity.Image;
import mvc.entiity.Post;
import mvc.entiity.User;
import mvc.entiity.UserInfo;
import mvc.repo.PostRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
@Transactional
@RequiredArgsConstructor
public class PostRepositoryImpl  implements PostRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public String createPostWithImageAndLikes(Post post) {
        List<Image> images = post.getImages();
        if (images == null || images.isEmpty()) {
            throw new IllegalArgumentException("Post must have at least one image to be saved");
        }
        post.setLikes(new ArrayList<>());
        entityManager.persist(post);

        return "Post created successfully!";
    }

    @Override
    public Post findPostById(Long id) {
        try {
            return entityManager.find(Post.class, id);
        }catch (Exception e){
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        }
        return  null;
    }
    @Override
    public String updatePostById(Long id, Post newPost) {
        try{
        Post post = entityManager.find(Post.class,id);
        post.setTitle(newPost.getTitle());
        post.setDescription(newPost.getDescription());
        entityManager.merge(post);

            return "updated successfully";
        }catch (Exception e){
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        }
        return "failed";
    }

    @Override
    public void addUserToPost(Long postId, Long userId) {
        Post post = entityManager.find(Post.class, postId);
        if (post == null) {
            throw new EntityNotFoundException("Post with id " + postId + " not found");
        }
        User user = entityManager.find(User.class, userId);
        if (user == null) {
            throw new EntityNotFoundException("User with id " + userId + " not found");
        }
        post.addUser(user);
        entityManager.merge(post);
    }
}
