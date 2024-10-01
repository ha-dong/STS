package com.example.storycraft.model;

import java.sql.Timestamp;

public class User {
    private String uId;
    private String uPw;
    private String uName;
    private String uPhone;
    private String uAdd;
    private String uProfile;
    private String uEmail;
    private String uCertified;
    private Timestamp uCdate;
    private Timestamp uEdate;
    private String uActivate;
    private String uNickname;
    private String uItd;
    private Timestamp uBirthday;
    private String uGender;
    private String mainComplete;
    private String uCode;
    private String uDstatus;
    
    // 추가된 필드
    private String uDreason;
    private Timestamp uDdate;
    private String deactivatedBy; // 추가: 'ADMIN' 또는 'USER'
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	public String getuPw() {
		return uPw;
	}
	public void setuPw(String uPw) {
		this.uPw = uPw;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public String getuPhone() {
		return uPhone;
	}
	public void setuPhone(String uPhone) {
		this.uPhone = uPhone;
	}
	public String getuAdd() {
		return uAdd;
	}
	public void setuAdd(String uAdd) {
		this.uAdd = uAdd;
	}
	public String getuProfile() {
		return uProfile;
	}
	public void setuProfile(String uProfile) {
		this.uProfile = uProfile;
	}
	public String getuEmail() {
		return uEmail;
	}
	public void setuEmail(String uEmail) {
		this.uEmail = uEmail;
	}
	public String getuCertified() {
		return uCertified;
	}
	public void setuCertified(String uCertified) {
		this.uCertified = uCertified;
	}
	public Timestamp getuCdate() {
		return uCdate;
	}
	public void setuCdate(Timestamp uCdate) {
		this.uCdate = uCdate;
	}
	public Timestamp getuEdate() {
		return uEdate;
	}
	public void setuEdate(Timestamp uEdate) {
		this.uEdate = uEdate;
	}
	public String getuActivate() {
		return uActivate;
	}
	public void setuActivate(String uActivate) {
		this.uActivate = uActivate;
	}
	public String getuNickname() {
		return uNickname;
	}
	public void setuNickname(String uNickname) {
		this.uNickname = uNickname;
	}
	public String getuItd() {
		return uItd;
	}
	public void setuItd(String uItd) {
		this.uItd = uItd;
	}
	public Timestamp getuBirthday() {
		return uBirthday;
	}
	public void setuBirthday(Timestamp uBirthday) {
		this.uBirthday = uBirthday;
	}
	public String getuGender() {
		return uGender;
	}
	public void setuGender(String uGender) {
		this.uGender = uGender;
	}
	public String getMainComplete() {
		return mainComplete;
	}
	public void setMainComplete(String mainComplete) {
		this.mainComplete = mainComplete;
	}
	public String getuCode() {
		return uCode;
	}
	public void setuCode(String uCode) {
		this.uCode = uCode;
	}
	public String getuDstatus() {
		return uDstatus;
	}
	public void setuDstatus(String uDstatus) {
		this.uDstatus = uDstatus;
	}
	public String getuDreason() {
		return uDreason;
	}
	public void setuDreason(String uDreason) {
		this.uDreason = uDreason;
	}
	public Timestamp getuDdate() {
		return uDdate;
	}
	public void setuDdate(Timestamp uDdate) {
		this.uDdate = uDdate;
	}
	public String getDeactivatedBy() {
		return deactivatedBy;
	}
	public void setDeactivatedBy(String deactivatedBy) {
		this.deactivatedBy = deactivatedBy;
	}

    // 기존 Getters and Setters...


}
