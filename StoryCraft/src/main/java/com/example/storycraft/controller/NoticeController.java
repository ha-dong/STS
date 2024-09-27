package com.example.storycraft.controller;

import com.example.storycraft.model.Notice;
import com.example.storycraft.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/notice")  // 매핑을 "/notice"로 설정
public class NoticeController {

    private final NoticeService noticeService;

    @Autowired
    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    // GET 요청 처리: 목록 보기, 작성 폼 보기, 상세 보기
    @GetMapping
    public String handleGetRequest(
            @RequestParam(value = "action", required = false) String action,
            @RequestParam(value = "id", required = false) Long id,
            Model model) {
        if ("new".equals(action)) {
            return showCreateForm(model);
        } else if ("view".equals(action) && id != null) {
            return viewNotice(id, model);
        } else {
            return listNotices(model);
        }
    }

    // POST 요청 처리: 저장, 수정, 삭제
    @PostMapping
    public String handlePostRequest(
            @RequestParam(value = "action", required = false) String action,
            @ModelAttribute Notice notice,
            @RequestParam(value = "id", required = false) Long id,
            HttpSession session) {
        if ("save".equals(action)) {
            return saveNotice(notice, session);
        } else if ("update".equals(action)) {
            return updateNotice(notice, session);
        } else if ("delete".equals(action) && id != null) {
            return deleteNotice(id, session);
        }
        return "redirect:/notice";
    }

    // 공지사항 목록 보기
    private String listNotices(Model model) {
        model.addAttribute("notices", noticeService.getAllNotices());
        return "notice";  // 통합된 JSP 파일 이름
    }

    // 공지사항 작성 폼 보기
    private String showCreateForm(Model model) {
        model.addAttribute("notice", new Notice());
        model.addAttribute("action", "new");
        return "notice";  // 통합된 JSP 파일 이름
    }

    // 공지사항 저장 처리
    private String saveNotice(Notice notice, HttpSession session) {
        String userId = (String) session.getAttribute("user");  // 세션에서 사용자 ID 가져오기
        if (userId == null || userId.isEmpty()) {
            // 로그인되지 않은 경우 처리 (예: 로그인 페이지로 리다이렉트)
            return "redirect:/login";
        }
        notice.setUid(userId);  // 수정된 부분: setUId → setUid
        noticeService.saveNotice(notice);  // 공지사항 저장
        return "redirect:/notice";  // 저장 후 목록 페이지로 리다이렉트
    }

    // 공지사항 상세 보기
    private String viewNotice(Long id, Model model) {
        Notice notice = noticeService.getNoticeById(id);
        model.addAttribute("notice", notice);
        model.addAttribute("action", "view");
        return "notice";  // 통합된 JSP 파일 이름
    }

    // 공지사항 수정 처리
    private String updateNotice(Notice notice, HttpSession session) {
        String userId = (String) session.getAttribute("user");  // 세션에서 사용자 ID 가져오기
        if (userId == null || userId.isEmpty()) {
            // 로그인되지 않은 경우 처리 (예: 로그인 페이지로 리다이렉트)
            return "redirect:/login";
        }
        notice.setUid(userId);  // 수정된 부분: setUId → setUid
        noticeService.updateNotice(notice);  // 공지사항 수정
        return "redirect:/notice";  // 수정 후 목록 페이지로 리다이렉트
    }

    // 공지사항 삭제 처리
    private String deleteNotice(Long id, HttpSession session) {
        String userId = (String) session.getAttribute("user");  // 세션에서 사용자 ID 가져오기
        if (userId == null || userId.isEmpty()) {
            // 로그인되지 않은 경우 처리 (예: 로그인 페이지로 리다이렉트)
            return "redirect:/login";
        }
        noticeService.deleteNoticeById(id);  // 공지사항 삭제
        return "redirect:/notice";  // 삭제 후 목록 페이지로 리다이렉트
    }
    
    @GetMapping("/api/list")
    @ResponseBody
    public List<Notice> getNotices() {
        return noticeService.getAllNotices();
    }
}
