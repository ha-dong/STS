// Notice.java

package com.example.storycraft.model;

import java.util.Date;

public class Notice {
    private Long ntNum;
    private String ntTypeCode;
    private String ntTitle;
    private String ntText;
    private Date ntCrdate;
    private Date ntEddate;
    private String uid; // 수정된 부분: uId → uid

    // 기본 생성자
    public Notice() {
        this.ntCrdate = new Date();  // 생성 시 자동으로 현재 날짜 설정
    }

    // 생성자
    public Notice(Long ntNum, String ntTypeCode, String ntTitle, String ntText, Date ntCrdate, Date ntEddate, String uid) {
        this.ntNum = ntNum;
        this.ntTypeCode = ntTypeCode;
        this.ntTitle = ntTitle;
        this.ntText = ntText;
        this.ntCrdate = ntCrdate;
        this.ntEddate = ntEddate;
        this.uid = uid;
    }

    // Getters and Setters
    public Long getNtNum() {
        return ntNum;
    }

    public void setNtNum(Long ntNum) {
        this.ntNum = ntNum;
    }

    public String getNtTypeCode() {
        return ntTypeCode;
    }

    public void setNtTypeCode(String ntTypeCode) {
        this.ntTypeCode = ntTypeCode;
    }

    public String getNtTitle() {
        return ntTitle;
    }

    public void setNtTitle(String ntTitle) {
        this.ntTitle = ntTitle;
    }

    public String getNtText() {
        return ntText;
    }

    public void setNtText(String ntText) {
        this.ntText = ntText;
    }

    public Date getNtCrdate() {
        return ntCrdate;
    }

    public void setNtCrdate(Date ntCrdate) {
        this.ntCrdate = ntCrdate;
    }

    public Date getNtEddate() {
        return ntEddate;
    }

    public void setNtEddate(Date ntEddate) {
        this.ntEddate = ntEddate;
    }

    public String getUid() { // 수정된 부분: getUId() → getUid()
        return uid;
    }

    public void setUid(String uid) { // 수정된 부분: setUId() → setUid()
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "Notice [ntNum=" + ntNum + ", ntTypeCode=" + ntTypeCode + ", ntTitle=" + ntTitle + ", ntText=" + ntText
                + ", ntCrdate=" + ntCrdate + ", ntEddate=" + ntEddate + ", uid=" + uid + "]";
    }
}
