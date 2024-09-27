// ProfileService.java

package com.example.storycraft.service;

import com.example.storycraft.dao.ProfileDao;
import com.example.storycraft.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Service
public class ProfileService {

    // 업로드 디렉토리를 직접 지정합니다.
    private static final String UPLOAD_DIRECTORY = "C:/embeded/upload/";

    @Autowired
    private ProfileDao profileDao;

    public Profile getProfileByUsername(String username) {
        // 프로필이 없을 경우 빈 객체 반환
        return profileDao.findByUsername(username).orElse(new Profile());
    }

    // 프로필 업데이트 또는 삽입
    public void updateProfile(Profile updatedProfile) {
        int rows = profileDao.updateProfile(updatedProfile);
        if (rows == 0) {
            throw new RuntimeException("Profile update failed: No rows affected.");
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

            profileDao.updateProfilePicture(username, filePath);

            System.out.println("프로필 사진이 저장되었습니다: " + fileName);

            // 업로드된 이미지의 경로 반환
            return filePath;
        } catch (IOException e) {
            throw new RuntimeException("프로필 사진 업로드 중 오류가 발생했습니다.", e);
        }
    }


    // 사용자 신고 처리
    public void reportUser(String reportedUsername, String reason) {
        // 신고 처리 로직
        System.out.println("사용자가 신고되었습니다: " + reportedUsername + " - 사유: " + reason);
    }
}
