<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>스토리 커스텀</title>
    <link rel="stylesheet" href="<c:url value='/resources/css/story.css'/>">
</head>
<body>
	<div class="container">
	<header>
	    <div class="header-logo-text">
	       <a href="<c:url value='/main'/>">
            <img src="/StoryCraft/resources/img/logo.png" alt="로고" class="small-logo">
       		</a>
	        <h1 class="header-title">스토리 커스텀</h1> <!-- 로고 오른쪽으로 이동한 타이틀 -->
	    </div>
	    <div class="login-box">
	        <!-- 세션에서 사용자 정보를 가져와서 표시 -->
	        <c:choose>
	            <c:when test="${not empty sessionScope.user}">
	                <span>${sessionScope.user}님</span> <!-- 사용자 이름 표시 -->
	                <button class="login-button" onclick="location.href='/storycraft/logout'">로그아웃</button> <!-- 로그아웃 버튼 -->
	            </c:when>
	            <c:otherwise>
	                <span>로그인이 필요합니다.</span>
	                <button class="login-button" onclick="location.href='/storycraft/login'">로그인</button>
	            </c:otherwise>
	        </c:choose>
	    </div>
	</header>



        <main>
            <form id="storyForm" method="POST" action="/saveStory" enctype="multipart/form-data">
                <!-- 제목 입력 칸 -->
                <div class="title-input">
                    <label for="title">스토리 제목:</label>
                    <input type="text" id="title" name="title" placeholder="스토리 제목을 입력하세요" required>
                </div>

                <!-- 제작자 입력 칸 (세션에서 자동으로 사용자 정보 입력) -->
                <div class="creator-input">
                    <label for="creator">제작자 이름: </label>
                    <input type="text" id="creator" name="creator" value="${sessionScope.user}" readonly>
                </div>

                <!-- 이미지 업로드 -->
                <div class="image-upload" onclick="document.getElementById('file-input').click();">
                    <input type="file" id="file-input" name="coverImage" accept="image/*" style="display: none;">
                    <img id="preview" src="#" alt="이미지 미리보기" style="display: none;">
                    <span id="upload-text">클릭하여 이미지를 업로드하세요</span>
                </div>
                
                <!-- 장르 선택 -->
                <div class="genre-selection">
                    <label for="genre">장르 선택:</label>
                    <select name="genre" id="genre">
                        <option value="fantasy">판타지</option>
                        <option value="romance">로맨스</option>
                        <option value="thriller">스릴러</option>
                        <option value="horror">호러</option>
                        <option value="science-fiction">SF</option>
                    </select>
                </div>

                <!-- 스토리 내용 작성 -->
                <div class="story-content">
                    <textarea name="storyContent" placeholder="여기에 스토리를 작성하세요..." required></textarea>
                </div>

                <!-- 선택지 추가 -->
                <div class="additional-content">
                    <div class="choice">
                        <textarea name="choice1" placeholder="선택지 1"></textarea>
                    </div>
                    <div class="choice">
                        <textarea name="choice2" placeholder="선택지 2"></textarea>
                    </div>
                    <button id="add-choice" class="add-choice-button" type="button">+</button>
                </div>
            </form>
        </main>
        
        <footer>
            <!-- 기존의 submit 동작을 없애고, 자바스크립트에서 처리하도록 수정 -->
            <button class="reset-button" type="button" onclick="document.getElementById('storyForm').reset();">초기화</button>
            <button class="save-button" type="button" onclick="saveStory()">저장</button>
            <button class="submit-button" type="submit" form="storyForm">제출하기</button>
        </footer>

    </div>

    <script src="<c:url value='/resources/js/story.js'/>"></script>

    <!-- JavaScript로 스토리 저장 기능 추가 -->
    <script>
        function saveStory() {
            // 저장 로직을 추가 (Ajax 사용 가능)
            alert("스토리가 저장되었습니다.");
        }
    </script>
</body>
</html>
