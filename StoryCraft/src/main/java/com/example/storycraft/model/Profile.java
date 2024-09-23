package com.example.storycraft.model;

import java.util.Date;
import java.util.List;

public class Profile {

    private String username;        // 사용자 이름 (고유 ID)
    private String nickname;        // 사용자 닉네임
    private String profilePicture;  // 프로필 사진 경로 또는 URL
    private String bio;             // 사용자 자기소개
    private String gender;          // 사용자 성별 (U_GENDER)
    private Date birthday;          // 사용자 생일 (U_BIRTHDAY)
    private List<Story> createdStories; // 사용자가 작성한 스토리 목록
    private List<Story> likedStories;   // 사용자가 좋아요한 스토리 목록

    // 기본 생성자
    public Profile() {}

    // Getter 및 Setter 메서드
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    // setIntro 메서드 추가
    public void setIntro(String intro) {
        this.bio = intro;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    // birthday 필드의 Getter 및 Setter
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public List<Story> getCreatedStories() {
        return createdStories;
    }

    public void setCreatedStories(List<Story> createdStories) {
        this.createdStories = createdStories;
    }

    public List<Story> getLikedStories() {
        return likedStories;
    }

    public void setLikedStories(List<Story> likedStories) {
        this.likedStories = likedStories;
    }

    // toString 메서드 (디버깅 용도)
    @Override
    public String toString() {
        return "Profile{" +
                "username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                ", bio='" + bio + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
