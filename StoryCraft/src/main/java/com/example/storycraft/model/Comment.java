package com.example.storycraft.model;

import java.sql.Timestamp;

public class Comment {
    private int cmtNum;       // 댓글 번호
    private int inqNum;       // 문의 번호
    private Timestamp cmtDate; // 댓글 작성일
    private String cmtText;   // 댓글 내용

    // 기본 생성자
    public Comment() {
    }

    // 모든 필드를 포함하는 생성자
    public Comment(int cmtNum, int inqNum, Timestamp cmtDate, String cmtText) {
        this.cmtNum = cmtNum;
        this.inqNum = inqNum;
        this.cmtDate = cmtDate;
        this.cmtText = cmtText;
    }

    // Getter 및 Setter
    public int getCmtNum() {
        return cmtNum;
    }

    public void setCmtNum(int cmtNum) {
        this.cmtNum = cmtNum;
    }

    public int getInqNum() {
        return inqNum;
    }

    public void setInqNum(int inqNum) {
        this.inqNum = inqNum;
    }

    public Timestamp getCmtDate() {
        return cmtDate;
    }

    public void setCmtDate(Timestamp cmtDate) {
        this.cmtDate = cmtDate;
    }

    public String getCmtText() {
        return cmtText;
    }

    public void setCmtText(String cmtText) {
        this.cmtText = cmtText;
    }

    // toString 메서드 (디버깅용)
    @Override
    public String toString() {
        return "Comment{" +
                "cmtNum=" + cmtNum +
                ", inqNum=" + inqNum +
                ", cmtDate=" + cmtDate +
                ", cmtText='" + cmtText + '\'' +
                '}';
    }
}
