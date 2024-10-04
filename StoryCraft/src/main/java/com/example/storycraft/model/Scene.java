// Scene.java

package com.example.storycraft.model;

import java.util.List;

public class Scene {

    private int scNum;
    private int stNum;
    private int parentScNum;
    private int scLevel;
    private String scText;
    private String scIllus;
    private int money;
    private int hp;
    private int tempSceneNum = -1;
    private List<Choice> choices;

    public Scene() {
    }

	public Scene(int scNum, int stNum, int parentScNum, int scLevel, String scText, String scIllus, int money, int hp,
			int tempSceneNum, List<Choice> choices) {
		super();
		this.scNum = scNum;
		this.stNum = stNum;
		this.parentScNum = parentScNum;
		this.scLevel = scLevel;
		this.scText = scText;
		this.scIllus = scIllus;
		this.money = money;
		this.hp = hp;
		this.tempSceneNum = tempSceneNum;
		this.choices = choices;
	}

	public int getScNum() {
		return scNum;
	}

	public void setScNum(int scNum) {
		this.scNum = scNum;
	}

	public int getStNum() {
		return stNum;
	}

	public void setStNum(int stNum) {
		this.stNum = stNum;
	}

	public int getParentScNum() {
		return parentScNum;
	}

	public void setParentScNum(int parentScNum) {
		this.parentScNum = parentScNum;
	}

	public int getScLevel() {
		return scLevel;
	}

	public void setScLevel(int scLevel) {
		this.scLevel = scLevel;
	}

	public String getScText() {
		return scText;
	}

	public void setScText(String scText) {
		this.scText = scText;
	}

	public String getScIllus() {
		return scIllus;
	}

	public void setScIllus(String scIllus) {
		this.scIllus = scIllus;
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

	public int getTempSceneNum() {
		return tempSceneNum;
	}

	public void setTempSceneNum(int tempSceneNum) {
		this.tempSceneNum = tempSceneNum;
	}

	public List<Choice> getChoices() {
		return choices;
	}

	public void setChoices(List<Choice> choices) {
		this.choices = choices;
	}

	@Override
	public String toString() {
		return "Scene [scNum=" + scNum + ", stNum=" + stNum + ", parentScNum=" + parentScNum + ", scLevel=" + scLevel
				+ ", scText=" + scText + ", scIllus=" + scIllus + ", money=" + money + ", hp=" + hp + ", tempSceneNum="
				+ tempSceneNum + ", choices=" + choices + "]";
	}
    
}
