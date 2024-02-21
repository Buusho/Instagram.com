package mvc.service.Impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mvc.entiity.Post;
import mvc.repo.PostRepository;
import mvc.service.PostService;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    @Override
    public Post findPostById(Long id) {
        return postRepository.findPostById(id);
    }

    @Override
    public String updatePostById(Long id, Post newPost) {
        return postRepository.updatePostById(id,newPost);
    }

    @Override
    public void addUserToPost(Long postId, Long userId) {
postRepository.addUserToPost(postId,userId);
    }
}
