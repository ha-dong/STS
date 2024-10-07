package com.example.storycraft.controller;

import com.example.storycraft.model.Comment;
import com.example.storycraft.model.Inquiry;
import com.example.storycraft.service.InquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class InquiryController {

    @Autowired
    private InquiryService inquiryService;

    // 문의 등록 페이지로 이동
    @GetMapping("/inquiryForm")
    public String inquiryForm() {
        return "inquiryForm";
    }

    // 문의 등록 처리
    @PostMapping("/inquiry")
    public String submitInquiry(
            @RequestParam("inquiryTitle") String inquiryTitle,
            @RequestParam("inquiryType") String inquiryType,
            @RequestParam("inquiryText") String inquiryText,
            @RequestParam(value = "inquiryFile", required = false) MultipartFile inquiryFile,
            HttpSession session,
            Model model) {

        String userId = (String) session.getAttribute("user");

        if (userId == null) {
            model.addAttribute("errorMessage", "로그인이 필요합니다.");
            return "login"; // 로그인 페이지로 이동
        }

        Inquiry inquiry = new Inquiry();
        inquiry.setInqTitle(inquiryTitle);
        inquiry.setInqText(inquiryText);
        inquiry.setInqTypecode(inquiryType);
        inquiry.setInqGenrecode("접수 완료");
        inquiry.setInqCrdate(new Timestamp(System.currentTimeMillis()));
        inquiry.setInqDstatus("Active");
        inquiry.setUserId(userId); // 수정된 부분

        if (inquiryFile != null && !inquiryFile.isEmpty()) {
            String fileName = inquiryService.saveFile(inquiryFile);
            inquiry.setInqFile(fileName);
        }

        inquiryService.saveInquiry(inquiry);

        return "redirect:/inquiry"; // 문의 목록 페이지로 리디렉션
    }

    // 문의 목록 페이지
    @GetMapping("/inquiry")
    public String getInquiryList(Model model, HttpSession session) {
        String userId = (String) session.getAttribute("user");

        List<Inquiry> inquiryList = inquiryService.getAllInquiries();

        model.addAttribute("currentUserId", userId);
        model.addAttribute("inquiryList", inquiryList);

        return "inquiry"; // inquiry.jsp 뷰 렌더링
    }

    
    @GetMapping("/api/inquiry/{inqNum}")
    @ResponseBody
    public Map<String, Object> getInquiryDetailApi(@PathVariable("inqNum") int inqNum) {
        Map<String, Object> response = new HashMap<>();

        Inquiry inquiry = inquiryService.getInquiryDetail(inqNum);
        response.put("success", true);
        response.put("inquiry", inquiry);
        return response;
    }

    
    
    // 문의 목록을 JSON 형식으로 반환하는 API
    @GetMapping("/api/inquiryList")
    @ResponseBody
    public Map<String, Object> getInquiryListApi(HttpSession session) {
        String userId = (String) session.getAttribute("user");

        List<Inquiry> inquiryList = inquiryService.getAllInquiries();

        // 상태 코드 변환
        for (Inquiry inquiry : inquiryList) {
            inquiry.setInqGenrecode(mapStatusCodeToName(inquiry.getInqGenrecode()));
        }

        Map<String, Object> response = new HashMap<>();
        response.put("currentUserId", userId);
        response.put("inquiryList", inquiryList);

        return response;
    }

    // 문의 상세 페이지
    @GetMapping("/inquiryDetail")
    public String inquiryDetail(@RequestParam("inqNum") int inqNum, Model model, HttpSession session) {
        String userId = (String) session.getAttribute("user");

        Inquiry inquiry = inquiryService.getInquiryDetail(inqNum);
        List<Comment> comments = inquiryService.getCommentsForInquiry(inqNum);  // 댓글 목록 가져오기

        if (inquiry == null) {
            return "error/404"; // 문의를 찾을 수 없는 경우 404 페이지로 이동
        }

        String statusName = mapStatusCodeToName(inquiry.getInqGenrecode());
        model.addAttribute("statusName", statusName);
        model.addAttribute("inquiry", inquiry);
        model.addAttribute("comments", comments);  // 댓글 리스트 모델에 추가
        model.addAttribute("currentUserId", userId);

        return "inquiryDetail"; // inquiryDetail.jsp 뷰 렌더링
    }

    // 문의 수정 페이지로 이동
    @GetMapping("/editInquiry")
    public String editInquiryForm(@RequestParam("inqNum") int inqNum, Model model, HttpSession session) {
        String userId = (String) session.getAttribute("user");

        if (userId == null) {
            return "redirect:/login";
        }

        Inquiry inquiry = inquiryService.getInquiryDetail(inqNum);

        if (inquiry == null || !inquiry.getUserId().equals(userId)) { // 수정된 부분
            model.addAttribute("errorMessage", "수정 권한이 없습니다.");
            return "error/403"; // 권한 없음 페이지로 이동
        }

        model.addAttribute("inquiry", inquiry);

        return "editInquiry"; // editInquiry.jsp 뷰 렌더링
    }

    // 문의 수정 처리
    @PostMapping("/editInquiry")
    public String editInquiry(
            @RequestParam("inqNum") int inqNum,
            @RequestParam("inquiryTitle") String inquiryTitle,
            @RequestParam("inquiryType") String inquiryType,
            @RequestParam("inquiryText") String inquiryText,
            @RequestParam(value = "inquiryFile", required = false) MultipartFile inquiryFile,
            HttpSession session,
            Model model) {

        String userId = (String) session.getAttribute("user");
        if (userId == null) {
            return "redirect:/login";
        }

        // 문의 조회
        Inquiry existingInquiry = inquiryService.getInquiryDetail(inqNum);
        if (existingInquiry == null || !existingInquiry.getUserId().equals(userId)) { // 수정된 부분
            model.addAttribute("errorMessage", "수정 권한이 없습니다.");
            return "error/403";
        }

        existingInquiry.setInqTitle(inquiryTitle);
        existingInquiry.setInqText(inquiryText);
        existingInquiry.setInqTypecode(inquiryType);
        existingInquiry.setInqCrdate(new Timestamp(System.currentTimeMillis()));

        if (inquiryFile != null && !inquiryFile.isEmpty()) {
            String fileName = inquiryService.saveFile(inquiryFile);
            existingInquiry.setInqFile(fileName);
        }

        inquiryService.updateInquiry(inqNum, existingInquiry);

        return "redirect:/inquiryDetail?inqNum=" + inqNum; // 수정 후 상세 페이지로 리디렉션
    }

 // 문의 삭제 API (REST 스타일로 경로 수정)
    @DeleteMapping("/api/inquiry/delete/{inqNum}")
    @ResponseBody
    public Map<String, Object> deleteInquiry(@PathVariable("inqNum") int inqNum) {
        Map<String, Object> response = new HashMap<>();

        Inquiry inquiry = inquiryService.getInquiryDetail(inqNum);
        if (inquiry == null) {
            response.put("success", false);
            response.put("message", "해당 문의를 찾을 수 없습니다.");
            return response;
        }

        // 댓글 먼저 삭제
        inquiryService.deleteCommentsByInquiry(inqNum);
        // 문의 삭제
        inquiryService.hardDeleteInquiry(inqNum);

        response.put("success", true);
        response.put("message", "삭제되었습니다.");
        return response;
    }


    // 문의 상태 업데이트 (관리자)
    @PostMapping("/admin/updateInquiryStatus")
    public String updateInquiryStatus(@RequestParam("inqNum") int inqNum, @RequestParam("newStatus") String newStatus) {
        inquiryService.updateInquiryStatus(inqNum, newStatus);
        return "redirect:/adminInquiryList";
    }

 // 관리자 문의 관리 페이지
    @GetMapping("/adminInquiryList")
    public String getAdminInquiryList(
            @RequestParam(value = "inquiryType", required = false) String inquiryType,
            @RequestParam(value = "inquiryStatus", required = false) String inquiryStatus,
            Model model) {

        List<Inquiry> inquiryList;
        if ((inquiryType == null || inquiryType.isEmpty()) && (inquiryStatus == null || inquiryStatus.isEmpty())) {
            inquiryList = inquiryService.getAllInquiries();
        } else if (inquiryType != null && !inquiryType.isEmpty() && (inquiryStatus == null || inquiryStatus.isEmpty())) {
            inquiryList = inquiryService.getInquiriesByType(inquiryType);
        } else if ((inquiryType == null || inquiryType.isEmpty()) && inquiryStatus != null && !inquiryStatus.isEmpty()) {
            inquiryList = inquiryService.getInquiriesByStatus(inquiryStatus);
        } else {
            inquiryList = inquiryService.getInquiriesByTypeAndStatus(inquiryType, inquiryStatus);
        }

        model.addAttribute("inquiryList", inquiryList);
        return "adminInquiryList";
    }

    // 댓글 추가 (관리자)
    @PostMapping("/admin/addComment")
    public String addComment(@RequestParam("inqNum") int inqNum, @RequestParam("commentText") String commentText) {
        inquiryService.addComment(inqNum, commentText);
        return "redirect:/adminInquiryList";
    }
    
    @DeleteMapping("/api/comments/deleteByInquiry/{inqNum}")
    @ResponseBody
    public Map<String, Object> deleteCommentsByInquiry(@PathVariable("inqNum") int inqNum) {
        Map<String, Object> response = new HashMap<>();
        try {
            // 댓글 삭제 로직 실행
            inquiryService.deleteCommentsByInquiry(inqNum);
            response.put("success", true);
            response.put("message", "댓글이 성공적으로 삭제되었습니다.");
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "댓글 삭제 중 오류 발생: " + e.getMessage());
        }
        return response;
    }


    // 상태 코드를 상태 이름으로 매핑
    private String mapStatusCodeToName(String statusCode) {
        switch (statusCode) {
            case "CI-05":
                return "접수 완료";
            case "CI-06":
                return "처리중";
            case "CI-07":
                return "처리 완료";
            default:
                return "접수 완료";
        }
    }
}
