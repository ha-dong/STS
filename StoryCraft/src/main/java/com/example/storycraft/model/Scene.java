package com.example.storycraft.model;

public class Scene {
    private Long scNum;
    private Long playerNum;
    private String scText;
    private String scChoices;
    private int money;
    private int hp;
    private String endCode;  // 종료 코드 필드 추가
    private String soundCode;  // 사운드 코드 필드 추가

    public Scene() {
    }

    public Scene(Long scNum, Long playerNum, String scText, String scChoices, int money, int hp, String endCode, String soundCode) {
        this.scNum = scNum;
        this.playerNum = playerNum;
        this.scText = scText;
        this.scChoices = scChoices;
        this.money = money;
        this.hp = hp;
        this.endCode = endCode;  // 초기화
        this.soundCode = soundCode;  // 초기화
    }

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

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public String getEndCode() {
        return endCode;  // 추가된 메서드
    }

    public void setEndCode(String endCode) {
        this.endCode = endCode;  // 추가된 메서드
    }

    public String getSoundCode() {
        return soundCode;  // 추가된 메서드
    }

    public void setSoundCode(String soundCode) {
        this.soundCode = soundCode;  // 추가된 메서드
    }

    @Override
    public String toString() {
        return "Scene [scNum=" + scNum + ", playerNum=" + playerNum + ", scText=" + scText + ", scChoices=" + scChoices
                + ", money=" + money + ", hp=" + hp + ", endCode=" + endCode + ", soundCode=" + soundCode + "]";
    }
}
