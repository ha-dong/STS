<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ICS - 메인 페이지</title>
    <link rel="stylesheet" href="/StoryCraft/resources/css/main.css">
</head>
<body>
    <div class="header">
        <img src="/StoryCraft/resources/img/logo.png" alt="로고" class="logo" onclick="location.href='main'">
        <span id="nickname" style="display: none; cursor: pointer;" onclick="location.href='profile'"></span>
        <button id="logoutButton" style="display: none;" onclick="logout()">로그아웃</button>
        <button id="signupButton" onclick="location.href='accession'">회원가입</button>
        <button id="loginButton" onclick="location.href='login'">로그인</button>
    </div>

    <div class="content">
        <div id="mainStory" class="highlight" onclick="startMainStory()">메인 스토리</div>
        <div id="userStory" class="highlight" onclick="checkUserStoryUnlocked()" style="opacity: 0.5; pointer-events: none;">
            사용자 스토리
        </div>
    </div>

    <div class="footer">
        <div class="icon-wrapper">
            <img src="${pageContext.request.contextPath}/resources/img/inquiry_icon.png" alt="문의" class="inquiry-icon" onclick="location.href='inquiry'">
            <span class="icon-label">문의사항</span>
        </div>
        <div class="icon-wrapper">
            <img src="${pageContext.request.contextPath}/resources/img/ntc.png" alt="공지사항" class="notice-icon" onclick="location.href='notice'">
            <span class="icon-label">공지사항</span>
        </div>
        <div class="icon-wrapper">
            <img src="${pageContext.request.contextPath}/resources/img/setting_icon.png" alt="설정" class="setting-icon" id="settingsIcon" onclick="openSettingsModal()">
            <span class="icon-label">설정</span>
        </div>
    </div>

    <!-- 설정 모달 -->
	<div id="settingsModal" class="modal">
	    <div class="modal-content">
	        <span class="close" id="closeSettingsButton" onclick="closeSettingsModal()">&times;</span>
	        <h2>설정</h2>
	        <div class="volume-controls">
	            <div class="control-box">
	                <label>배경음악</label>
	                <input type="range" min="0" max="100" value="50" id="bgmSlider" class="slider">
	            </div>
	            <div class="control-box">
	                <label>효과음</label>
	                <input type="range" min="0" max="100" value="50" id="sfxSlider" class="slider">
	            </div>
	        </div>
	        <div class="mute-options">
	            <label><input type="checkbox" id="muteBGM" onclick="toggleMute('bgmSlider')"> 음악 끄기</label>
	            <label><input type="checkbox" id="muteSFX" onclick="toggleMute('sfxSlider')"> 효과음 끄기</label>
	        </div>
	        <button id="deleteAccountButton" class="delete-account-btn" onclick="openDeleteAccountModal()">탈퇴하기</button>
	    </div>
	</div>

    <!-- 탈퇴 확인 모달 -->
    <div id="deleteAccountModal" class="modal">
        <div class="modal-content">
            <span class="close" onclick="closeDeleteAccountModal()">&times;</span>
            <h2>회원 탈퇴</h2>
            <p>정말로 탈퇴하시겠습니까?</p>
            <div class="delete-options">
                <button id="confirmDeleteButton" onclick="confirmDeleteAccount()">네</button>
                <button onclick="closeDeleteAccountModal()">아니오</button>
            </div>
        </div>
    </div>


    <script src="<c:url value='/resources/js/storyControl.js'/>"></script>
</body>
</html>
