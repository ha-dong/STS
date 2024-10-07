<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>스토리 플레이</title>
    <link rel="stylesheet" href="<c:url value='/resources/css/storyPlay.css'/>">
    <script src="<c:url value='/resources/js/storyPlay.js'/>"></script>
    <script>
        var contextPath = '<c:url value="/" />';
    </script>
</head>
<body>
    <div class="header">
        <a href="${contextPath}/StoryCraft/main">
            <img src="${pageContext.request.contextPath}/resources/img/logo.png" alt="로고" class="logo" id="logo">
        </a>
    </div>
    <div class="container mt-4">
        <h1>스토리 플레이</h1>

        <!-- 스토리 제목과 장면 이미지 -->
        <div id="storyDetails" class="story-item">
            <h2 id="storyTitle">${story.stTitle}</h2>
            <c:if test="${not empty scene.scIllus}">
                <img id="sceneImage" src="<c:url value='/uploads/${scene.scIllus}'/>" alt="장면 이미지" class="card-img-top">
            </c:if>
        </div>

        <!-- 장면 텍스트 -->
        <div id="sceneText" class="story-text">
            <p>${scene.scText}</p>
        </div>

        <!-- 선택지 목록 -->
        <div id="choices" class="choice-buttons">
            <c:forEach var="choice" items="${choices}">
			    <button class="choice-btn" data-choice-num="${choice.choiceNum}">${choice.choiceContent}</button>
			</c:forEach>
        </div>
    </div>
</body>
</html>
