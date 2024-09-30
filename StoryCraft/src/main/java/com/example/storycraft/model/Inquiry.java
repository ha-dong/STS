package com.example.storycraft.model;

import java.sql.Timestamp;

public class Inquiry {
    private int inqNum;          // 문의 번호
    private String inqTitle;     // 문의 제목
    private String userId;       // 작성자 ID
    private String inqTypecode;  // 문의 종류 코드
    private String inqGenrecode; // 문의 장르 코드 또는 상태 코드
    private String inqText;      // 문의 내용
    private String inqFile;      // 첨부 파일 이름
    private Timestamp inqCrdate; // 작성일
    private String inqDstatus;   // 삭제 상태 (Active 또는 Deleted)

    // 기본 생성자
    public Inquiry() {}

    // 모든 필드를 포함하는 생성자
    public Inquiry(int inqNum, String inqTitle, String userId, String inqTypecode,
                   String inqGenrecode, String inqText, String inqFile,
                   Timestamp inqCrdate, String inqDstatus) {
        this.inqNum = inqNum;
        this.inqTitle = inqTitle;
        this.userId = userId;
        this.inqTypecode = inqTypecode;
        this.inqGenrecode = inqGenrecode;
        this.inqText = inqText;
        this.inqFile = inqFile;
        this.inqCrdate = inqCrdate;
        this.inqDstatus = inqDstatus;
    }

    // Getter 및 Setter 메서드

    public int getInqNum() {
        return inqNum;
    }

    public void setInqNum(int inqNum) {
        this.inqNum = inqNum;
    }

    public String getInqTitle() {
        return inqTitle;
    }

    public void setInqTitle(String inqTitle) {
        this.inqTitle = inqTitle;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getInqTypecode() {
        return inqTypecode;
    }

    public void setInqTypecode(String inqTypecode) {
        this.inqTypecode = inqTypecode;
    }

    public String getInqGenrecode() {
        return inqGenrecode;
    }

    public void setInqGenrecode(String inqGenrecode) {
        this.inqGenrecode = inqGenrecode;
    }

    public String getInqText() {
        return inqText;
    }

    public void setInqText(String inqText) {
        this.inqText = inqText;
    }

    public String getInqFile() {
        return inqFile;
    }

    public void setInqFile(String inqFile) {
        this.inqFile = inqFile;
    }

    public Timestamp getInqCrdate() {
        return inqCrdate;
    }

    public void setInqCrdate(Timestamp inqCrdate) {
        this.inqCrdate = inqCrdate;
    }

    public String getInqDstatus() {
        return inqDstatus;
    }

    public void setInqDstatus(String inqDstatus) {
        this.inqDstatus = inqDstatus;
    }

    // toString 메서드 (디버깅용)
    @Override
    public String toString() {
        return "Inquiry{" +
                "inqNum=" + inqNum +
                ", inqTitle='" + inqTitle + '\'' +
                ", userId='" + userId + '\'' +
                ", inqTypecode='" + inqTypecode + '\'' +
                ", inqGenrecode='" + inqGenrecode + '\'' +
                ", inqText='" + inqText + '\'' +
                ", inqFile='" + inqFile + '\'' +
                ", inqCrdate=" + inqCrdate +
                ", inqDstatus='" + inqDstatus + '\'' +
                '}';
    }
}
