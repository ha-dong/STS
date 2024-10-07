package com.example.storycraft.controller;

import com.example.storycraft.model.User;
import com.example.storycraft.service.AdminUserService;
import com.example.storycraft.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminUserController {

    @Autowired
    private AdminUserService adminUserService;

    @Autowired
    private UserService userService;

    // 관리자용 사용자 목록 페이지 매핑
    @GetMapping("/adminUser")
    public String viewUserManagementPage(HttpSession session, Model model) {
        String username = (String) session.getAttribute("user");
        if (username != null) {
            User user = userService.findUserByUsername(username);
            if ("CU-02".equals(user.getuCode())) { // 관리자 권한 확인
                List<User> users = adminUserService.getAllUsers();
                model.addAttribute("users", users);
                return "adminUserManagement"; // adminUserManagement.jsp로 이동
            }
        }
        return "redirect:/StoryCraft/login"; // 권한이 없으면 로그인 페이지로 리다이렉트
    }

 // 사용자 비활성화 API
    @PostMapping("/adminUser/api/users/deactivate")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> deactivateUser(@RequestBody Map<String, String> request, HttpSession session) {
        String username = (String) session.getAttribute("user");
        if (username == null) {
            return ResponseEntity.status(401).build(); // 로그인되지 않은 경우
        }
        User adminUser = userService.findUserByUsername(username);
        if (!"CU-02".equals(adminUser.getuCode())) {
            return ResponseEntity.status(403).build(); // 관리자 권한이 없는 경우
        }

        String userId = request.get("userId");
        String reason = request.get("reason");

        Map<String, Object> response = new HashMap<>();

        if (userId == null || reason == null || reason.trim().isEmpty()) {
            response.put("success", false);
            response.put("message", "유효하지 않은 요청입니다.");
            return ResponseEntity.badRequest().body(response);
        }

        boolean success = adminUserService.deactivateUser(userId, reason);
        if (success) {
            response.put("success", true);
            response.put("message", "사용자가 성공적으로 비활성화되었습니다.");
            return ResponseEntity.ok(response);
        } else {
            response.put("success", false);
            response.put("message", "사용자 비활성화에 실패했습니다.");
            return ResponseEntity.status(500).body(response);
        }
    }


    // 사용자 활성화 API
    @PostMapping("/adminUser/api/users/activate")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> activateUser(@RequestBody Map<String, String> request, HttpSession session) {
        String username = (String) session.getAttribute("user");
        if (username == null) {
            return ResponseEntity.status(401).build(); // 로그인되지 않은 경우
        }
        User adminUser = userService.findUserByUsername(username);
        if (!"CU-02".equals(adminUser.getuCode())) {
            return ResponseEntity.status(403).build(); // 관리자 권한이 없는 경우
        }

        String userId = request.get("userId");

        Map<String, Object> response = new HashMap<>();

        if (userId == null) {
            response.put("success", false);
            response.put("message", "유효하지 않은 요청입니다.");
            return ResponseEntity.badRequest().body(response);
        }

        boolean success = adminUserService.activateUser(userId);
        if (success) {
            response.put("success", true);
            response.put("message", "사용자가 성공적으로 활성화되었습니다.");
            return ResponseEntity.ok(response);
        } else {
            response.put("success", false);
            response.put("message", "사용자 활성화에 실패했습니다.");
            return ResponseEntity.status(500).body(response);
        }
    }

    // 추가적인 관리자 전용 API를 여기에 작성
}
