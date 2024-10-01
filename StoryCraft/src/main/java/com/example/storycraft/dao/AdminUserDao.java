package com.example.storycraft.dao;

import com.example.storycraft.model.User;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AdminUserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 모든 사용자 목록 조회
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM USERS";
        try {
            return jdbcTemplate.query(sql, (rs, rowNum) -> {
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
                user.setuDdate(rs.getTimestamp("U_DDATE")); // 추가
                user.setDeactivatedBy(rs.getString("DEACTIVATED_BY")); // 추가
                return user;
            });
        } catch (DataAccessException e) {
            System.err.println("Error retrieving users: " + e.getMessage());
            return null;
        }
    }

    // 특정 사용자를 비활성화 (관리자용)
    public boolean deactivateUser(String userId, String reason) {
        String sql = "UPDATE USERS SET U_ACTIVATE = 'N', U_DREASON = ?, U_DDATE = ?, DEACTIVATED_BY = 'ADMIN' WHERE U_ID = ?";
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        try {
            int result = jdbcTemplate.update(sql, reason, currentTimestamp, userId);
            return result > 0;
        } catch (DataAccessException e) {
            System.err.println("Error deactivating user: " + e.getMessage());
            return false;
        }
    }

    // 특정 사용자 활성화 (관리자용)
    public boolean activateUser(String userId) {
        String sql = "UPDATE USERS SET U_ACTIVATE = 'Y', U_DREASON = NULL, U_DDATE = NULL, DEACTIVATED_BY = NULL WHERE U_ID = ?";
        try {
            int result = jdbcTemplate.update(sql, userId);
            return result > 0;
        } catch (DataAccessException e) {
            System.err.println("Error activating user: " + e.getMessage());
            return false;
        }
    }
}
