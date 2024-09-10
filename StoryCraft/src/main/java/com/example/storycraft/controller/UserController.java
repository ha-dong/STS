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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.storycraft.model.User;
import com.example.storycraft.service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
   
    
    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/accession")
    public String accession() {
        return "accession";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/main")
    public String main() {
        return "main";
    }

    @GetMapping("/notice")
    public String notice() {
        return "notice";
    }

    @GetMapping("/inquiry")
    public String inquiry() {
        return "inquiry";
    }

    @GetMapping("/inquiryList")
    public String inquiryList() {
        return "inquiryList";
    }

    @GetMapping("/find")
    public String find() {
        return "find";
    }

    @GetMapping("/resetpw")
    public String resetpw() {
        return "resetpw";
    }

    @GetMapping("/mainStory")
    public String mainStory() {
        return "mainStory";
    }

    // /jsp/editInquiry 경로 제거됨
    // 해당 경로는 PageController에서 담당하게 됨

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

//    // 로그인 처리 추가 (POST 방식)
//    @PostMapping("/login")
//    public String loginUser(@RequestParam("userid") String userid,
//                            @RequestParam("password") String password,
//                            HttpSession session) {
//        // 입력된 아이디와 비밀번호로 사용자 조회
//        User user = userService.findByUserIdAndPassword(userid, password);
//
//        if (user != null) {
//            // 유저 정보가 존재하면 세션에 사용자 정보 저장
//            session.setAttribute("user", user);
//            return "redirect:/mainStory";  // 스토리 페이지로 리다이렉트
//        } else {
//            // 로그인 실패 시 로그인 페이지로 리다이렉트
//            return "redirect:/login?error=true";
//        }
//    }

    @PostMapping("/api/validate-id")
    @ResponseBody
    public Map<String, Boolean> validateUserId(@RequestBody Map<String, String> request) {
        String userId = request.get("userid");
        boolean isAvailable = userService.isUserIdAvailable(userId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("valid", isAvailable);
        return response;
    }

    @PostMapping("/api/find-id")
    @ResponseBody
    public ResponseEntity<Map<String, String>> findId(@RequestBody Map<String, String> params) {
        String username = params.get("username");
        String email = params.get("email");

        System.out.println("Received username: " + username);

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

    private String generateAuthToken() {
        Random random = new Random();
        int authToken = 100000 + random.nextInt(900000);
        return String.valueOf(authToken);
    }

    private void sendAuthEmail(String email, String authToken) throws Exception {
        String host = "smtp.gmail.com";
        String user = "suleehk@gmail.com";
        String pass = "dujy aizq nbhw xuut"; 

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
