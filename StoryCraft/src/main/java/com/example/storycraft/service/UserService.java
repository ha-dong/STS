package com.example.storycraft.service;

import com.example.storycraft.dao.UserDao;
import com.example.storycraft.model.User;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public void addUser(User user) {
        user.setuCertified("CA-01");
        user.setuCode("CU-01");
        user.setuDstatus("N");
        user.setMainComplete("N");
        userDao.insertUser(user);
    }

    public boolean isUserIdAvailable(String userId) {
        return userDao.isUserIdAvailable(userId);
    }

    public User findUserByUsername(String username) {
        return userDao.findUserByUsername(username);
    }

    public boolean authenticate(String username, String password) {
        try {
            User user = userDao.findUserByUsername(username);
            if (user == null) {
                return false;
            }
            return user.getuPw().equals(password);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getNicknameByUsername(String username) {
        User user = userDao.findUserByUsername(username);
        return user != null ? user.getuNickname() : null;
    }

    public boolean isMainStoryCompleted(String userId) {
        return userDao.isMainStoryCompleted(userId);
    }

    public void unlockUserStory(String userId) {
        userDao.unlockUserStory(userId);
    }

    public boolean isUserStoryUnlocked(String userId) {
        return userDao.isUserStoryUnlocked(userId);
    }

    // 계정 비활성화 (탈퇴 처리)
    public void deleteAccount(String userId) {
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        userDao.updateAccountStatus(userId, "N", currentTimestamp);  // U_ACTIVATE를 'N'으로 변경하고 U_DDATE에 현재 시간 기록
    }

    // 계정 복구 처리
    public void reactivateAccount(String userId) {
        userDao.updateAccountStatus(userId, "Y", null);  // U_ACTIVATE를 'Y'로 변경하고 U_DDATE를 NULL로 초기화
    }

    // 메인 스토리 완료 업데이트
    public void completeMainStory(String userId) {
        userDao.updateMainComplete(userId, "Y");
    }

    public String findIdByUsernameAndEmail(String username, String email) {
        return userDao.findIdByUsernameAndEmail(username, email);
    }
    
    // 비번 변경 메서드
    public User findUserByIdAndEmail(String userId, String email) {
        return userDao.findUserByIdAndEmail(userId, email);
    }
    
    // 인증 토큰 업데이트
    public void updateUserAuthToken(String userId, String authToken, Timestamp expiryTime) {
        userDao.updateAuthToken(userId, authToken, expiryTime);
    }
    
    // 비번 변경 메서드
    public void updatePassword(String userId, String newPassword) {
        userDao.updatePassword(userId, newPassword);
    }

    // 새롭게 추가할 resetPassword 메서드
    public void resetPassword(String token, String newPassword, String email) {
        // 이메일과 토큰으로 사용자 검증 (예: DB에서 토큰과 이메일을 확인)
        User user = userDao.findUserByEmailAndToken(email, token);

        if (user != null) {
            // 비밀번호를 새로운 값으로 업데이트
            userDao.updatePassword(user.getuId(), newPassword);
        } else {
            throw new IllegalArgumentException("유효하지 않은 토큰이거나 이메일입니다.");
        }
    }
}
