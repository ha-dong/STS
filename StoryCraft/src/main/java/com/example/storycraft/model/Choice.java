package com.example.storycraft.model;

public class Choice {
    private int choiceNum;
    private int sceneNum;
    private String choiceName;
    private String choiceContent;
    private int money;
    private int hp;

    public Choice() {
    }

	public Choice(int choiceNum, int sceneNum, String choiceName, String choiceContent, int money, int hp) {
		super();
		this.choiceNum = choiceNum;
		this.sceneNum = sceneNum;
		this.choiceName = choiceName;
		this.choiceContent = choiceContent;
		this.money = money;
		this.hp = hp;
	}

	public int getChoiceNum() {
		return choiceNum;
	}

	public void setChoiceNum(int choiceNum) {
		this.choiceNum = choiceNum;
	}

	public int getSceneNum() {
		return sceneNum;
	}

	public void setSceneNum(int sceneNum) {
		this.sceneNum = sceneNum;
	}

	public String getChoiceName() {
		return choiceName;
	}

	public void setChoiceName(String choiceName) {
		this.choiceName = choiceName;
	}

	public String getChoiceContent() {
		return choiceContent;
	}

	public void setChoiceContent(String choiceContent) {
		this.choiceContent = choiceContent;
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

	@Override
	public String toString() {
		return "Choice [choiceNum=" + choiceNum + ", sceneNum=" + sceneNum + ", choiceName=" + choiceName
				+ ", choiceContent=" + choiceContent + ", money=" + money + ", hp=" + hp + "]";
	}
    
}
