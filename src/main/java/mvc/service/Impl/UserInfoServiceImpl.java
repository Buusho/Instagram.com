package mvc.service.Impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mvc.entiity.UserInfo;
import mvc.repo.UserInfoRepository;
import mvc.repo.UserRepository;
import mvc.service.UserInfoService;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserInfoServiceImpl implements UserInfoService {
    private final UserInfoRepository userInfoRepository;
    @Override
    public boolean findUserInfoByUserId(Long userId, Long userInfoId) {
        return userInfoRepository.findUserInfoByUserId(userId,userInfoId);
    }

    @Override
    public String updateUserInfoBYId(Long id, UserInfo newUserInfo) {
        return userInfoRepository.updateUserInfoBYId(id,newUserInfo);
    }

    @Override
    public String changeImageByUserInfoId(Long userInfoId, UserInfo newUserInfo) {
        return userInfoRepository.changeImageByUserInfoId(userInfoId,newUserInfo);
    }

    @Override
    public String deleteImageByUserInfoId(Long Id) {
        return userInfoRepository.deleteImageByUserInfoId(Id);
    }
}
