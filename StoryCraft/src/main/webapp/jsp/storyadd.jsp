<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>스토리 추가</title>
    <link rel="stylesheet" href="<c:url value='/resources/css/storyadd.css'/>">
</head>
<body>
    <div class="container">
        <header>
         	<div class="header-logo">
         	 	<a href="<c:url value='/main'/>">
            		<img src="/StoryCraft/resources/img/logo.png" alt="로고" class="small-logo">
       			</a>
                <h1>선택지 별 스토리</h1> <!-- 제목 -->
            </div>
            <div class="login-box">
            <c:choose>
	            <c:when test="${not empty sessionScope.user}">
	                <span>${sessionScope.user}님</span> <!-- 사용자 이름 표시 -->
	                <button class="login-button" onclick="location.href='/storycraft/logout'">로그아웃</button> <!-- 로그아웃 버튼 -->
	            </c:when>
        </c:choose>
        </header>

        <main>
            <form id="storyForm" method="POST" action="/saveStory" enctype="multipart/form-data">
				
			<!-- 이미지 업로드 -->
               <div class="image-upload" onclick="document.getElementById('file-input').click();">
                   <input type="file" id="file-input" name="coverImage" accept="image/*" style="display: none;">
                   <img id="preview" src="#" alt="이미지 미리보기" style="display: none;">
                   <span id="upload-text">클릭하여 이미지를 업로드하세요</span>
               </div>

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
                <footer>
                    <button class="reset-button" type="button" onclick="document.getElementById('storyForm').reset();">초기화</button>
                    <button class="save-button" type="button">저장</button>
                    <button class="submit-button" type="submit">제출하기</button>
                </footer>
          
        </main>
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
