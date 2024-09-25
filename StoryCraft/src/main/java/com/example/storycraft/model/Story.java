package com.example.storycraft.model;

import java.sql.Timestamp;
import org.springframework.web.multipart.MultipartFile;

public class Story {

	private Long stNum; // 스토리 번호
    private String stTitle; // 스토리 제목
    private Timestamp stCrdate; // 생성 날짜
    private Timestamp stEddate; // 수정 날짜
    private Long stViewnum; // 조회수
    private String stTypecode; // 스토리 유형 코드
    private String stGenrecode; // 장르 코드
    private Long stSugnum; // 추천수
    private String stCover; // 커버 이미지 경로
    private String stDstatus; // 삭제 상태
    private String stDreason; // 삭제 사유
    private Timestamp stDdate; // 삭제 날짜
    private String uId; // 작성자 (회원 ID)
    private String stContent; // 스토리 내용
    private MultipartFile stImg; // 이미지 등록

    public Story() {
    }

	public Story(Long stNum, String stTitle, Timestamp stCrdate, Timestamp stEddate, Long stViewnum, String stTypecode,
			String stGenrecode, Long stSugnum, String stCover, String stDstatus, String stDreason, Timestamp stDdate,
			String uId, String stContent, MultipartFile stImg) {
		super();
		this.stNum = stNum;
		this.stTitle = stTitle;
		this.stCrdate = stCrdate;
		this.stEddate = stEddate;
		this.stViewnum = stViewnum;
		this.stTypecode = stTypecode;
		this.stGenrecode = stGenrecode;
		this.stSugnum = stSugnum;
		this.stCover = stCover;
		this.stDstatus = stDstatus;
		this.stDreason = stDreason;
		this.stDdate = stDdate;
		this.uId = uId;
		this.stContent = stContent;
		this.stImg = stImg;
	}

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

	public Timestamp getStEddate() {
		return stEddate;
	}

	public void setStEddate(Timestamp stEddate) {
		this.stEddate = stEddate;
	}

	public Long getStViewnum() {
		return stViewnum;
	}

	public void setStViewnum(Long stViewnum) {
		this.stViewnum = stViewnum;
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

	public Long getStSugnum() {
		return stSugnum;
	}

	public void setStSugnum(Long stSugnum) {
		this.stSugnum = stSugnum;
	}

	public String getStCover() {
		return stCover;
	}

	public void setStCover(String stCover) {
		this.stCover = stCover;
	}

	public String getStDstatus() {
		return stDstatus;
	}

	public void setStDstatus(String stDstatus) {
		this.stDstatus = stDstatus;
	}

	public String getStDreason() {
		return stDreason;
	}

	public void setStDreason(String stDreason) {
		this.stDreason = stDreason;
	}

	public Timestamp getStDdate() {
		return stDdate;
	}

	public void setStDdate(Timestamp stDdate) {
		this.stDdate = stDdate;
	}

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

	public String getStContent() {
		return stContent;
	}

	public void setStContent(String stContent) {
		this.stContent = stContent;
	}

	public MultipartFile getStImg() {
		return stImg;
	}

	public void setStImg(MultipartFile stImg) {
		this.stImg = stImg;
	}

	@Override
	public String toString() {
		return "Story [stNum=" + stNum + ", stTitle=" + stTitle + ", stCrdate=" + stCrdate + ", stEddate=" + stEddate
				+ ", stViewnum=" + stViewnum + ", stTypecode=" + stTypecode + ", stGenrecode=" + stGenrecode
				+ ", stSugnum=" + stSugnum + ", stCover=" + stCover + ", stDstatus=" + stDstatus + ", stDreason="
				+ stDreason + ", stDdate=" + stDdate + ", uId=" + uId + ", stContent=" + stContent + ", stImg=" + stImg
				+ "]";
	}
    
}
