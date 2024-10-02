package com.example.storycraft.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.storycraft.service.KakaoService;
import com.example.storycraft.service.ProfileService;
import com.example.storycraft.service.UserService;
import com.example.storycraft.model.Profile;
import com.example.storycraft.model.User;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private KakaoService kakaoService;
    
    @Autowired
    private ProfileService profileService; // 추가
    
    // 일반 로그인 처리
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> credentials, HttpSession session) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            return ResponseEntity.status(400).body(Map.of(
                "success", false,
                "message", "사용자 이름과 비밀번호는 필수 입력 항목입니다."
            ));
        }

        User user = userService.findUserByUsername(username);
        if (user != null && "N".equals(user.getuActivate())) {
            // 계정이 비활성화된 경우
            session.setAttribute("user", username); // 계정 복구에 사용할 사용자 정보 세션 저장
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "비활성화");
            response.put("uDreason", user.getuDreason());
            response.put("deactivatedBy", user.getDeactivatedBy());
            return ResponseEntity.ok(response);
        }

        if (userService.authenticate(username, password)) {
            session.setAttribute("user", username);
            String nickname = userService.getNicknameByUsername(username);
            String uCode = user.getuCode(); // uCode 가져오기
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("loggedIn", true);
            response.put("token", "your-token-here"); // 실제 토큰 생성 로직 필요
            response.put("uCode", uCode);
            return ResponseEntity.ok(response);
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "로그인 실패");
            return ResponseEntity.status(401).body(response);
        }
    }

    // 카카오 로그인 처리
    @PostMapping("/kakao-login")
    public ResponseEntity<?> kakaoLogin(@RequestBody Map<String, String> kakaoData, HttpSession session) {
        String accessToken = kakaoData.get("accessToken");

        if (accessToken == null || accessToken.isEmpty()) {
            return ResponseEntity.status(400).body("카카오 액세스 토큰이 필요합니다.");
        }

        // 카카오 API를 통해 사용자 정보 확인 및 저장
        Map<String, Object> userInfo = kakaoService.getUserInfo(accessToken);
        String kakaoId = userInfo.get("id").toString();

        User user = userService.findUserByUsername(kakaoId);
        if (user == null) {
            // 신규 카카오 유저일 경우 사용자 정보 저장
            userService.saveKakaoUser(userInfo);
            user = userService.findUserByUsername(kakaoId); // 새로 생성된 사용자 조회
        }

        // 세션에 사용자 정보 저장
        session.setAttribute("user", kakaoId);
        session.setAttribute("nickname", userInfo.get("nickname"));

        // 세션 로그 확인
        System.out.println("Session User: " + session.getAttribute("user"));
        System.out.println("Session Nickname: " + session.getAttribute("nickname"));

        return ResponseEntity.ok(new LoginResponse(true, kakaoId, (String) userInfo.get("nickname")));
    }

    // 기존 세션 기반 check-login 엔드포인트 유지
    @GetMapping("/check-login")
    public ResponseEntity<?> checkLoginStatus(HttpSession session) {
        String username = (String) session.getAttribute("user");
        if (username != null) {
            Profile profile = profileService.getProfileByUsername(username);
            if (profile != null) {
                Map<String, Object> response = new HashMap<>();
                response.put("loggedIn", true);
                response.put("username", profile.getUsername());
                response.put("email", profile.getEmail());
                response.put("nickname", profile.getNickname());
                response.put("gender", profile.getGender());
                response.put("birthday", profile.getBirthday());
                response.put("bio", profile.getBio());
                response.put("profileImage", profile.getProfilePicture());
                response.put("uCode", userService.findUserByUsername(username).getuCode()); // uCode 추가
                return ResponseEntity.ok(response);
            } else {
                Map<String, Object> response = new HashMap<>();
                response.put("loggedIn", false);
                response.put("message", "프로필을 찾을 수 없습니다.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("loggedIn", false);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }   

    public static class LoginResponse {
        public boolean loggedIn;
        public String username;
        public String nickname;
        public String uCode; // 추가된 필드

        // 기존 생성자
        public LoginResponse(boolean loggedIn, String username, String nickname) {
            this.loggedIn = loggedIn;
            this.username = username;
            this.nickname = nickname;
        }

        // 새로운 생성자 추가
        public LoginResponse(boolean loggedIn, String username, String nickname, String uCode) {
            this.loggedIn = loggedIn;
            this.username = username;
            this.nickname = nickname;
            this.uCode = uCode;
        }
    }
    
    
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok(new SimpleResponse(true, "로그아웃 되었습니다."));
    }

    @GetMapping("/check-main-story-completed")
    public ResponseEntity<?> checkMainStoryCompleted(HttpSession session) {
        String username = (String) session.getAttribute("user");
        if (username != null) {
            User user = userService.findUserByUsername(username);
            boolean mainStoryCompleted = userService.isMainStoryCompleted(user.getuId());
            return ResponseEntity.ok(new MainStoryResponse(mainStoryCompleted));
        } else {
            return ResponseEntity.status(401).build();
        }
    }

    @PostMapping("/unlock-user-story")
    public ResponseEntity<?> unlockUserStory(HttpSession session) {
        String username = (String) session.getAttribute("user");
        if (username != null) {
            User user = userService.findUserByUsername(username);
            userService.unlockUserStory(user.getuId());
            return ResponseEntity.ok(new SimpleResponse(true));
        } else {
            return ResponseEntity.status(401).build();
        }
    }

    @GetMapping("/check-user-story-unlocked")
    public ResponseEntity<?> checkUserStoryUnlocked(HttpSession session) {
        String username = (String) session.getAttribute("user");
        if (username != null) {
            User user = userService.findUserByUsername(username);
            boolean userStoryUnlocked = userService.isUserStoryUnlocked(user.getuId());
            return ResponseEntity.ok(new UserStoryResponse(userStoryUnlocked));
        } else {
            return ResponseEntity.status(401).build();
        }
    }

    @PostMapping("/delete-account")
    public ResponseEntity<?> deleteAccount(HttpSession session) {
        String username = (String) session.getAttribute("user");
        if (username != null) {
            User user = userService.findUserByUsername(username);
            userService.deleteAccount(user.getuId());  // 계정 삭제 (비활성화)
            session.invalidate();
            return ResponseEntity.ok(new SimpleResponse(true));
        } else {
            return ResponseEntity.status(401).build();
        }
    }

    @PostMapping("/reactivate-account")
    public ResponseEntity<?> reactivateAccount(HttpSession session) {
        String username = (String) session.getAttribute("user");
        if (username != null) {
            User user = userService.findUserByUsername(username);
            userService.reactivateAccount(user.getuId());  // 계정 복구
            session.setAttribute("user", username);  // 계정 복구 후 세션 유지
            return ResponseEntity.ok(new SimpleResponse(true, "계정이 복구되었습니다."));
        } else {
            return ResponseEntity.status(401).body(new SimpleResponse(false, "계정 복구에 실패했습니다."));
        }
    }

    // 추가된 부분: 메인 스토리 스킵 및 완료
    @PostMapping("/skip-main-story")
    public ResponseEntity<?> skipMainStory(HttpSession session) {
        String username = (String) session.getAttribute("user");
        if (username != null) {
            User user = userService.findUserByUsername(username);
            userService.completeMainStory(user.getuId());
            return ResponseEntity.ok(new SimpleResponse(true)); // 스킵 성공
        } else {
            return ResponseEntity.status(401).build(); // 로그인 안된 경우
        }
    }

    @PostMapping("/complete-main-story")
    public ResponseEntity<?> completeMainStory(HttpSession session) {
        String username = (String) session.getAttribute("user");
        if (username != null) {
            User user = userService.findUserByUsername(username);
            userService.completeMainStory(user.getuId());
            return ResponseEntity.ok(new SimpleResponse(true)); // 완료 성공
        } else {
            return ResponseEntity.status(401).build(); // 로그인 안된 경우
        }
    }

    public static class MainStoryResponse {
        public boolean mainStoryCompleted;

        public MainStoryResponse(boolean mainStoryCompleted) {
            this.mainStoryCompleted = mainStoryCompleted;
        }
    }

    public static class UserStoryResponse {
        public boolean userStoryUnlocked;

        public UserStoryResponse(boolean userStoryUnlocked) {
            this.userStoryUnlocked = userStoryUnlocked;
        }
    }

    public static class SimpleResponse {
        public boolean success;
        public String message;

        public SimpleResponse(boolean success) {
            this.success = success;
        }

        public SimpleResponse(boolean success, String message) {
            this.success = success;
            this.message = message;
        }
    }
}