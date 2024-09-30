package com.example.storycraft.controller;

import com.example.storycraft.model.Inquiry;
import com.example.storycraft.service.InquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminInquiryController {

    @Autowired
    private InquiryService inquiryService;

    // 관리자 문의 관리 페이지
    @GetMapping("/adminInquiryList")
    public String getAdminInquiryList(Model model) {
        List<Inquiry> inquiryList = inquiryService.getAllInquiries();
        model.addAttribute("inquiryList", inquiryList);
        return "adminInquiryList";  // 관리자 문의 관리 페이지
    }

    // 문의 상태 업데이트
    @PostMapping("/admin/updateInquiryStatus")
    public String updateInquiryStatus(@RequestParam("inqNum") int inqNum, @RequestParam("newStatus") String newStatus) {
        inquiryService.updateInquiryStatus(inqNum, newStatus);
        return "redirect:/adminInquiryList";
    }

    // 문의 삭제
    @DeleteMapping("/admin/deleteInquiry/{inqNum}")
    @ResponseBody
    public Map<String, Object> deleteInquiry(@PathVariable("inqNum") int inqNum) {
        Map<String, Object> response = new HashMap<>();
        try {
            inquiryService.hardDeleteInquiry(inqNum);
            response.put("success", true);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
        }
        return response;
    }

    // 댓글 추가
    @PostMapping("/admin/addComment")
    public String addComment(@RequestParam("inqNum") int inqNum, @RequestParam("commentText") String commentText) {
        inquiryService.addComment(inqNum, commentText);
        return "redirect:/adminInquiryList";
    }
}
