package mvc.repo.Impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mvc.entiity.User;
import mvc.entiity.UserInfo;
import mvc.repo.UserInfoRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
@RequiredArgsConstructor
public class UserInfoRepositoryImpl implements UserInfoRepository {
    @PersistenceContext
    private final   EntityManager entityManager;
    @Override
    public boolean findUserInfoByUserId(Long userId, Long userInfoId) {
        try {
            User user = entityManager.find(User.class, userId);
            return user.getUserInfo().getId().equals(userInfoId);
//        if (user != null) {
//            UserInfo userInfo = user.getUserInfo();
//            if (userInfo != null && userInfo.getId().equals(userInfoId)) {
//                return userInfo.toString(); // Или возвращайте информацию о пользователе в удобном для вас формате
//            } else {
//                return "Information about the user with userId: " + userId + " and userInfoId: " + userInfoId + " not found.";
//            }
//        }else {
//            return "User with userId: " + userId + " not found.";
//        }
        }catch (Exception e){
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        }
        return false;
    }

    @Override
    public String updateUserInfoBYId(Long id, UserInfo newUserInfo) {
        try {
            UserInfo userInfo = entityManager.find(UserInfo.class, id);
            userInfo.setFull_name(newUserInfo.getFull_name());
            userInfo.setBiography(newUserInfo.getBiography());
            userInfo.setGender(newUserInfo.getGender());
            userInfo.setImage(newUserInfo.getImage());
            entityManager.merge(userInfo);
            return "updated successfully";
        }catch (Exception e){
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        }
        return "failed";
    }
    @Override
    public String changeImageByUserInfoId(Long userInfoId, UserInfo newUserInfo) {
        try {
            UserInfo userInfo = entityManager.find(UserInfo.class, userInfoId);
            userInfo.setImage(newUserInfo.getImage());
            entityManager.merge(userInfo);
            return "updated successfully";
        }catch (Exception e){
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        }
        return "failed";
    }

    @Override
    public String deleteImageByUserInfoId(Long Id) {
        try {


            UserInfo userInfo = entityManager.find(UserInfo.class, Id);
            entityManager.remove(userInfo.getImage());
            return "updated successfully";
        }catch (Exception e){
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        }
        return "failed";
    }

}
