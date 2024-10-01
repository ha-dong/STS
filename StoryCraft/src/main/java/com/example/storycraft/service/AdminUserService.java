package com.example.storycraft.service;

import com.example.storycraft.dao.AdminUserDao;
import com.example.storycraft.model.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminUserService {

    @Autowired
    private AdminUserDao adminUserDao;
    
    @Autowired
    private UserService userService;
    

    // 모든 사용자 목록 조회
    public List<User> getAllUsers() {
        return adminUserDao.getAllUsers();
    }

    // 사용자 비활성화 (관리자용)
    public boolean deactivateUser(String userId, String reason) {
        try {
            userService.deactivateAccountByAdmin(userId, reason);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 사용자 활성화 (관리자용)
    public boolean activateUser(String userId) {
        return adminUserDao.activateUser(userId);
    }

}
