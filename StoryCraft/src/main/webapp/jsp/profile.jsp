<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ICS - 회원 프로필</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/profile.css">
    
    <!-- contextPath를 JavaScript 변수로 설정 -->
    <script>
        var contextPath = '${pageContext.request.contextPath}';
    </script>
</head>
<body>
    <div class="header">
        <img src="${pageContext.request.contextPath}/resources/img/logo.png" alt="로고" class="logo" onclick="location.href='main'">
        
        <!-- 프로필 이미지 (로그아웃 버튼 옆에 배치) -->
        <c:choose>
            <c:when test="${profile.profilePicture != null}">
                <img id="profileImage"
                     src="${pageContext.request.contextPath}/profile-images/${profile.profilePicture}"
                     alt="프로필 이미지"
                     style="width: 50px; height: 50px; object-fit: cover; border-radius: 50%; cursor: pointer;"
                     onclick="window.location.href='${pageContext.request.contextPath}/profile';">
            </c:when>
            <c:otherwise>
                <img id="profileImage"
                     src="${pageContext.request.contextPath}/resources/img/default_profile.png"
                     style="width: 50px; height: 50px; object-fit: cover; border-radius: 50%; cursor: pointer;"
                     onclick="window.location.href='${pageContext.request.contextPath}/profile';">
            </c:otherwise>
        </c:choose>
        
        <button id="signupButton" onclick="location.href='register'">회원가입</button>
        <button id="loginButton" onclick="location.href='login'">로그인</button>
        <button id="logoutButton" style="display: none;" onclick="logout()">로그아웃</button>
    </div>

    <!-- Profile Section -->
    <section class="profile">
        <div class="profile-image">
            <div class="profile-image-placeholder" id="profileImagePlaceholder">
                <c:choose>
                    <c:when test="${profile.profilePicture != null}">
                        <img src="${pageContext.request.contextPath}/profile-images/${profile.profilePicture}" alt="프로필 이미지" style="width: 100%; height: 100%; object-fit: cover;">
                    </c:when>
                    <c:otherwise>
                        <span>프로필 이미지를 클릭하여 업로드하세요</span>
                    </c:otherwise>
                </c:choose>
            </div>
            <input type="file" id="profile-upload" name="profileImage" style="display:none;">
        </div>
        <div class="nickname-wrapper">
            <label for="nickname">닉네임:</label>
            <input type="text" id="nickname" name="nickname" value="${profile.nickname}">
        </div>
        <div class="gender-wrapper">
            <label for="gender">성별:</label>
            <select id="gender" name="gender">
                <option value="M" ${profile.gender == 'M' ? 'selected' : ''}>남성</option>
                <option value="F" ${profile.gender == 'F' ? 'selected' : ''}>여성</option>
                <option value="O" ${profile.gender == 'O' ? 'selected' : ''}>기타</option>
            </select>
        </div>
        <div class="birthday-wrapper">
            <label for="birthday">생일:</label>
            <input type="date" id="birthday" name="birthday" value="<fmt:formatDate value='${profile.birthday}' pattern='yyyy-MM-dd'/>">
        </div>
        <div class="intro-wrapper">
            <label for="intro">한 줄 소개:</label>
            <input type="text" id="intro" name="intro" value="${profile.bio}" maxlength="20">
        </div>
        <button class="confirm-btn" id="profile-confirm-btn">프로필 저장</button>
    </section>

    <section class="stats">
        <div class="stat">추천 수<br>0개</div>
        <div class="stat">조회 수<br>0회</div>
    </section>

    <section class="content">
        <div class="stories">
            <button class="mystorylist" onclick="location.href='mystorylist'">내가 작성한 스토리</button>
            <div class="story-grid">
                <c:forEach var="story" items="${myStoryList}">
                    <div class="story-box">
                        <h3>${story.title}</h3>
                    </div>
                </c:forEach>
            </div>
            <button class="more-btn" onclick="location.href='mystorylist'">더보기</button>
        </div>
        <div class="recommendations">
            <button class="favoritestorylist" onclick="location.href='favoritestorylist'">내가 좋아요 누른 스토리</button>
            <div class="story-grid">
                <c:forEach var="favoriteStory" items="${favoriteStoryList}">
                    <div class="story-box">
                        <h3>${favoriteStory.title}</h3>
                    </div>
                </c:forEach>
            </div>
            <button class="more-btn" onclick="location.href='favoritestorylist'">더보기</button>
        </div>
    </section>

    <!-- Footer Icons -->
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
            <img src="${pageContext.request.contextPath}/resources/img/setting_icon.png" alt="설정" class="setting-icon" onclick="showSettingsModal()">
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

    <script src="${pageContext.request.contextPath}/resources/js/profile.js"></script>
</body>
</html>
