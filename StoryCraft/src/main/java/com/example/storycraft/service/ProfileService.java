package com.example.storycraft.service;

import com.example.storycraft.model.Profile;
import com.example.storycraft.model.Story;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@Service
public class ProfileService {

    // 업로드 디렉토리를 직접 지정합니다.
    private static final String UPLOAD_DIRECTORY = "D:/upload/";

    @Autowired
    private JdbcTemplate jdbcTemplate;  // JdbcTemplate 의존성 주입

    // 사용자 프로필 조회 (DB에서 조회하는 로직)
    public Profile findByUsername(String username) {
        String sql = "SELECT U_NICKNAME, U_GENDER, U_BIRTHDAY, U_ITD, U_PROFILE FROM USERS WHERE U_ID = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{username}, (rs, rowNum) -> {
            Profile profile = new Profile();
            profile.setUsername(username);
            profile.setNickname(rs.getString("U_NICKNAME"));
            profile.setGender(rs.getString("U_GENDER"));
            // 생일 필드를 Timestamp로 받아 Date로 설정
            Timestamp birthdayTimestamp = rs.getTimestamp("U_BIRTHDAY");
            if (birthdayTimestamp != null) {
                profile.setBirthday(new java.util.Date(birthdayTimestamp.getTime()));
            }
            profile.setBio(rs.getString("U_ITD"));
            profile.setProfilePicture(rs.getString("U_PROFILE"));
            return profile;
        });
    }

    // 프로필 업데이트 (닉네임, 성별, 생일, 한 줄 소개)
    public void updateProfile(Profile updatedProfile) {
        String sql = "UPDATE USERS SET U_NICKNAME = ?, U_GENDER = ?, U_BIRTHDAY = ?, U_ITD = ? WHERE U_ID = ?";
        try {
            // 생일을 Timestamp로 변환
            Timestamp birthday = null;
            if (updatedProfile.getBirthday() != null) {
                birthday = new Timestamp(updatedProfile.getBirthday().getTime());
            }

            jdbcTemplate.update(sql, updatedProfile.getNickname(), updatedProfile.getGender(),
                    birthday, updatedProfile.getBio(), updatedProfile.getUsername());

            System.out.println("프로필이 DB에 업데이트되었습니다: " + updatedProfile.getUsername());
        } catch (Exception e) {
            System.err.println("프로필 업데이트 중 오류가 발생했습니다: " + e.getMessage());
            throw new RuntimeException("DB 업데이트 실패");
        }
    }

    // 프로필 사진 업로드 및 이미지 경로 반환
    public String uploadProfilePhoto(String username, MultipartFile file) {
        String originalFileName = file.getOriginalFilename();
        String fileName = System.currentTimeMillis() + "_" + originalFileName;

        File uploadFile = new File(UPLOAD_DIRECTORY, fileName);
        try {
            // 디렉토리가 없는 경우 생성
            if (!uploadFile.getParentFile().exists()) {
                uploadFile.getParentFile().mkdirs();
            }
            file.transferTo(uploadFile);

            // 데이터베이스에 저장할 경로 (Resource Handler의 매핑과 일치해야 함)
            String filePath = "/profile-images/" + fileName;

            String sql = "UPDATE USERS SET U_PROFILE = ? WHERE U_ID = ?";
            jdbcTemplate.update(sql, filePath, username);

            System.out.println("프로필 사진이 저장되었습니다: " + fileName);

            // 업로드된 이미지의 경로 반환 (캐시 방지를 위해 timestamp 추가)
            return filePath + "?t=" + System.currentTimeMillis();
        } catch (IOException e) {
            throw new RuntimeException("프로필 사진 업로드 중 오류가 발생했습니다.", e);
        }
    }

    // 특정 사용자가 작성한 스토리 목록 조회 (임시 구현)
    public List<Story> findCreatedStoriesByUser(String username) {
        // 여기에 실제 DB 쿼리 추가 가능
        return List.of();  // 임시로 빈 리스트 반환
    }

    // 특정 사용자가 좋아요한 스토리 목록 조회 (임시 구현)
    public List<Story> findLikedStoriesByUser(String username) {
        // 여기에 실제 DB 쿼리 추가 가능
        return List.of();  // 임시로 빈 리스트 반환
    }

    // 사용자 신고 처리 (임시 구현)
    public void reportUser(String reportedUsername, String reason) {
        // 신고 처리 로직
        System.out.println("사용자가 신고되었습니다: " + reportedUsername + " - 사유: " + reason);
    }

    // 로그인한 사용자의 이름 반환 (임시 처리)
    public String getLoggedInUsername() {
        return "loggedInUser";  // 실제로 인증 시스템과 연동 필요
    }
}
