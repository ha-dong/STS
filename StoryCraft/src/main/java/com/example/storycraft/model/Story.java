// Story.java

package com.example.storycraft.model;

import java.sql.Timestamp;
import java.util.List;

public class Story {

    private int stNum;
    private String stTitle;
    private Timestamp stCrdate;
    private Timestamp stEddate;
    private int stViewnum;
    private String stTypecode;
    private String stGenrecode;
    private int stSugnum;
    private String stCover;
    private String stDstatus;
    private String stDreason;
    private Timestamp stDdate;
    private String uId;
    private String endCode;
    private Integer initialMoney;
    private Integer initialHP;
    private int stRenum;
    private List<Scene> scenes;

    public Story() {
    }

	public Story(int stNum, String stTitle, Timestamp stCrdate, Timestamp stEddate, int stViewnum, String stTypecode,
			String stGenrecode, int stSugnum, String stCover, String stDstatus, String stDreason, Timestamp stDdate,
			String uId, String endCode, Integer initialMoney, Integer initialHP, int stRenum, List<Scene> scenes) {
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
		this.endCode = endCode;
		this.initialMoney = initialMoney;
		this.initialHP = initialHP;
		this.stRenum = stRenum;
		this.scenes = scenes;
	}

	public int getStNum() {
		return stNum;
	}

	public void setStNum(int stNum) {
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

	public int getStViewnum() {
		return stViewnum;
	}

	public void setStViewnum(int stViewnum) {
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

	public int getStSugnum() {
		return stSugnum;
	}

	public void setStSugnum(int stSugnum) {
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

	public String getEndCode() {
		return endCode;
	}

	public void setEndCode(String endCode) {
		this.endCode = endCode;
	}

	public Integer getInitialMoney() {
		return initialMoney;
	}

	public void setInitialMoney(Integer initialMoney) {
		this.initialMoney = initialMoney;
	}

	public Integer getInitialHP() {
		return initialHP;
	}

	public void setInitialHP(Integer initialHP) {
		this.initialHP = initialHP;
	}

	public int getStRenum() {
		return stRenum;
	}

	public void setStRenum(int stRenum) {
		this.stRenum = stRenum;
	}

	public List<Scene> getScenes() {
		return scenes;
	}

	public void setScenes(List<Scene> scenes) {
		this.scenes = scenes;
	}

	@Override
	public String toString() {
		return "Story [stNum=" + stNum + ", stTitle=" + stTitle + ", stCrdate=" + stCrdate + ", stEddate=" + stEddate
				+ ", stViewnum=" + stViewnum + ", stTypecode=" + stTypecode + ", stGenrecode=" + stGenrecode
				+ ", stSugnum=" + stSugnum + ", stCover=" + stCover + ", stDstatus=" + stDstatus + ", stDreason="
				+ stDreason + ", stDdate=" + stDdate + ", uId=" + uId + ", endCode=" + endCode + ", initialMoney="
				+ initialMoney + ", initialHP=" + initialHP + ", stRenum=" + stRenum + ", scenes=" + scenes + "]";
	}
    
}
