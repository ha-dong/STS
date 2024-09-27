package com.example.storycraft.controller;

import com.example.storycraft.model.Profile;
import com.example.storycraft.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/api")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    /**
     * 자신의 프로필 페이지로 이동
     */
    @GetMapping("/myprofile")
    public ModelAndView viewMyProfile(HttpSession session) {
        try {
            // 세션에서 사용자명 추출
            String username = (String) session.getAttribute("user");
            if (username != null) {
                return new ModelAndView("redirect:/api/profile/" + username);
            } else {
                return new ModelAndView("redirect:/api/login");
            }
        } catch (Exception e) {
            return new ModelAndView("redirect:/api/login");
        }
    }

    /**
     * 특정 사용자의 프로필 페이지를 반환
     */
    @GetMapping("/profile/{username}")
    public String getProfile(@PathVariable String username, Model model, HttpSession session) {
        try {
            // 세션에서 사용자명 추출
            String loggedInUser = (String) session.getAttribute("user");

            Profile profile = profileService.getProfileByUsername(username);

            boolean isLoggedIn = loggedInUser != null && loggedInUser.equals(username);
            model.addAttribute("profile", profile);
            model.addAttribute("isLoggedIn", isLoggedIn);
            return "profile"; 
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/error";
        }
    }

    /**
     * 프로필 수정 처리
     */
    @PostMapping("/update-profile")
    @ResponseBody
    public ResponseEntity<Map<String, String>> updateProfile(@RequestBody Map<String, Object> payload, HttpSession session) {
        Map<String, String> response = new HashMap<>();
        try {
            // 세션에서 사용자명 추출
            String loggedInUser = (String) session.getAttribute("user");

            // 로그인된 사용자인지 확인
            if (loggedInUser == null) {
                response.put("success", "false");
                response.put("message", "로그인이 필요합니다.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }

            String username = loggedInUser;
            String email = (String) payload.get("email");
            String nickname = (String) payload.get("nickname");
            String gender = (String) payload.get("gender");
            String intro = (String) payload.get("intro");
            String birthdayStr = (String) payload.get("birthday");

            // 생일 문자열을 Date로 파싱
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date birthday = null;
            if (birthdayStr != null && !birthdayStr.isEmpty()) {
                try {
                    birthday = dateFormat.parse(birthdayStr);
                } catch (ParseException e) {
                    response.put("success", "false");
                    response.put("message", "잘못된 날짜 형식입니다.");
                    return ResponseEntity.badRequest().body(response);
                }
            }

            Profile profile = new Profile();
            profile.setUsername(username);
            profile.setEmail(email);
            profile.setNickname(nickname);
            profile.setGender(gender);
            profile.setBio(intro);
            profile.setBirthday(birthday);

            profileService.updateProfile(profile);
            response.put("success", "true");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) { // ProfileService에서 던진 예외 처리
            response.put("success", "false");
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        } catch (Exception e) { // 기타 예외 처리
            response.put("success", "false");
            response.put("message", "프로필 업데이트 중 오류가 발생했습니다.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    /**
     * 프로필 사진 업로드 처리
     */
    @PostMapping("/upload-profile")
    @ResponseBody
    public ResponseEntity<Map<String, String>> saveProfilePhoto(@RequestParam("profileImage") MultipartFile file,
                                                                HttpSession session) {
        Map<String, String> response = new HashMap<>();
        try {
            // 세션에서 사용자명 추출
            String loggedInUser = (String) session.getAttribute("user");

            // 로그인된 사용자인지 확인
            if (loggedInUser == null) {
                response.put("success", "false");
                response.put("message", "로그인이 필요합니다.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }

            // 파일 업로드 처리 및 웹 접근용 URL 생성
            String filePath = profileService.uploadProfilePhoto(loggedInUser, file);
            response.put("success", "true");
            response.put("profileImageUrl", filePath); // contextPath는 클라이언트에서 추가
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", "false");
            response.put("message", "이미지 업로드에 실패했습니다.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
