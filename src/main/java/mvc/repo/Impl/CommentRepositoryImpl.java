package mvc.repo.Impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mvc.entiity.Comment;
import mvc.entiity.Like;
import mvc.entiity.Post;
import mvc.repo.CommentRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
@Transactional
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public String saveComment(Comment comment) {
        Post post = entityManager.find(Post.class, comment.getPost().getId());
        if (post == null) {
            throw new EntityNotFoundException("Post with id " + comment.getPost().getId() + " not found");
        }
        comment.setLikes(new ArrayList<>());
        entityManager.persist(comment);

        return "Comment saved successfully!";
    }

    @Override
    public List<Comment> findAllCommentByPostId(Long postId) {
        return entityManager.createQuery("SELECT c FROM Comment c WHERE c.post.id = :postId", Comment.class)
                .setParameter("postId", postId)
                .getResultList();
    }

    @Override
    public String deleteCommentById(Long id) {

        Comment comment = entityManager.find(Comment.class, id);
        if (comment == null) {
            throw new EntityNotFoundException("Comment with id " + id + " not found");
        }
        List<Like> likes = comment.getLikes();
        for (Like like : likes) {
            entityManager.remove(like);
        }
        entityManager.remove(comment);

        return "Comment deleted successfully!";
    }
}
