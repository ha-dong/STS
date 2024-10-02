package com.example.storycraft.controller;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.example.storycraft.model.User;
import com.example.storycraft.service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    // 메인 페이지 매핑
    @GetMapping("/index")
    public String index() {
        return "index";
    }

    // 가입 페이지 매핑
    @GetMapping("/accession")
    public String accession() {
        return "accession";
    }

    // 로그인 페이지 매핑
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // 회원가입 페이지 매핑
    @GetMapping("/register")
    public String register() {
        return "register";
    }

    // 메인 페이지 매핑
    @GetMapping("/main")
    public String main() {
        return "main";
    }

    // 문의 목록 페이지 매핑
    @GetMapping("/inquiryList")
    public String inquiryList() {
        return "inquiryList";
    }

    // 아이디 찾기 페이지 매핑
    @GetMapping("/find")
    public String find() {
        return "find";
    }

    // 비밀번호 재설정 페이지 매핑
    @GetMapping("/resetpw")
    public String resetpw() {
        return "resetpw";
    }

    // 프로필 페이지 매핑
    @GetMapping("/profile")
    public String profile() {
        return "profile";  // resources/templates/profile.jsp로 연결
    }

    // 내가 작성한 스토리 목록 페이지 매핑
    @GetMapping("/mystorylist")
    public String myStoryList() {
        return "mystorylist";  // resources/templates/mystorylist.jsp로 연결
    }

    // 메인 스토리 페이지 매핑
    @GetMapping("/mainStory")
    public String mainStory() {
        return "mainStory"; // resources/templates/mainStory.jsp로 연결
    }

    // 좋아요한 스토리 목록 페이지 매핑
    @GetMapping("/favoritestorylist")
    public String favoriteStoryList() {
        return "favoritestorylist";  // resources/templates/favoritestorylist.jsp로 연결
    }
    
    /**
     * 관리자 페이지로 이동
     */
    @GetMapping("/manager")
    public String viewManagerPage(HttpSession session, Model model) {
        String username = (String) session.getAttribute("user");
        if (username != null) {
            User user = userService.findUserByUsername(username);
            if ("CU-02".equals(user.getuCode())) { // getuCode() 메서드 사용
                return "manager"; // manager.jsp로 이동
            }
        }
        return "redirect:/StoryCraft/login"; // 권한이 없으면 로그인 페이지로 리다이렉트
    }

    // 회원가입 처리
    @PostMapping("/register")
    public ModelAndView registerUser(@RequestParam("userid") String userid,
                                     @RequestParam("password") String password,
                                     @RequestParam("confirmPassword") String confirmPassword,
                                     @RequestParam("username") String username,
                                     @RequestParam("nickname") String nickname,
                                     @RequestParam("birthday") String birthday,
                                     @RequestParam("gender") String gender,
                                     @RequestParam("phonenumber") String phonenumber,
                                     @RequestParam("emailLocal") String emailLocal,
                                     @RequestParam("emailDomain") String emailDomain,
                                     @RequestParam("address") String address) {

        if (!password.equals(confirmPassword)) {
            return new ModelAndView("register").addObject("error", "Passwords do not match");
        }

        User user = new User();
        user.setuId(userid);
        user.setuPw(password);
        user.setuName(username);
        user.setuPhone(phonenumber);
        user.setuAdd(address);
        user.setuEmail(emailLocal + "@" + emailDomain);
        user.setuCertified("CA-01");
        user.setuCdate(new Timestamp(System.currentTimeMillis()));
        user.setuActivate("Y");
        user.setMainComplete("N");
        user.setuCode("CU-01");
        user.setuDstatus("N");
        user.setuNickname(nickname);
        user.setuBirthday(Timestamp.valueOf(birthday + " 00:00:00"));
        user.setuGender(gender);

        userService.addUser(user);

        return new ModelAndView("redirect:/login");
    }

    // 사용자 ID 검증 API
    @PostMapping("/api/validate-id")
    @ResponseBody
    public Map<String, Boolean> validateUserId(@RequestBody Map<String, String> request) {
        String userId = request.get("userid");
        boolean isAvailable = userService.isUserIdAvailable(userId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("valid", isAvailable);
        return response;
    }

    // 아이디 찾기 API
    @PostMapping("/api/find-id")
    @ResponseBody
    public ResponseEntity<Map<String, String>> findId(@RequestBody Map<String, String> params) {
        String username = params.get("username");
        String email = params.get("email");

        Map<String, String> response = new HashMap<>();

        if (username == null || email == null) {
            response.put("message", "필수 파라미터가 누락되었습니다.");
            return ResponseEntity.badRequest().body(response);
        }

        String userId = userService.findIdByUsernameAndEmail(username, email);
        if (userId != null) {
            response.put("userId", userId);
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "아이디를 찾을 수 없습니다.");
            return ResponseEntity.ok(response);
        }
    }

    // 비밀번호 재설정 API
    @PostMapping("/api/reset-password")
    @ResponseBody
    public ResponseEntity<Map<String, String>> resetPassword(@RequestBody Map<String, String> request) {
        String token = request.get("token");
        String newPassword = request.get("newPassword");
        String email = request.get("email");

        Map<String, String> response = new HashMap<>();
        if (token == null || newPassword == null || email == null) {
            response.put("message", "필수 파라미터가 누락되었습니다.");
            return ResponseEntity.status(400).body(response);
        }

        userService.resetPassword(token, newPassword, email);
        response.put("success", "true");
        return ResponseEntity.ok(response);
    }

    // 이메일 인증 코드 전송 API
    @PostMapping("/api/send-auth-email")
    @ResponseBody
    public ResponseEntity<Map<String, String>> sendAuthEmail(@RequestBody Map<String, String> request) {
        String userId = request.get("userid");
        String email = request.get("email");

        Map<String, String> response = new HashMap<>();

        String authToken = generateAuthToken();
        Timestamp expiryTime = new Timestamp(System.currentTimeMillis() + 5 * 60 * 1000);

        userService.updateUserAuthToken(userId, authToken, expiryTime);

        try {
            sendAuthEmail(email, authToken);
            response.put("message", "인증번호가 이메일로 전송되었습니다.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "이메일 전송 중 오류가 발생했습니다.");
            return ResponseEntity.status(500).body(response);
        }
    }

    // 인증 코드 생성
    private String generateAuthToken() {
        Random random = new Random();
        int authToken = 100000 + random.nextInt(900000);
        return String.valueOf(authToken);
    }

    // 이메일 발송
    private void sendAuthEmail(String email, String authToken) throws Exception {
        String host = "smtp.gmail.com";
        String user = "suleehk@gmail.com";
        String pass = "udpx bwhk qpft amns";  // 실제 비밀번호는 적절한 방식으로 관리되어야 함

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("비밀번호 재설정을 위한 인증 코드");
            message.setText("인증 코드: " + authToken + "\n이 코드는 5분 후에 만료됩니다.");

            Transport.send(message);
            System.out.println("이메일이 성공적으로 전송되었습니다!");

        } catch (MessagingException e) {
            throw new RuntimeException("이메일 전송 중 오류가 발생했습니다.", e);
        }
    }
}
