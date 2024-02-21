package mvc.repo.Impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mvc.entiity.Follower;
import mvc.entiity.User;
import mvc.entiity.UserInfo;
import mvc.repo.FollowerRepository;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Transactional
@RequiredArgsConstructor
public class FollowerRepositoryImpl  implements FollowerRepository {
    @PersistenceContext
    private final EntityManager entityManager;
    @Override
    public List<User> search(User userName, UserInfo fullName) {

        return null;
    }

    @Override
    public User subscribe(User profile) {
        User currentUser = getCurrentUser();

        // Проверяем, является ли профиль уже подпиской текущего пользователя
        if (currentUser.getFollower().getSubscriptions().contains(profile)) {
            // Профиль уже есть в подписках, удаляем его
            currentUser.getFollower().getSubscriptions().remove(profile);
            profile.getFollower().getSubscribers().remove(currentUser);
        } else {
            // Профиля еще нет в подписках, добавляем его
            currentUser.getFollower().getSubscriptions().add(profile.getId());
            profile.getFollower().getSubscribers().add(currentUser.getId());
        }

        // Сохраняем изменения в базе данных
        entityManager.merge(currentUser);
        entityManager.merge(profile);

        return profile;
    }
    // Метод для получения текущего пользователя (вы можете реализовать его по вашему усмотрению)
    private User getCurrentUser() {
        // Реализуйте этот метод в соответствии с вашей логикой аутентификации и управления пользователями
        // Здесь можно использовать Spring Security или другие средства для получения текущего пользователя
        // Возвратим заглушку для примера
        return new User();
    }

    @Override
    public Follower getAllSubscribersByUserId(Long userId) {
        String queryString = "SELECT f FROM Follower f JOIN f.user u WHERE u.id = :userId";
        TypedQuery<Follower> query = entityManager.createQuery(queryString, Follower.class);
        query.setParameter("userId", userId);
        return query.getSingleResult();
    }

    @Override
    public Follower getAllSubscriptionsByUserId(Long userId) {
        String queryString = "SELECT f FROM Follower f JOIN f.user u WHERE u.id = :userId";
        Query<Follower> query = (Query<Follower>) entityManager.createQuery(queryString, Follower.class);
        query.setParameter("userId", userId);
        return (Follower) query.getResultList();
    }
}
