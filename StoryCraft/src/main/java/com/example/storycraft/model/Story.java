package com.example.storycraft.model;

import java.sql.Timestamp;


public class Story {


    private Long stNum;  // 스토리 번호
    private String stTitle;  // 스토리 제목
    private Timestamp stCrdate;  // 생성 날짜
    private String stTypecode;  // 스토리 유형 코드 (예: 메인 스토리, 사용자 스토리 등)
    private String stGenrecode;  // 장르 코드 (예: 판타지, 스릴러 등)
    private String uId;  // 작성자 (회원 ID)

    // Getters and Setters
    public Long getStNum() {
        return stNum;
    }

    public void setStNum(Long stNum) {
        this.stNum = stNum;
    }

    public String getStTitle() {
        return stTitle;
    }

    public void setStTitle(String stTitle) {
        this.stTitle = stTitle;
    }

    public Timestamp getStCrdate() {
        return stCrdate;
    }

    public void setStCrdate(Timestamp stCrdate) {
        this.stCrdate = stCrdate;
    }

    public String getStTypecode() {
        return stTypecode;
    }

    public void setStTypecode(String stTypecode) {
        this.stTypecode = stTypecode;
    }

    public String getStGenrecode() {
        return stGenrecode;
    }

    public void setStGenrecode(String stGenrecode) {
        this.stGenrecode = stGenrecode;
    }

    public String getUId() {
        return uId;
    }

    public void setUId(String uId) {
        this.uId = uId;
    }

	@Override
	public String toString() {
		return "Story [stNum=" + stNum + ", stTitle=" + stTitle + ", stCrdate=" + stCrdate + ", stTypecode="
				+ stTypecode + ", stGenrecode=" + stGenrecode + ", uId=" + uId + "]";
	}
 
}
