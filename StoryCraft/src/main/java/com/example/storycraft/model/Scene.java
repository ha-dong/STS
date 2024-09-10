package com.example.storycraft.model;


public class Scene {


    private Long scNum;  // 장면 번호
    private Long playerNum;  // 스토리와 연결된 플레이어 번호
    private String scText;  // 장면 내용
    private String scChoices;  // 선택지

    // Getters and Setters
    public Long getScNum() {
        return scNum;
    }

    public void setScNum(Long scNum) {
        this.scNum = scNum;
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
}
