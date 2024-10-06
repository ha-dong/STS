<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>MAINSTORY</title>
    
    <!-- Google Fonts 링크 추가 -->
    <link href="https://fonts.googleapis.com/css2?family=Noto+Serif+KR:wght@400;700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Cinzel:wght@700&display=swap" rel="stylesheet">
    
    <!-- Font Awesome CDN 추가 -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    
    <!-- 외부 CSS 파일 링크 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/mainStory.css">
    
    <!-- contextPath 변수를 정의하는 스크립트 추가 -->
    <script type="text/javascript">
        var contextPath = '${pageContext.request.contextPath}';
    </script>
    
</head>
<body>

	<!-- 오디오 파일 추가 -->
	<audio id="clickSound" src="${pageContext.request.contextPath}/resources/audio/Storyclick.mp3"></audio>
	
	<!-- 언어 선택 드롭다운 -->
	<div class="language-selector">
	    <label for="language-select">언어 선택:</label>
	    <select id="language-select" onchange="changeLanguage(this.value)">
	        <option value="ko" selected>한국어</option>
	        <option value="en">English</option>
	        <!-- 추가 언어는 여기서 선택 -->
	    </select>
	</div>
	
	<!-- TTS 버튼 추가 -->
	<button class="tts-button" onclick="toggleTTS()">
	    <i class="fas fa-volume-up"></i> 음성 읽기
	</button>
	
    <!-- 제목 및 시작 버튼 컨테이너 -->
    <div class="title-container" id="title-container">
        <h1 id="story-title"></h1>
        <p class="story-text"></p>
        <button class="choice" onclick="startStory()">
            <i class="fas fa-play"></i> 시작하기
        </button>
    </div>

    <!-- 스토리 컨테이너: 스토리 진행 시 표시 -->
    <div class="story-container hidden" id="story-container">
        <div id="story-content" class="story-content">
            <!-- 스토리 내용이 여기에 동적으로 추가됩니다 -->
        </div>
    </div>
    
    <script src="${pageContext.request.contextPath}/resources/js/mainStory.js"></script>
</body>
</html>
