<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <!-- 기존 코드 유지 -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ICS - 시작 화면</title>
    <!-- 기존 폰트 링크 -->
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;500&display=swap" rel="stylesheet">
    <!-- 새로운 폰트 링크 추가 -->
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Myeongjo&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/index.css">
</head>
<body>
    <!-- 기존 코드 유지 -->
    <div class="container">
        <img src="${pageContext.request.contextPath}/resources/img/mainbackground.png" alt="ICS" class="start-image" onclick="redirectToMain()">
        <div class="click-text">화면을 클릭해주세요</div>
    </div>

    <script>
        function redirectToMain() {
            window.location.href = "${pageContext.request.contextPath}/main";
        }
    </script>
</body>
</html>
