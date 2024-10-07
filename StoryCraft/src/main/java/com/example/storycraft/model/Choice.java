package com.example.storycraft.model;

public class Choice {
    private int choiceNum;
    private int scNum;
    private int stNum;
    private int parentScNum;
    private int scLevel;
    private String choiceName;
    private String choiceContent;
    private Integer money;
    private Integer hp;
    private Integer nextScNum;
    
    public Choice() {
    }

	public Choice(int choiceNum, int scNum, int stNum, int parentScNum, int scLevel, String choiceName,
			String choiceContent, Integer money, Integer hp, Integer nextScNum) {
		super();
		this.choiceNum = choiceNum;
		this.scNum = scNum;
		this.stNum = stNum;
		this.parentScNum = parentScNum;
		this.scLevel = scLevel;
		this.choiceName = choiceName;
		this.choiceContent = choiceContent;
		this.money = money;
		this.hp = hp;
		this.nextScNum = nextScNum;
	}

	public int getChoiceNum() {
		return choiceNum;
	}

	public void setChoiceNum(int choiceNum) {
		this.choiceNum = choiceNum;
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

	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}

	public Integer getHp() {
		return hp;
	}

	public void setHp(Integer hp) {
		this.hp = hp;
	}

	public Integer getNextScNum() {
		return nextScNum;
	}

	public void setNextScNum(Integer nextScNum) {
		this.nextScNum = nextScNum;
	}

	@Override
	public String toString() {
		return "Choice [choiceNum=" + choiceNum + ", scNum=" + scNum + ", stNum=" + stNum + ", parentScNum="
				+ parentScNum + ", scLevel=" + scLevel + ", choiceName=" + choiceName + ", choiceContent="
				+ choiceContent + ", money=" + money + ", hp=" + hp + ", nextScNum=" + nextScNum + "]";
	}
    
}
