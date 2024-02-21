package mvc.repo;

import mvc.entiity.UserInfo;

public interface UserInfoRepository {
    boolean findUserInfoByUserId(Long userId, Long userInfoId);
    String updateUserInfoBYId(Long id , UserInfo newUserInfo);

    String changeImageByUserInfoId(Long userInfoId, UserInfo newUserInfo);
    String deleteImageByUserInfoId(Long Id);

}
