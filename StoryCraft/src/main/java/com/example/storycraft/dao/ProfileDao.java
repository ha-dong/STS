package com.example.storycraft.dao;

import com.example.storycraft.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public class ProfileDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 사용자 프로필 조회 (DB에서 조회)
    public Profile findByUsername(String username) {
        String sql = "SELECT U_NICKNAME, U_GENDER, U_BIRTHDAY, U_ITD, U_PROFILE FROM USERS WHERE U_ID = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{username}, (rs, rowNum) -> {
            Profile profile = new Profile();
            profile.setUsername(username);
            profile.setNickname(rs.getString("U_NICKNAME"));
            profile.setGender(rs.getString("U_GENDER"));

            Timestamp birthdayTimestamp = rs.getTimestamp("U_BIRTHDAY");
            if (birthdayTimestamp != null) {
                profile.setBirthday(new java.util.Date(birthdayTimestamp.getTime()));
            }

            profile.setBio(rs.getString("U_ITD"));
            profile.setProfilePicture(rs.getString("U_PROFILE")); // 프로필 사진 경로 설정
            return profile;
        });
    }

    // 프로필 업데이트 (닉네임, 성별, 생일, 한 줄 소개 업데이트)
    public void updateProfile(Profile updatedProfile) {
        String sql = "UPDATE USERS SET U_NICKNAME = ?, U_GENDER = ?, U_BIRTHDAY = ?, U_ITD = ? WHERE U_ID = ?";
        // **수정된 부분: 생일을 Timestamp로 변환**
        Timestamp birthday = null;
        if (updatedProfile.getBirthday() != null) {
            birthday = new Timestamp(updatedProfile.getBirthday().getTime());
        }

        jdbcTemplate.update(sql, updatedProfile.getNickname(), updatedProfile.getGender(),
                birthday, updatedProfile.getBio(), updatedProfile.getUsername());
    }

    // 프로필 사진 경로 업데이트
    public void updateProfilePicture(String username, String profilePicturePath) {
        String sql = "UPDATE USERS SET U_PROFILE = ? WHERE U_ID = ?";
        jdbcTemplate.update(sql, profilePicturePath, username);
    }
}
