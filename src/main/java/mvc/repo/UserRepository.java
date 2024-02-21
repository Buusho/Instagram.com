package mvc.repo;

import mvc.entiity.User;

import java.util.List;

public interface UserRepository {
String signUpWithInfoAndFollowers (User user );

String signInByUserId(Long id);

User findUserById(Long id);
String updateUserById(Long id,User newUser);

boolean deleteUserById(Long id);

List<User> userProfile();


}
