package com.example.storycraft.controller;

import com.example.storycraft.model.Inquiry;
import com.example.storycraft.model.SimpleResponse;
import com.example.storycraft.service.InquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/api")
public class InquiryController {

    @Autowired
    private InquiryService inquiryService;

    // 문의 등록 API
    @PostMapping("/inquiry")
    public ResponseEntity<?> submitInquiry(
        @RequestParam("inquiryTitle") String inquiryTitle,
        @RequestParam("inquiryType") String inquiryType,
        @RequestParam("inquiryText") String inquiryText,
        @RequestParam(value = "inquiryFile", required = false) MultipartFile inquiryFile,
        HttpSession session) {

        String userId = (String) session.getAttribute("user");

        if (userId == null) {
            return ResponseEntity.status(401).body("로그인이 필요합니다.");
        }

        Inquiry inquiry = new Inquiry();
        inquiry.setInqTitle(inquiryTitle);
        inquiry.setInqText(inquiryText);
        inquiry.setInqTypecode(inquiryType);
        inquiry.setInqGenrecode("접수 완료");  // 기본 설정
        inquiry.setInqCrdate(new Timestamp(System.currentTimeMillis()));
        inquiry.setInqDstatus("Active");
        inquiry.setUId(userId);

        if (inquiryFile != null && !inquiryFile.isEmpty()) {
            String fileName = inquiryService.saveFile(inquiryFile);
            inquiry.setInqFile(fileName);
        }

        inquiryService.saveInquiry(inquiry);
        return ResponseEntity.ok(new SimpleResponse(true, "문의가 성공적으로 등록되었습니다."));
    }

    // 문의 목록 조회 API
    @GetMapping("/inquiryList")
    public ResponseEntity<?> getInquiryList(HttpSession session) {
        String userId = (String) session.getAttribute("user");

        if (userId == null) {
            return ResponseEntity.status(401).body("로그인이 필요합니다.");
        }

        List<Inquiry> inquiryList = inquiryService.getInquiryListByUserId(userId);
        return ResponseEntity.ok(inquiryList);
    }

    // 문의 상세 정보 조회 API
    @GetMapping("/inquiry/{inqNum}")
    public ResponseEntity<?> getInquiryDetail(@PathVariable("inqNum") int inqNum, HttpSession session) {
        String userId = (String) session.getAttribute("user");
        if (userId == null) {
            return ResponseEntity.status(401).body("로그인이 필요합니다.");
        }

        Inquiry inquiry = inquiryService.getInquiryDetail(inqNum, userId);
        if (inquiry == null) {
            return ResponseEntity.status(404).body("해당 문의를 찾을 수 없습니다.");
        }
        return ResponseEntity.ok(inquiry);
    }

    // 문의 삭제 API (하드 삭제, 복구 불가)
    @DeleteMapping("/inquiry/{inqNum}")
    public ResponseEntity<?> deleteInquiry(@PathVariable("inqNum") int inqNum, HttpSession session) {
        String userId = (String) session.getAttribute("user");
        if (userId == null) {
            return ResponseEntity.status(401).body("로그인이 필요합니다.");
        }

        inquiryService.hardDeleteInquiry(inqNum, userId); // 하드 삭제 처리
        return ResponseEntity.ok(new SimpleResponse(true, "문의가 성공적으로 삭제되었습니다."));
    }

    // 문의 수정 API
    @PostMapping("/inquiry/{inqNum}")
    public ResponseEntity<?> editInquiry(
        @PathVariable("inqNum") int inqNum, 
        @RequestParam("inquiryTitle") String inquiryTitle,
        @RequestParam("inquiryType") String inquiryType,
        @RequestParam("inquiryText") String inquiryText,
        @RequestParam(value = "inquiryFile", required = false) MultipartFile inquiryFile,
        HttpSession session) {

        String userId = (String) session.getAttribute("user");
        if (userId == null) {
            return ResponseEntity.status(401).body("로그인이 필요합니다.");
        }

        Inquiry inquiryDetails = new Inquiry();
        inquiryDetails.setInqTitle(inquiryTitle);
        inquiryDetails.setInqText(inquiryText);
        inquiryDetails.setInqTypecode(inquiryType);

        if (inquiryFile != null && !inquiryFile.isEmpty()) {
            String fileName = inquiryService.saveFile(inquiryFile);
            inquiryDetails.setInqFile(fileName);
        }

        inquiryService.updateInquiry(inqNum, inquiryDetails, userId);
        return ResponseEntity.ok(new SimpleResponse(true, "수정되었습니다."));
    }
}
