package com.example.storycraft.model;

import org.springframework.web.multipart.MultipartFile;

public class Scene {

	private Long scNum; // 씬 번호
    private Long stNum; // 스토리 번호
    private Long parentScNum; // 부모 씬 번호
    private Long scLevel; // 씬 레벨
    private MultipartFile scImg; // 이미지 등록
    private Long playerNum; // 플레이어 번호
    private String scText; // 씬 내용
    private String scChoices; // 선택지
    private String scIllus; // 일러스트 경로
    private Long money; // 게임에서의 금액
    private Long hp; // 플레이어의 체력
    private String endCode; // 엔딩 코드
    private String soundCode; // 배경음악 코드

    public Scene() {
    }

	public Scene(Long scNum, Long stNum, Long parentScNum, Long scLevel, MultipartFile scImg, Long playerNum,
			String scText, String scChoices, String scIllus, Long money, Long hp, String endCode, String soundCode) {
		super();
		this.scNum = scNum;
		this.stNum = stNum;
		this.parentScNum = parentScNum;
		this.scLevel = scLevel;
		this.scImg = scImg;
		this.playerNum = playerNum;
		this.scText = scText;
		this.scChoices = scChoices;
		this.scIllus = scIllus;
		this.money = money;
		this.hp = hp;
		this.endCode = endCode;
		this.soundCode = soundCode;
	}

	public Long getScNum() {
		return scNum;
	}

	public void setScNum(Long scNum) {
		this.scNum = scNum;
	}

	public Long getStNum() {
		return stNum;
	}

	public void setStNum(Long stNum) {
		this.stNum = stNum;
	}

	public Long getParentScNum() {
		return parentScNum;
	}

	public void setParentScNum(Long parentScNum) {
		this.parentScNum = parentScNum;
	}

	public Long getScLevel() {
		return scLevel;
	}

	public void setScLevel(Long scLevel) {
		this.scLevel = scLevel;
	}

	public MultipartFile getScImg() {
		return scImg;
	}

	public void setScImg(MultipartFile scImg) {
		this.scImg = scImg;
	}

	public Long getPlayerNum() {
		return playerNum;
	}

	public void setPlayerNum(Long playerNum) {
		this.playerNum = playerNum;
	}

	public String getScText() {
		return scText;
	}

	public void setScText(String scText) {
		this.scText = scText;
	}

	public String getScChoices() {
		return scChoices;
	}

	public void setScChoices(String scChoices) {
		this.scChoices = scChoices;
	}

	public String getScIllus() {
		return scIllus;
	}

	public void setScIllus(String scIllus) {
		this.scIllus = scIllus;
	}

	public Long getMoney() {
		return money;
	}

	public void setMoney(Long money) {
		this.money = money;
	}

	public Long getHp() {
		return hp;
	}

	public void setHp(Long hp) {
		this.hp = hp;
	}

	public String getEndCode() {
		return endCode;
	}

	public void setEndCode(String endCode) {
		this.endCode = endCode;
	}

	public String getSoundCode() {
		return soundCode;
	}

	public void setSoundCode(String soundCode) {
		this.soundCode = soundCode;
	}

	@Override
	public String toString() {
		return "Scene [scNum=" + scNum + ", stNum=" + stNum + ", parentScNum=" + parentScNum + ", scLevel=" + scLevel
				+ ", scImg=" + scImg + ", playerNum=" + playerNum + ", scText=" + scText + ", scChoices=" + scChoices
				+ ", scIllus=" + scIllus + ", money=" + money + ", hp=" + hp + ", endCode=" + endCode + ", soundCode="
				+ soundCode + "]";
	}
    
}
