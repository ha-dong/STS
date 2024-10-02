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
    <!-- 오디오 태그 추가, 자동 재생 -->
    <audio id="bgm" src="${pageContext.request.contextPath}/resources/audio/BGM.mp3" autoplay loop></audio>
    <!-- 터치음 오디오 태그 추가 -->
    <audio id="waterdrop-sound" src="${pageContext.request.contextPath}/resources/audio/waterdrops.mp3"></audio>

    <!-- 기존 코드 유지 -->
    <div class="container" onclick="handleClick()">
        <img src="${pageContext.request.contextPath}/resources/img/mainbackground.png" alt="ICS" class="start-image">
        <div class="click-text">화면을 클릭해주세요</div>
    </div>

    <script>
        function handleClick() {
            // 여러 번 클릭 방지
            const container = document.querySelector('.container');
            container.style.pointerEvents = 'none';

            // 물방울 소리 재생
            const waterdropSound = document.getElementById('waterdrop-sound');
            waterdropSound.play();

            // 페이지 이동 지연
            setTimeout(() => {
                window.location.href = "${pageContext.request.contextPath}/main";
            }, 1500); // 1.5초 지연
        }
    </script>
</body>
</html>
