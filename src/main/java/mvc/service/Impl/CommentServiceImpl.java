package mvc.service.Impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mvc.entiity.Comment;
import mvc.repo.CommentRepository;
import mvc.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    @Override
    public String saveComment(Comment comment) {
        return commentRepository.saveComment(comment);
    }

    @Override
    public List<Comment> findAllCommentByPostId(Long postId) {
        return commentRepository.findAllCommentByPostId(postId);
    }

    @Override
    public String deleteCommentById(Long id) {
        return commentRepository.deleteCommentById(id);
    }
}
