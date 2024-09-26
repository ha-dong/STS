<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>완벽한 살인 스토리</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/mainStory.css">
</head>
<body>
<div class="story-container">
    <h1>완벽한 살인</h1>
    <div id="story-content" class="story-content">
        <p>강준호 형사는 베테랑으로, 완벽하게 계획된 살인사건 현장에서 단서를 추적해 나간다. 처음엔 완벽해 보였던 범죄 속에서 그는 숨겨진 진실과 반전을 발견하게 된다.</p>
        <button class="choice" onclick="startStory()">시작하기</button>
    </div>
</div>
<script src="${pageContext.request.contextPath}/resources/js/mainStory.js"></script>
</body>
</html>
