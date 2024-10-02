<!-- Profile.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>프로필 페이지</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/profile.css">
    <link href="https://cdn.jsdelivr.net/gh/spoqa/spoqa-han-sans/css/SpoqaHanSansNeo.css" rel="stylesheet" type="text/css" />
    <script>
        var contextPath = '${pageContext.request.contextPath}';
    </script>
    <!-- profile.js 스크립트는 body 끝 부분에서 포함합니다. -->
</head>
<body>
    <div class="header">
        <a href="${contextPath}/StoryCraft/main">
            <img src="${pageContext.request.contextPath}/resources/img/logo.png" alt="로고" class="logo" id="logo">
        </a>
        
        <div class="header-right">
           <!-- 프로필 이미지 -->
           <img id="profileImage"
                src="${contextPath}/resources/img/default_profile.png"
                style="width: 50px; height: 50px; object-fit: cover; border-radius: 50%; cursor: pointer; display: none;">
           
           <!-- 버튼들 -->
           <button id="logoutButton" style="display: none;">로그아웃</button>
           <button id="loginButton">로그인</button>
           <button id="signupButton">회원가입</button>
        </div>
    </div>

    <!-- 프로필 섹션 -->
    <section class="profile">
        <div class="profile-image">
            <div class="profile-image-placeholder" id="profileImagePlaceholder">
                <!-- 프로필 이미지 또는 업로드 안내 문구 -->
                <span>프로필 이미지를 클릭하여 업로드하세요</span>
            </div>
            <!-- id를 profileUpload로 변경 -->
            <input type="file" id="profileUpload" name="profileImage" style="display:none;">
        </div>
        <div class="profile-details">
            <p><strong>이메일:</strong> <span id="userEmail"></span></p>
            <p><strong>닉네임:</strong> <span id="userNickname"></span></p>
            <p><strong>성별:</strong> <span id="userGender"></span></p>
            <p><strong>생일:</strong> <span id="userBirthday"></span></p>
            <p><strong>한 줄 소개:</strong> <span id="userBio"></span></p>
        </div>
        <button class="edit-btn" onclick="openEditModal()">수정하기</button>
        
        <!-- 사용자 포스트 버튼 추가 -->
        <button id="userPostButton" class="user-post-btn" style="display: none;">내 포스트 보기</button>
    </section>

    <!-- 수정 모달 -->
    <div id="editModal" class="modal">
        <div class="modal-content">
            <span class="close" onclick="closeEditModal()">&times;</span>
            <h2>프로필 수정</h2>
            <form id="editProfileForm">
                <div class="form-group">
                    <label for="editEmail">이메일:</label>
                    <input type="email" id="editEmail" name="email" required>
                </div>
                <div class="form-group">
                    <label for="editNickname">닉네임:</label>
                    <input type="text" id="editNickname" name="nickname" required>
                </div>
                <div class="form-group">
                    <label for="editGender">성별:</label>
                    <select id="editGender" name="gender" required>
                        <option value="남성">남성</option>
                        <option value="여성">여성</option>
                        <option value="기타">기타</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="editBirthday">생일:</label>
                    <input type="date" id="editBirthday" name="birthday" required>
                </div>
                <div class="form-group">
                    <label for="editIntro">한 줄 소개:</label>
                    <input type="text" id="editIntro" name="intro" maxlength="20">
                </div>
                <button type="submit" class="save-btn">저장</button>
            </form>
        </div>
    </div>

    <!-- profile.js 스크립트 포함 -->
    <script src="${pageContext.request.contextPath}/resources/js/profile.js"></script>
</body>
</html>