package com.example.storycraft.controller;

import java.io.IOException;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.storycraft.model.User;
import com.example.storycraft.service.KakaoService;
import com.example.storycraft.service.UserService;

@Controller
public class KakaoController {

    @Autowired
    private KakaoService kakaoService;
    
    @Autowired
    private UserService userService;  // UserService 주입

    // 카카오 로그인 콜백 처리
    @GetMapping("/callback")
    public void kakaoLogin(@RequestParam String code, HttpSession session, HttpServletResponse response)
            throws IOException {
        
        System.out.println("Kakao callback has been triggered with code: " + code);

        // 액세스 토큰 요청
        String accessToken = kakaoService.getAccessToken(code);
        System.out.println("Access Token: " + accessToken);

        if (accessToken == null || accessToken.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Failed to retrieve access token");
            return;
        }

        // 사용자 정보 요청
        Map<String, Object> userInfo = kakaoService.getUserInfo(accessToken);
        System.out.println("User Info: " + userInfo);

        // 사용자 정보 확인 및 세션 설정
        String kakaoId = userInfo.get("id").toString();
        User existingUser = userService.findUserByUsername(kakaoId);

        if (existingUser == null) {
            // 신규 회원 가입
            userService.saveKakaoUser(userInfo);
        }

        // 세션에 사용자 정보 저장
        session.setAttribute("user", kakaoId);
        session.setAttribute("nickname", userInfo.get("nickname"));

        // 토큰을 클라이언트 측으로 전달 (로그인 후 토큰을 URL로 전달하여 저장)
        response.sendRedirect("/StoryCraft/main?token=" + accessToken);  
    }
}