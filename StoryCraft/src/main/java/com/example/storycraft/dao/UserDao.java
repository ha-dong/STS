// UserDao.java

package com.example.storycraft.dao;

import com.example.storycraft.model.User;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean insertUser(User user) {
        String sql = "INSERT INTO USERS (U_ID, U_PW, U_NAME, U_PHONE, U_ADD, U_PROFILE, U_EMAIL, U_CERTIFIED, U_CDATE, U_ACTIVATE, U_NICKNAME, U_ITD, U_BIRTHDAY, U_GENDER, MAIN_COMPLETE, U_CODE, U_DSTATUS) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            int result = jdbcTemplate.update(sql, user.getuId(), user.getuPw(), user.getuName(), user.getuPhone(),
                                       user.getuAdd(), user.getuProfile(), user.getuEmail(), user.getuCertified(),
                                       user.getuCdate(), user.getuActivate(), user.getuNickname(),
                                       user.getuItd(), user.getuBirthday(), user.getuGender(),
                                       user.getMainComplete(), user.getuCode(), user.getuDstatus());
            return result > 0;
        } catch (DataAccessException e) {
            System.err.println("Error inserting user: " + e.getMessage());
            return false;
        }
    }

    public boolean isUserIdAvailable(String userId) {
        String sql = "SELECT COUNT(*) FROM USERS WHERE U_ID = ?";
        try {
            Integer count = jdbcTemplate.queryForObject(sql, Integer.class, userId);
            return count != null && count == 0;
        } catch (DataAccessException e) {
            System.err.println("Error checking user ID availability: " + e.getMessage());
            return false;
        }
    }

    public boolean isMainStoryCompleted(String userId) {
        String sql = "SELECT MAIN_COMPLETE FROM USERS WHERE U_ID = ?";
        try {
            String mainComplete = jdbcTemplate.queryForObject(sql, String.class, userId);
            return "Y".equals(mainComplete);
        } catch (DataAccessException e) {
            System.err.println("Error checking main story completion: " + e.getMessage());
            return false;
        }
    }

    public boolean isUserStoryUnlocked(String userId) {
        return isMainStoryCompleted(userId);
    }

    public boolean unlockUserStory(String userId) {
        String sql = "UPDATE USERS SET MAIN_COMPLETE = 'Y' WHERE U_ID = ?";
        try {
            int result = jdbcTemplate.update(sql, userId);
            return result > 0;
        } catch (DataAccessException e) {
            System.err.println("Error unlocking user story: " + e.getMessage());
            return false;
        }
    }

    // 계정 비활성화 (삭제) 처리
    // 3-파라미터 메서드 수정: 기본 사유와 비활성화자 설정 후 업데이트
    public boolean updateAccountStatus(String userId, String activateStatus, Timestamp deactivateDate) {
        String defaultReason = "비공개 사유"; // 기본 비활성화 사유
        String defaultDeactivatedBy = "SYSTEM"; // 기본 비활성화자
        String sql = "UPDATE USERS SET U_ACTIVATE = ?, U_DREASON = ?, DEACTIVATED_BY = ?, U_DDATE = ? WHERE U_ID = ?";
        try {
            int result = jdbcTemplate.update(sql, activateStatus, defaultReason, defaultDeactivatedBy, deactivateDate, userId);
            return result > 0;
        } catch (DataAccessException e) {
            System.err.println("Error updating account status: " + e.getMessage());
            return false;
        }
    }

    // 5-파라미터 메서드 유지
    public boolean updateAccountStatus(String userId, String activateStatus, String reason, Timestamp deactivateDate, String deactivatedBy) {
        String sql = "UPDATE USERS SET U_ACTIVATE = ?, U_DREASON = ?, DEACTIVATED_BY = ?, U_DDATE = ? WHERE U_ID = ?";
        try {
            int result = jdbcTemplate.update(sql, activateStatus, reason, deactivatedBy, deactivateDate, userId);
            return result > 0;
        } catch (DataAccessException e) {
            System.err.println("Error updating account status: " + e.getMessage());
            return false;
        }
    }

    public User findUserByUsername(String username) {
        String sql = "SELECT * FROM USERS WHERE U_ID = ?";
        try {
            List<User> users = jdbcTemplate.query(sql, new Object[]{username}, (rs, rowNum) -> {
                User user = new User();
                user.setuId(rs.getString("U_ID"));
                user.setuPw(rs.getString("U_PW"));
                user.setuName(rs.getString("U_NAME"));
                user.setuPhone(rs.getString("U_PHONE"));
                user.setuAdd(rs.getString("U_ADD"));
                user.setuProfile(rs.getString("U_PROFILE"));
                user.setuEmail(rs.getString("U_EMAIL"));
                user.setuCertified(rs.getString("U_CERTIFIED"));
                user.setuCdate(rs.getTimestamp("U_CDATE"));
                user.setuActivate(rs.getString("U_ACTIVATE"));
                user.setuNickname(rs.getString("U_NICKNAME"));
                user.setuItd(rs.getString("U_ITD"));
                user.setuBirthday(rs.getTimestamp("U_BIRTHDAY"));
                user.setuGender(rs.getString("U_GENDER"));
                user.setMainComplete(rs.getString("MAIN_COMPLETE"));
                user.setuCode(rs.getString("U_CODE"));
                user.setuDstatus(rs.getString("U_DSTATUS"));
                user.setuDreason(rs.getString("U_DREASON")); // 추가
                user.setDeactivatedBy(rs.getString("DEACTIVATED_BY")); // 추가
                return user;
            });
            
            return users.isEmpty() ? null : users.get(0);
        } catch (DataAccessException e) {
            System.err.println("Error finding user by username: " + e.getMessage());
            return null;
        }
    }

    // 메인 스토리 완료 상태 업데이트
    public boolean updateMainComplete(String userId, String status) {
        String sql = "UPDATE USERS SET MAIN_COMPLETE = ? WHERE U_ID = ?";
        try {
            int result = jdbcTemplate.update(sql, status, userId);
            return result > 0;
        } catch (DataAccessException e) {
            System.err.println("Error updating MAIN_COMPLETE: " + e.getMessage());
            return false;
        }
    }
    
    public String findIdByUsernameAndEmail(String username, String email) {
        String sql = "SELECT U_ID FROM USERS WHERE TRIM(LOWER(U_NAME)) = TRIM(LOWER(?)) AND TRIM(LOWER(U_EMAIL)) = TRIM(LOWER(?))";
        System.out.println("Executing SQL: " + sql + " with username = " + username + " and email = " + email);
        try {
            String userId = jdbcTemplate.queryForObject(sql, new Object[]{username.trim(), email.trim()}, String.class);
            System.out.println("Found user ID: " + userId);
            return userId;
        } catch (EmptyResultDataAccessException e) {
            System.err.println("No user found with given username and email: " + e.getMessage());
            return null;
        } catch (DataAccessException e) {
            System.err.println("Error accessing data: " + e.getMessage());
            return null;
        }
    }
    
    // 비밀번호 변경 메서드
    public User findUserByIdAndEmail(String userId, String email) {
        String sql = "SELECT * FROM USERS WHERE U_ID = ? AND U_EMAIL = ?";
        try {
            List<User> users = jdbcTemplate.query(sql, new Object[]{userId, email}, (rs, rowNum) -> {
                User user = new User();
                user.setuId(rs.getString("U_ID"));
                user.setuPw(rs.getString("U_PW"));
                user.setuName(rs.getString("U_NAME"));
                user.setuPhone(rs.getString("U_PHONE"));
                user.setuAdd(rs.getString("U_ADD"));
                user.setuProfile(rs.getString("U_PROFILE"));
                user.setuEmail(rs.getString("U_EMAIL"));
                user.setuCertified(rs.getString("U_CERTIFIED"));
                user.setuCdate(rs.getTimestamp("U_CDATE"));
                user.setuActivate(rs.getString("U_ACTIVATE"));
                user.setuNickname(rs.getString("U_NICKNAME"));
                user.setuItd(rs.getString("U_ITD"));
                user.setuBirthday(rs.getTimestamp("U_BIRTHDAY"));
                user.setuGender(rs.getString("U_GENDER"));
                user.setMainComplete(rs.getString("MAIN_COMPLETE"));
                user.setuCode(rs.getString("U_CODE"));
                user.setuDstatus(rs.getString("U_DSTATUS"));
                user.setuDreason(rs.getString("U_DREASON")); // 추가
                user.setDeactivatedBy(rs.getString("DEACTIVATED_BY")); // 추가
                return user;
            });
            
            return users.isEmpty() ? null : users.get(0);
        } catch (DataAccessException e) {
            System.err.println("Error finding user by ID and email: " + e.getMessage());
            return null;
        }
    }

    // 인증 토큰 업데이트 메서드 추가
    public void updateAuthToken(String userId, String authToken, Timestamp expiryTime) {
        String sql = "UPDATE USERS SET AUTH_TOKEN = ?, TOKEN_EXPIRY = ? WHERE U_ID = ?";
        jdbcTemplate.update(sql, authToken, expiryTime, userId);
    }
    
    // 이메일과 토큰으로 사용자를 찾는 메서드
    public User findUserByEmailAndToken(String email, String token) {
        String sql = "SELECT * FROM USERS WHERE U_EMAIL = ? AND AUTH_TOKEN = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{email, token}, (rs, rowNum) -> {
            User user = new User();
            user.setuId(rs.getString("U_ID"));
            user.setuEmail(rs.getString("U_EMAIL"));
            // 필요한 다른 필드들 설정...
            user.setuDreason(rs.getString("U_DREASON")); // 추가
            user.setDeactivatedBy(rs.getString("DEACTIVATED_BY")); // 추가
            return user;
        });
    }
    
    // 비밀번호를 업데이트하는 메서드
    public void updatePassword(String userId, String newPassword) {
        String sql = "UPDATE USERS SET U_PW = ? WHERE U_ID = ?";
        jdbcTemplate.update(sql, newPassword, userId);
    }

    // 카카오 로그인용 insertUser 메서드
    public void insertKakaoUser(User user) {
        String sql = "INSERT INTO USERS (U_ID, U_NAME, U_EMAIL, U_CERTIFIED, U_CDATE, U_ACTIVATE, MAIN_COMPLETE, U_CODE, U_DSTATUS) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql, user.getuId(), user.getuName(), user.getuEmail(), user.getuCertified(), 
                            user.getuCdate(), user.getuActivate(), user.getMainComplete(), 
                            user.getuCode(), user.getuDstatus());
    }
    
    // 계정 비활성화 (관리자에 의한 비활성화)
    public boolean deactivateAccountByAdmin(String userId, String reason) {
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        return updateAccountStatus(userId, "N", reason, currentTimestamp, "ADMIN");
    }

    // 계정 비활성화 (사용자 스스로 비활성화)
    public boolean deactivateAccountByUser(String userId, String reason) {
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        return updateAccountStatus(userId, "N", reason, currentTimestamp, "USER");
    }
}
