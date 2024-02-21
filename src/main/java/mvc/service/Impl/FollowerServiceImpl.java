package mvc.service.Impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mvc.entiity.Follower;
import mvc.entiity.User;
import mvc.entiity.UserInfo;
import mvc.repo.FollowerRepository;
import mvc.service.FollowerService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
public class FollowerServiceImpl implements FollowerService {
    private final FollowerRepository followerRepository;
    @Override
    public List<User> search(User userName, UserInfo fullName) {
        return followerRepository.search(userName,fullName);
    }

    @Override
    public User subscribe(User profile) {
        return followerRepository.subscribe(profile);
    }

    @Override
    public Follower getAllSubscribersByUserId(Long userId) {
        return followerRepository.getAllSubscribersByUserId(userId);
    }

    @Override
    public Follower getAllSubscriptionsByUserId(Long userId) {
        return followerRepository.getAllSubscriptionsByUserId(userId);
    }
}
