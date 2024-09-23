package com.example.storycraft.model;

import java.sql.Timestamp;
import java.util.List;

public class Story {
    private Long stNum;
    private String stTitle;
    private Timestamp stCrdate;  // Timestamp로 수정
    private String stTypecode;
    private String stGenrecode;
    private String uId;
    private List<String> likedUsers;

    // Getter 및 Setter 메서드
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

    public void setStCrdate(Timestamp stCrdate) {  // Timestamp로 변경된 setter
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

    public List<String> getLikedUsers() {
        return likedUsers;
    }

    public void setLikedUsers(List<String> likedUsers) {
        this.likedUsers = likedUsers;
    }
}
