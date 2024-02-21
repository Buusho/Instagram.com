package mvc.repo;

import mvc.entiity.Comment;

import java.util.List;

public interface CommentRepository {
    String saveComment (Comment comment);
List<Comment> findAllCommentByPostId(Long postId);

String deleteCommentById (Long id);

}
