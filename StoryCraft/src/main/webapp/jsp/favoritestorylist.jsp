<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ICS - 내가 좋아요 누른 스토리 목록</title>
    <link rel="stylesheet" href="<c:url value='/resources/css/favoritestorylist.css'/>">
</head>
<body>
    <div class="header">
        <img src="/StoryCraft/resources/img/logo.png" alt="로고" class="logo" onclick="location.href='main'">
        <button id="signupButton" onclick="location.href='register'">회원가입</button>
        <button id="loginButton" onclick="location.href='login'">로그인</button>
        <button id="logoutButton" style="display: none;" onclick="logout()">로그아웃</button>
    </div>

    <main>
        <h1>내가 좋아요 누른 스토리 목록</h1>

        <!-- 장르 탭 -->
        <div class="genre-tabs">
            <button class="genre-tab">전체</button>
            <button class="genre-tab">사극</button>
            <button class="genre-tab">스릴러</button>
            <button class="genre-tab">판타지</button>
            <button class="genre-tab">코미디</button>
            <button class="genre-tab">SF</button>
            <button class="genre-tab">로맨스</button>
        </div>

        <!-- 스토리 리스트 -->
        <div class="story-list">
            <!-- 좋아요한 스토리 목록 데이터를 동적으로 출력 -->
            <c:forEach var="favoriteStory" items="${favoriteStoryList}">
                <div>${favoriteStory.title}</div>
            </c:forEach>
        </div>

        <!-- 정렬 드롭다운 -->
        <div class="sort-dropdown">
            <select>
                <option disabled selected>--인기--</option>
                <option>인기순</option>
                <option>최신순</option>
            </select>
        </div>

        <!-- 커스텀 설정 버튼 -->
        <button class="customize-button">커스텀 설정</button>
    </main>

    <!-- Footer Icons -->
    <div class="footer">
        <div class="icon-wrapper">
            <img src="/StoryCraft/resources/img/inquiry_icon.png" alt="문의" class="inquiry-icon" onclick="location.href='inquiry'">
            <span class="icon-label">문의사항</span>
        </div>
        <div class="icon-wrapper">
            <img src="/StoryCraft/resources/img/ntc.png" alt="공지사항" class="notice-icon" onclick="location.href='notice'">
            <span class="icon-label">공지사항</span>
        </div>
        <div class="icon-wrapper">
            <img src="/StoryCraft/resources/img/setting_icon.png" alt="설정" class="setting-icon" onclick="showSettingsModal()">
            <span class="icon-label">설정</span>
        </div>
    </div>

    <!-- 설정 모달 -->
    <div id="settingsModal" class="modal">
        <div class="modal-content">
            <span class="close" onclick="closeSettingsModal()">&times;</span>
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
            <button id="deleteAccountButton" class="delete-account-btn" onclick="showDeleteAccountModal()">탈퇴하기</button>
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
    <script src="<c:url value='/resources/js/favortiestorylist.js'/>"></script>
</body>
</html>
