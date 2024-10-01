package com.example.storycraft.service;

import com.example.storycraft.dao.UserDao;
import com.example.storycraft.model.User;

import java.sql.Timestamp;
import java.util.Map;

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
        String reason = "사용자 요청"; // 사용자 비활성화 사유
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        boolean success = userDao.updateAccountStatus(userId, "N", reason, currentTimestamp, "USER");
        if (!success) {
            throw new RuntimeException("사용자에 의한 계정 비활성화에 실패했습니다.");
        }
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

    //카카오톡
    public void saveKakaoUser(Map<String, Object> userInfo) {
        String kakaoId = userInfo.get("id").toString();

        // 먼저 사용자가 이미 존재하는지 확인
        User existingUser = userDao.findUserByUsername(kakaoId);

        if (existingUser != null) {
            // 사용자가 이미 존재하는 경우, 로그인을 처리합니다.
            System.out.println("User already exists with ID: " + kakaoId);
            // 로그인을 위한 추가 처리를 여기서 할 수 있습니다 (세션 설정 등).
            return; // 새로운 사용자 추가 없이 로그인 처리만 함.
        }

        // 새 사용자 삽입
        User user = new User();
        user.setuId(kakaoId);  // 카카오 ID
        user.setuName(userInfo.get("nickname").toString());  // 카카오 닉네임
        user.setuCertified("CA-02");  // 카카오 회원가입 코드
        user.setuActivate("Y");  // 활성화 상태
        user.setMainComplete("N");  // 메인 스토리 미완료
        user.setuCode("CU-01");  // 회원 코드
        user.setuDstatus("N");  // 상태 N
        user.setuCdate(new Timestamp(System.currentTimeMillis()));  // 가입 시간 설정

        // 이메일 정보 저장
        if (userInfo.get("email") != null) {
            user.setuEmail(userInfo.get("email").toString());
        }

        // 데이터베이스에 사용자 추가
        userDao.insertKakaoUser(user);  

        // 새로운 사용자 로그인 처리 (세션 설정 등)
    }
    
 // 계정 비활성화 (관리자에 의한 비활성화)
    public void deactivateAccountByAdmin(String userId, String reason) {
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        boolean success = userDao.updateAccountStatus(userId, "N", reason, currentTimestamp, "ADMIN");
        if (!success) {
            throw new RuntimeException("관리자에 의한 계정 비활성화에 실패했습니다.");
        }
    }

    // 계정 비활성화 (사용자 스스로 비활성화)
    public void deactivateAccountByUser(String userId, String reason) {
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        boolean success = userDao.updateAccountStatus(userId, "N", reason, currentTimestamp, "USER");
        if (!success) {
            throw new RuntimeException("사용자에 의한 계정 비활성화에 실패했습니다.");
        }
    }
}

