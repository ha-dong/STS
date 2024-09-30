package com.example.storycraft.service;

import com.example.storycraft.dao.InquiryDao;
import com.example.storycraft.model.Comment;
import com.example.storycraft.model.Inquiry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class InquiryService {

    @Autowired
    private InquiryDao inquiryDao;

    // 문의 저장
    public void saveInquiry(Inquiry inquiry) {
        // 문의 등록 시 기본적으로 INQ_GENRECODE는 "접수 완료"로 설정
        inquiry.setInqGenrecode("접수 완료");
        inquiryDao.insertInquiry(inquiry);
    }

    // 파일 저장
    public String saveFile(MultipartFile file) {
        String originalFileName = file.getOriginalFilename();
        String fileExtension = "";
        if (originalFileName != null && originalFileName.contains(".")) {
            fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        }
        String newFileName = UUID.randomUUID().toString() + fileExtension;

        try {
            // 파일 저장 경로를 실제 경로로 변경해야 합니다.
            File saveFile = new File("/path/to/save/files", newFileName);
            file.transferTo(saveFile);
            return newFileName;
        } catch (IOException e) {
            throw new RuntimeException("파일 저장 중 오류 발생", e);
        }
    }

    // 모든 문의 목록 가져오기
    public List<Inquiry> getAllInquiries() {
        return inquiryDao.getAllInquiries();
    }

    // 문의 상세 정보 가져오기
    public Inquiry getInquiryDetail(int inqNum) {
        return inquiryDao.getInquiryDetail(inqNum);
    }

    // 문의 삭제 처리 (완전 삭제)
    public void hardDeleteInquiry(int inqNum) {
        inquiryDao.hardDeleteInquiry(inqNum);
    }

    // 문의 수정 처리
    public void updateInquiry(int inqNum, Inquiry inquiryDetails) {
        inquiryDao.updateInquiry(inqNum, inquiryDetails);
    }
    
    // 문의 상태 업데이트
    public void updateInquiryStatus(int inqNum, String newStatus) {
        inquiryDao.updateInquiryStatus(inqNum, newStatus);
    }

    // 댓글 추가
    public void addComment(int inqNum, String commentText) {
        inquiryDao.insertComment(inqNum, commentText);
    }
 
    public List<Comment> getCommentsForInquiry(int inqNum) {
        return inquiryDao.getCommentsForInquiry(inqNum);
    }
    
}
