<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>스토리 제작</title>
    <link rel="stylesheet" href="<c:url value='/resources/css/storyCreate.css'/>">
    <script src="<c:url value='/resources/js/storyCreate.js'/>"></script>
    <!-- 컨텍스트 경로를 JavaScript 변수로 설정 -->
    <script>
        var contextPath = '<c:url value="/" />';
    </script>
</head>
<body>
    <!-- 헤더 부분 시작 -->
    <div class="header">
        <img src="<c:url value='/resources/img/logo.png'/>" alt="로고" class="logo" id="logo">
        <!-- 로그인 상태와 관계없이 헤더 간소화 -->
    </div>
    <!-- 헤더 부분 끝 -->

    <div class="container">
        <h1>스토리 제작</h1>
        
        <div class="login-box">
                <c:choose>
                    <c:when test="${not empty sessionScope.user}">
                        <span>${sessionScope.user}님</span> <!-- 사용자 이름 표시 -->
                        <button class="login-button" onclick="location.href='<c:url value='/login'/>'">로그아웃</button> <!-- 로그아웃 버튼 -->
                    </c:when>
                    <c:otherwise>
                        <button class="login-button" onclick="location.href='<c:url value='/login'/>'">로그인</button>
                        <button class="login-button" onclick="location.href='<c:url value='/register'/>'">회원가입</button>
                    </c:otherwise>
                </c:choose>
            </div>
        
        <form id="storyForm" enctype="multipart/form-data">
            <!-- 스토리 기본 정보 -->
            <div class="story-info">
                <label for="title">제목:</label>
                <input type="text" id="title" name="title" required>

                <label for="cover">표지 이미지:</label>
                <input type="file" id="cover" name="cover" accept="image/*" onchange="previewImage(this, 'coverPreview')">
                <img id="coverPreview" src="#" alt="이미지 미리보기" style="display:none; width:200px; height:200px; object-fit:cover;">

                <label for="genre">장르:</label>
                <select id="genre" name="genre" required>
                    <option value="">선택하세요</option>
                    <option value="CG-01">판타지</option>
                    <option value="CG-02">스릴러</option>
                    <!-- 기타 장르 옵션 추가 -->
                </select>

                <label for="initialMoney">초기 돈 설정 (0~50):</label>
                <input type="number" id="initialMoney" name="initialMoney" min="0" max="50" value="0" required>

                <label for="initialHP">초기 체력 설정 (10~100):</label>
                <input type="number" id="initialHP" name="initialHP" min="10" max="100" value="100" required>

                <!-- 엔딩 코드 선택 -->
                <label for="endCode">엔딩 코드:</label>
                <select id="endCode" name="endCode">
                    <option value="">선택하세요</option>
                    <option value="CE-01">해피엔딩</option>
                    <option value="CE-02">새드엔딩</option>
                    <option value="CE-03">열린결말</option>
                </select>
            </div>

            <!-- 스토리 내용 및 선택지 -->
            <div class="scene-editor" id="sceneEditor">
                <!-- 루트 장면 -->
                <div class="scene" data-scene-num="1" data-parent-scene-num="0">
                    <h2>장면 1</h2>
                    <label for="sceneText_1">스토리 내용:</label>
                    <textarea id="sceneText_1" name="sceneText_1" required></textarea>

                    <label for="sceneImage_1">삽화 이미지:</label>
                    <input type="file" id="sceneImage_1" name="sceneImage_1" accept="image/*" onchange="previewImage(this, 'sceneImagePreview_1')">
                    <img id="sceneImagePreview_1" src="#" alt="이미지 미리보기" style="display:none; width:200px; height:200px; object-fit:cover;">

                    <!-- 선택지 추가 버튼 -->
                    <button type="button" class="add-choice-btn" onclick="addChoice(1)">선택지 추가</button>

                    <!-- 선택지 목록 -->
                    <div class="choices" id="choices_1">
                        <!-- 선택지가 추가되는 영역 -->
                    </div>
                </div>
            </div>

            <!-- 저장 및 제출 버튼 -->
            <div class="form-actions">
                <button type="button" onclick="saveScene()">저장</button>
                <button type="button" onclick="resetForm()">초기화</button>
                <button type="button" onclick="submitStory()">제출하기</button>
            </div>
        </form>
    </div>
</body>
</html>
