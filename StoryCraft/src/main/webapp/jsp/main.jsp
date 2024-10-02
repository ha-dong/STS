<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ICS - 메인 페이지</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css"> <!-- contextPath 사용 -->
    <link href="https://cdn.jsdelivr.net/gh/spoqa/spoqa-han-sans/css/SpoqaHanSansNeo.css" rel="stylesheet" type="text/css" />

    <!-- 컨텍스트 경로와 사용자 이름을 JavaScript 변수로 설정 -->
    <script>
        var contextPath = '${pageContext.request.contextPath}';
        var storedUsername = ''; // 메인 페이지에서는 필요에 따라 설정
    </script>
    <script src="${pageContext.request.contextPath}/resources/js/storyControl.js"></script>
</head>
<body>
    <div class="header">
        <img src="${pageContext.request.contextPath}/resources/img/logo.png" alt="로고" class="logo" id="logo">
        
        <!-- 프로필 이미지 (로그아웃 버튼 옆에 배치) -->
        <c:choose>
            <c:when test="${profile.profilePicture != null}">
                <img id="profileImage"
             src="${contextPath}/resources/img/default_profile.png"
             style="width: 50px; height: 50px; object-fit: cover; border-radius: 50%; cursor: pointer; display: none;"
                     onclick="window.location.href='${contextPath}/StoryCraft/profile/${profile.username}';">
            </c:when>
            <c:otherwise>
                <img id="profileImage"
                     src="${pageContext.request.contextPath}/resources/img/default_profile.png"
                     style="width: 50px; height: 50px; object-fit: cover; border-radius: 50%; cursor: pointer;"
                     onclick="window.location.href='${contextPath}/StoryCraft/profile/${profile.username}';">
            </c:otherwise>
        </c:choose>
        
        <button id="logoutButton" style="display: none;" onclick="logout()">로그아웃</button>
        <button id="signupButton" onclick="location.href='${contextPath}/StoryCraft/accession'">회원가입</button>
        <button id="loginButton" onclick="location.href='${contextPath}/StoryCraft/login'">로그인</button>
    </div>

    <!-- 배경음악 오디오 태그 -->
    <audio id="backgroundMusic" src="${pageContext.request.contextPath}/resources/audio/BGM.mp3" loop></audio>   

    <div class="content">
        <div id="mainStory" class="highlight" onclick="startMainStory()">메인 스토리</div>
        <div id="userStory" class="highlight" onclick="checkUserStoryUnlocked()" style="opacity: 0.5; pointer-events: none;">사용자 스토리</div>
    </div>

    <div class="footer">
       <div class="icon-container">
           <div class="icon-wrapper">
               <img src="${pageContext.request.contextPath}/resources/img/inquiry_icon.png" alt="문의" class="inquiry-icon">
               <span class="icon-label">문의사항</span>
           </div>
           <div class="icon-wrapper">
               <img src="${pageContext.request.contextPath}/resources/img/ntc.png" alt="공지사항" class="notice-icon">
               <span class="icon-label">공지사항</span>
           </div>
           <div class="icon-wrapper">
               <img src="${pageContext.request.contextPath}/resources/img/setting_icon.png" alt="설정" class="setting-icon" id="settingsIcon">
               <span class="icon-label">설정</span>
           </div>
       </div>
    </div>

    <!-- 공지사항 모달 -->
    <div id="noticeModal" class="modal">
        <div class="modal-content">
            <span class="close" id="closeNoticeModal">&times;</span>
            <h2>공지사항</h2>
            <table id="noticeTable">
                <thead>
                    <tr>
                        <th></th>
                        <th>분류</th>
                        <th>제목</th>
                        <th>작성일</th>
                        <th>작성자</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- Notices will be inserted here via JavaScript -->
                </tbody>
            </table>
            <!-- 공지사항 내용 표시 영역 추가 -->
            <div id="noticeContent" class="notice-content">
                <h3>공지사항 내용</h3>
                <p id="noticeText">제목을 클릭하면 내용이 표시됩니다.</p>
            </div>
        </div>
    </div>

    <!-- 설정 모달 -->
    <div id="settingsModal" class="modal">
        <div class="modal-content">
            <span class="close" id="closeSettingsButton">&times;</span>
            <h2>설정</h2>
            <div class="volume-controls">
                <div class="control-box">
                    <label for="bgmSlider">배경음악</label>
                    <input type="range" min="0" max="100" value="50" id="bgmSlider" class="slider">
                </div>
                <div class="control-box">
                    <label for="sfxSlider">효과음</label>
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

    <script>
        // storyControl.js에서 모든 초기화를 처리함
    </script>
</body>
</html>
