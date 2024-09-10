package com.example.storycraft.model;

import java.sql.Timestamp;

public class Inquiry {
    private int inqNum;
    private String inqTitle;
    private String inqText;
    private String inqTypecode;
    private String inqGenrecode;
    private String inqFile;
    private Timestamp inqCrdate;
    private String inqDstatus;
    private String uId;
    private String inqDreason;  // 삭제 사유
    private Timestamp inqDdate; // 삭제 날짜

    public Inquiry() {
    }

    public Inquiry(int inqNum, String inqTitle, String inqText, String inqTypecode, String inqGenrecode, String inqFile,
                   Timestamp inqCrdate, String inqDstatus, String uId, String inqDreason, Timestamp inqDdate) {
        this.inqNum = inqNum;
        this.inqTitle = inqTitle;
        this.inqText = inqText;
        this.inqTypecode = inqTypecode;
        this.inqGenrecode = inqGenrecode;
        this.inqFile = inqFile;
        this.inqCrdate = inqCrdate;
        this.inqDstatus = inqDstatus;
        this.uId = uId;
        this.inqDreason = inqDreason;
        this.inqDdate = inqDdate;
    }

    // getter 및 setter 메서드들

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

    public String getInqText() {
        return inqText;
    }

    public void setInqText(String inqText) {
        this.inqText = inqText;
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

    public String getUId() {
        return uId;
    }

    public void setUId(String uId) {
        this.uId = uId;
    }

    // 삭제 사유 (inqDreason) getter 및 setter
    public String getInqDreason() {
        return inqDreason;
    }

    public void setInqDreason(String inqDreason) {
        this.inqDreason = inqDreason;
    }

    // 삭제 날짜 (inqDdate) getter 및 setter
    public Timestamp getInqDdate() {
        return inqDdate;
    }

    public void setInqDdate(Timestamp inqDdate) {
        this.inqDdate = inqDdate;
    }

    @Override
    public String toString() {
        return "Inquiry [inqNum=" + inqNum + ", inqTitle=" + inqTitle + ", inqText=" + inqText + ", inqTypecode="
                + inqTypecode + ", inqGenrecode=" + inqGenrecode + ", inqFile=" + inqFile + ", inqCrdate=" + inqCrdate
                + ", inqDstatus=" + inqDstatus + ", uId=" + uId + ", inqDreason=" + inqDreason + ", inqDdate=" + inqDdate + "]";
    }
}
