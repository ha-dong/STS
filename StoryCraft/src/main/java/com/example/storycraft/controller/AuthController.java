package com.example.storycraft.controller;

import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.storycraft.service.KakaoService;
import com.example.storycraft.service.UserService;
import com.example.storycraft.model.User;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private KakaoService kakaoService;

    //로그인 처리
    // 일반 로그인 처리
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials, HttpSession session) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            return ResponseEntity.status(400).body("사용자 이름과 비밀번호는 필수 입력 항목입니다.");
        }

        User user = userService.findUserByUsername(username);
        
        // 사용자 존재 여부와 계정 활성화 여부 확인
        if (user != null && "N".equals(user.getuActivate())) {
            session.setAttribute("user", username); // 계정 복구에 사용할 사용자 정보 세션 저장
            return ResponseEntity.ok(new SimpleResponse(false, "계정이 비활성화되었습니다. 복구하시겠습니까?"));
        }

        // 사용자 인증
        if (userService.authenticate(username, password)) {
            session.setAttribute("user", username);
            session.setAttribute("uCode", user.getuCode()); // uCode 세션에 저장

            // U_CODE가 CU-00이면 관리자 페이지로 이동
            if ("CU-00".equals(user.getuCode())) {
                return ResponseEntity.ok(new LoginResponse(true, username, user.getuNickname(), user.getuProfile(), "/StoryCraft/manager"));
            } else {
                // 일반 사용자 페이지로 이동
                return ResponseEntity.ok(new LoginResponse(true, username, user.getuNickname(), user.getuProfile(), "/StoryCraft/main"));
            }
        } else {
            return ResponseEntity.status(401).body(new LoginResponse(false, null, null, null, null));
        }
    }

    // 카카오 로그인 처리
    @PostMapping("/kakao-login")
    public ResponseEntity<?> kakaoLogin(@RequestBody Map<String, String> kakaoData, HttpSession session) {
        String accessToken = kakaoData.get("accessToken");

        if (accessToken == null || accessToken.isEmpty()) {
            return ResponseEntity.status(400).body("카카오 액세스 토큰이 필요합니다.");
        }

        Map<String, Object> userInfo = kakaoService.getUserInfo(accessToken);
        String kakaoId = userInfo.get("id").toString();

        User user = userService.findUserByUsername(kakaoId);
        if (user == null) {
            userService.saveKakaoUser(userInfo);
        }

        session.setAttribute("user", kakaoId);
        session.setAttribute("nickname", userInfo.get("nickname"));

        return ResponseEntity.ok(new LoginResponse(true, kakaoId, (String) userInfo.get("nickname"), user.getuProfile(), "/StoryCraft/main"));
    }

    @GetMapping("/check-login")
    public ResponseEntity<?> checkLoginStatus(HttpSession session) {
        String username = (String) session.getAttribute("user");
        if (username != null) {
            User user = userService.findUserByUsername(username);
            String nickname = userService.getNicknameByUsername(username);
            String profileImage = user.getuProfile(); // 프로필 이미지 추가
            return ResponseEntity.ok(new LoginResponse(true, username, nickname, profileImage, "/StoryCraft/main"));
        } else {
            return ResponseEntity.status(401).body(new LoginResponse(false, null, null, null, null));
        }
    }

    public static class LoginResponse {
        public boolean loggedIn;
        public String username;
        public String nickname;
        public String profileImage;
        public String redirectUrl;

        public LoginResponse(boolean loggedIn, String username, String nickname, String profileImage, String redirectUrl) {
            this.loggedIn = loggedIn;
            this.username = username;
            this.nickname = nickname;
            this.profileImage = profileImage;
            this.redirectUrl = redirectUrl;
        }
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
