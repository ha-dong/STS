// ProfileDao.java

package com.example.storycraft.dao;

import com.example.storycraft.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Optional;

@Repository
public class ProfileDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 사용자 프로필 조회 (USERS 테이블에서 조회)
    public Optional<Profile> findByUsername(String username) {
        String sql = "SELECT U_EMAIL, U_NICKNAME, U_GENDER, U_BIRTHDAY, U_ITD, U_PROFILE FROM USERS WHERE U_ID = ?";
        try {
            Profile profile = jdbcTemplate.queryForObject(sql, new Object[]{username}, (rs, rowNum) -> {
                Profile p = new Profile();
                p.setUsername(username);
                p.setEmail(rs.getString("U_EMAIL"));
                p.setNickname(rs.getString("U_NICKNAME"));
                p.setGender(rs.getString("U_GENDER"));

                Timestamp birthdayTimestamp = rs.getTimestamp("U_BIRTHDAY");
                if (birthdayTimestamp != null) {
                    p.setBirthday(new java.util.Date(birthdayTimestamp.getTime()));
                }

                p.setBio(rs.getString("U_ITD"));
                p.setProfilePicture(rs.getString("U_PROFILE")); // 프로필 사진 경로 설정
                return p;
            });
            return Optional.ofNullable(profile);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    // 프로필 업데이트 (USERS 테이블)
    public int updateProfile(Profile updatedProfile) {
        String sql = "UPDATE USERS SET U_EMAIL = ?, U_NICKNAME = ?, U_GENDER = ?, U_BIRTHDAY = ?, U_ITD = ? WHERE U_ID = ?";
        // 생일을 Timestamp로 변환
        Timestamp birthday = null;
        if (updatedProfile.getBirthday() != null) {
            birthday = new Timestamp(updatedProfile.getBirthday().getTime());
        }

        int rowsAffected = jdbcTemplate.update(sql, updatedProfile.getEmail(), updatedProfile.getNickname(),
                updatedProfile.getGender(), birthday, updatedProfile.getBio(), updatedProfile.getUsername());
        System.out.println("Profile update - Rows affected: " + rowsAffected);
        return rowsAffected;
    }

    // 프로필 사진 경로 업데이트 (USERS 테이블)
    public void updateProfilePicture(String username, String profilePicturePath) {
        String sql = "UPDATE USERS SET U_PROFILE = ? WHERE U_ID = ?";
        jdbcTemplate.update(sql, profilePicturePath, username);
    }

}
