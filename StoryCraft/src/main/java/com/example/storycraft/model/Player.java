package com.example.storycraft.model;

public class Player {

	private Long playerNum; // 플레이어 번호
    private Long stNum; // 스토리 번호
    private String sugStatus; // 추천 상태

    public Player() {
    }

	public Player(Long playerNum, Long stNum, String sugStatus) {
		super();
		this.playerNum = playerNum;
		this.stNum = stNum;
		this.sugStatus = sugStatus;
	}

	public Long getPlayerNum() {
		return playerNum;
	}

	public void setPlayerNum(Long playerNum) {
		this.playerNum = playerNum;
	}

	public Long getStNum() {
		return stNum;
	}

	public void setStNum(Long stNum) {
		this.stNum = stNum;
	}

	public String getSugStatus() {
		return sugStatus;
	}

	public void setSugStatus(String sugStatus) {
		this.sugStatus = sugStatus;
	}

	@Override
	public String toString() {
		return "Player [playerNum=" + playerNum + ", stNum=" + stNum + ", sugStatus=" + sugStatus + "]";
	}
	
}
