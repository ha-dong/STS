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

    // �����ڿ� ����� ��� ������ �ε�
    @GetMapping("/adminUser")
    public String viewUserManagementPage(HttpSession session, Model model) {
        String username = (String) session.getAttribute("user");
        if (username != null) {
            User user = userService.findUserByUsername(username);
            if ("CU-02".equals(user.getuCode())) { // ������ ���� Ȯ��
                List<User> users = adminUserService.getAllUsers();
                model.addAttribute("users", users);
                return "adminUserManagement"; // adminUserManagement.jsp�� �̵�
            }
        }
        return "redirect:/StoryCraft/login"; // ������ ������ �α��� �������� �����̷�Ʈ
    }

    // ����� ��Ȱ��ȭ API
    @PostMapping("/adminUser/api/users/deactivate")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> deactivateUser(@RequestBody Map<String, String> request, HttpSession session) {
        String username = (String) session.getAttribute("user");
        if (username == null) {
            return ResponseEntity.status(401).build(); // �α��ε��� ���� ���
        }
        User adminUser = userService.findUserByUsername(username);
        if (!"CU-02".equals(adminUser.getuCode())) {
            return ResponseEntity.status(403).build(); // ������ ������ ���� ���
        }

        String userId = request.get("userId");
        String reason = request.get("reason");

        Map<String, Object> response = new HashMap<>();

        if (userId == null || reason == null || reason.trim().isEmpty()) {
            response.put("success", false);
            response.put("message", "��ȿ���� ���� ��û�Դϴ�.");
            return ResponseEntity.badRequest().body(response);
        }

        boolean success = adminUserService.deactivateUser(userId, reason);
        if (success) {
            response.put("success", true);
            response.put("message", "����ڰ� ���������� ��Ȱ��ȭ�Ǿ����ϴ�.");
            return ResponseEntity.ok(response);
        } else {
            response.put("success", false);
            response.put("message", "����� ��Ȱ��ȭ�� �����߽��ϴ�.");
            return ResponseEntity.status(500).body(response);
        }
    }

    // ����� Ȱ��ȭ API
    @PostMapping("/adminUser/api/users/activate")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> activateUser(@RequestBody Map<String, String> request, HttpSession session) {
        String username = (String) session.getAttribute("user");
        if (username == null) {
            return ResponseEntity.status(401).build(); // �α��ε��� ���� ���
        }
        User adminUser = userService.findUserByUsername(username);
        if (!"CU-02".equals(adminUser.getuCode())) {
            return ResponseEntity.status(403).build(); // ������ ������ ���� ���
        }

        String userId = request.get("userId");

        Map<String, Object> response = new HashMap<>();

        if (userId == null) {
            response.put("success", false);
            response.put("message", "��ȿ���� ���� ��û�Դϴ�.");
            return ResponseEntity.badRequest().body(response);
        }

        boolean success = adminUserService.activateUser(userId);
        if (success) {
            response.put("success", true);
            response.put("message", "����ڰ� ���������� Ȱ��ȭ�Ǿ����ϴ�.");
            return ResponseEntity.ok(response);
        } else {
            response.put("success", false);
            response.put("message", "����� Ȱ��ȭ�� �����߽��ϴ�.");
            return ResponseEntity.status(500).body(response);
        }
    }

    // �߰����� ������ ���� API�� ���⿡ �ۼ�
}
