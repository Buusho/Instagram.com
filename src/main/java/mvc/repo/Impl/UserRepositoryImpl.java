package mvc.repo.Impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mvc.entiity.Follower;
import mvc.entiity.User;
import mvc.entiity.UserInfo;
import mvc.repo.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
@Transactional
@RequiredArgsConstructor
public class UserRepositoryImpl  implements UserRepository {
    @PersistenceContext
private final EntityManager entityManager;

    @Override
    public String signUpWithInfoAndFollowers(User user) {
        try {
            if (user.getPhone_number() != null && !user.getPhone_number().isEmpty()) {
                String formattedPhoneNumber = user.getPhone_number().replaceAll("\\D", "");
                if (!formattedPhoneNumber.startsWith("+996")) {
                    formattedPhoneNumber = "+996" + formattedPhoneNumber;
                }
                user.setPhone_number(formattedPhoneNumber);
            }
            UserInfo userInfo = user.getUserInfo();
            userInfo.setFull_name(null);
            userInfo.setBiography(null);
            userInfo.setGender(null);
            userInfo.setImage(null);
            entityManager.persist(userInfo);

            Follower follower = new Follower();
            follower.setUser(user);
            follower.setSubscribers(new ArrayList<>()); // Начальное значение подписчиков - пустой список
            follower.setSubscriptions(new ArrayList<>()); // Начальное значение подписок - пустой список
            entityManager.persist(follower);

            if (user.getUser_name().matches("^[a-zA-Z0-9_.]+$")) {
                user.setUser_name(user.getUser_name());
            } else {
                System.out.println("Имя пользователя должно содержать только буквы, цифры, символы нижнего подчеркивания (_) и точки (.)");
                throw new IllegalArgumentException("Имя пользователя должно содержать только буквы, цифры, символы нижнего подчеркивания (_) и точки (.)");
            }

            entityManager.persist(user);
            return "User signed up successfully!";
        } catch (Exception e){
        if (entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().rollback();
        }
    }
        return "failed";

    }

    @Override
    public String signInByUserId(Long id) {
        return null;
    }

    @Override
    public User findUserById(Long id) {
        try {

            return entityManager.find(User.class, id);
        }catch (Exception e){
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        }
        return null;
    }
    @Override
    public String updateUserById(Long id, User newUser) {
        try {
            User user = findUserById(id);
            user.setUser_name(newUser.getUser_name());
            user.setPassword(newUser.getPassword());
            user.setEmail(newUser.getEmail());
            user.setPhone_number(newUser.getPhone_number());
            entityManager.merge(user);
            return "updated successfully";
        }catch (Exception e){
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        }
        return "failed";
    }

    @Override
    public boolean deleteUserById(Long id) {
        try {
            entityManager.remove(entityManager.find(User.class, id));
        }catch (Exception e){
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        }
        return false;
    }

    @Override
    public List<User> userProfile() {
return entityManager.createQuery("select s  from User s ",User.class).getResultList();
    }
}
