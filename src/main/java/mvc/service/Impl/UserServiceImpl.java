package mvc.service.Impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mvc.entiity.User;
import mvc.repo.UserRepository;
import mvc.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public String signUpWithInfoAndFollowers(User user) {
        return userRepository.signUpWithInfoAndFollowers(user);
    }

    @Override
    public String signInByUserId(Long id) {
        return userRepository.signInByUserId(id);
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findUserById(id);
    }

    @Override
    public String updateUserById(Long id, User newUser) {
        return userRepository.updateUserById(id,newUser);
    }

    @Override
    public boolean deleteUserById(Long id) {
        return userRepository.deleteUserById(id);
    }

    @Override
    public List<User> userProfile() {
        return userRepository.userProfile();
    }
}
