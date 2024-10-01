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
    <!-- 제목 및 시작 버튼 컨테이너 -->
    <div class="title-container" id="title-container">
        <h1 id="story-title">완벽한 범죄</h1>
        <p class="story-text">강준호 형사는 베테랑으로, 완벽하게 계획된 살인사건 현장에서 단서를 추적해 나간다. 처음엔 완벽해 보였던 범죄 속에서 그는 숨겨진 진실과 반전을 발견하게 된다.</p>
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
