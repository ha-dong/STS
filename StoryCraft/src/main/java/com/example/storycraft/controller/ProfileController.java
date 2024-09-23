package com.example.storycraft.controller;

import com.example.storycraft.model.Profile;
import com.example.storycraft.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/api")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    // 특정 사용자의 프로필 페이지로 이동
    @GetMapping("/profile/{username}")
    public ModelAndView viewProfile(@PathVariable String username) {
        ModelAndView mav = new ModelAndView("profile");
        Profile profile = profileService.findByUsername(username);
        mav.addObject("profile", profile);
        mav.addObject("myStoryList", profileService.findCreatedStoriesByUser(username));
        mav.addObject("favoriteStoryList", profileService.findLikedStoriesByUser(username));
        return mav;
    }

    // 프로필 수정 페이지로 이동
    @GetMapping("/profile/edit/{username}")
    public ModelAndView editProfile(@PathVariable String username) {
        ModelAndView mav = new ModelAndView("profileedit");
        Profile profile = profileService.findByUsername(username);
        mav.addObject("profile", profile);
        return mav;
    }

    // 프로필 수정 처리
    @PostMapping("/update-profile")
    @ResponseBody
    public Map<String, String> updateProfile(@RequestBody Map<String, Object> payload) {
        Map<String, String> response = new HashMap<>();
        try {
            String username = (String) payload.get("username");
            String nickname = (String) payload.get("nickname");
            String gender = (String) payload.get("gender");
            String intro = (String) payload.get("intro");
            String birthdayStr = (String) payload.get("birthday");

            // 생일 문자열을 Date로 파싱 (포맷 수정)
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date birthday = null;
            if (birthdayStr != null && !birthdayStr.isEmpty()) {
                try {
                    birthday = dateFormat.parse(birthdayStr);
                } catch (ParseException e) {
                    response.put("success", "false");
                    response.put("message", "잘못된 날짜 형식입니다.");
                    return response;
                }
            }

            Profile profile = new Profile();
            profile.setUsername(username);
            profile.setNickname(nickname);
            profile.setGender(gender);
            profile.setIntro(intro);
            profile.setBirthday(birthday);

            profileService.updateProfile(profile);
            response.put("success", "true");
        } catch (Exception e) {
            response.put("success", "false");
            response.put("message", e.getMessage());
        }
        return response;
    }

    // 프로필 사진 업로드 처리
    @PostMapping("/upload-profile")
    @ResponseBody
    public Map<String, String> saveProfilePhoto(@RequestParam("profileImage") MultipartFile file,
                                                @RequestParam("username") String username,
                                                HttpServletRequest request) {
        Map<String, String> response = new HashMap<>();
        try {
            // 파일 업로드 처리 및 웹 접근용 URL 생성
            String fileName = profileService.uploadProfilePhoto(username, file);
            String imageUrl = "/profile-images/" + fileName;  // 웹 접근 URL 형식으로 변경
            String contextPath = request.getContextPath(); // 컨텍스트 경로 추가
            response.put("success", "true");
            response.put("profileImageUrl", contextPath + imageUrl);
        } catch (Exception e) {
            response.put("success", "false");
            response.put("message", e.getMessage());
        }
        return response;
    }

    // 자신의 프로필 페이지로 이동
    @GetMapping("/myprofile")
    public ModelAndView viewMyProfile() {
        String username = profileService.getLoggedInUsername();
        return viewProfile(username);
    }

    // 사용자의 신고 처리
    @PostMapping("/profile/report")
    public String reportUser(@RequestParam String reportedUsername, @RequestParam String reason) {
        profileService.reportUser(reportedUsername, reason);
        return "redirect:/api/profile/" + reportedUsername + "?report=success";
    }

    // 프로필 사진 업로드 페이지로 이동
    @GetMapping("/profile/photo/upload/{username}")
    public ModelAndView uploadProfilePhoto(@PathVariable String username) {
        ModelAndView mav = new ModelAndView("profilephoto");
        Profile profile = profileService.findByUsername(username);
        mav.addObject("profile", profile);
        return mav;
    }
}
