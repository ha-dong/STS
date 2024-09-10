package com.example.storycraft.service;

import com.example.storycraft.dao.InquiryDao;
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
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String newFileName = UUID.randomUUID().toString() + fileExtension;

        try {
            File saveFile = new File("/path/to/save/files", newFileName);
            file.transferTo(saveFile);
            return newFileName;
        } catch (IOException e) {
            throw new RuntimeException("파일 저장 중 오류 발생", e);
        }
    }

    // 사용자 ID에 따른 문의 목록 가져오기
    public List<Inquiry> getInquiryListByUserId(String userId) {
        return inquiryDao.getInquiryListByUserId(userId);
    }

    // 문의 상세 정보 가져오기
    public Inquiry getInquiryDetail(int inqNum, String userId) {
        return inquiryDao.getInquiryDetail(inqNum, userId);
    }

    // 문의 삭제 처리 (완전 삭제)
    public void hardDeleteInquiry(int inqNum, String userId) {
        inquiryDao.hardDeleteInquiry(inqNum, userId);
    }

    // 문의 수정 처리
    public void updateInquiry(int inqNum, Inquiry inquiryDetails, String userId) {
        inquiryDao.updateInquiry(inqNum, inquiryDetails, userId);
    }
}
