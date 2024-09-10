package com.example.storycraft.controller;

import com.example.storycraft.model.Notice;
import com.example.storycraft.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/notices")
public class NoticeController {

    private final NoticeService noticeService;

    @Autowired
    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    // 공지사항 목록 페이지
    @GetMapping
    public String listNotices(Model model) {
        model.addAttribute("notices", noticeService.getAllNotices());
        return "notice";  // 공지사항 목록 JSP로 이동
    }

    // 공지사항 작성 폼 페이지
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("notice", new Notice());
        return "noticeWrite";  // 공지사항 작성 JSP로 이동
    }

    // 공지사항 저장
    @PostMapping("/save")
    public String saveNotice(@ModelAttribute Notice notice) {
        noticeService.saveNotice(notice);  // 공지사항 저장
        return "redirect:/notices";  // 저장 후 목록 페이지로 리다이렉트
    }

    // 공지사항 세부 내용 페이지 (제목 클릭 시 이동)
    @GetMapping("/{id}")
    public String viewNotice(@PathVariable Long id, Model model) {
        Notice notice = noticeService.getNoticeById(id);
        model.addAttribute("notice", notice);
        return "noticeDetail";  // 공지사항 세부 내용 JSP로 이동
    }

    // 공지사항 수정 처리
    @PostMapping("/update")
    public String updateNotice(@ModelAttribute Notice notice) {
        noticeService.updateNotice(notice);  // 공지사항 수정
        return "redirect:/notices";  // 수정 후 목록 페이지로 리다이렉트
    }
}
