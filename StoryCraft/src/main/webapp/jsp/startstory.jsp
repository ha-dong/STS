<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>스토리 플레이</title>
    <link rel="stylesheet" href="<c:url value='/resources/css/startstory.css'/>">
</head>
<body>
    <div class="container">
        <header>
            <h1>${story.stTitle}</h1> <!-- 스토리 제목 -->
            <p>제작자: ${story.uId}</p> <!-- 제작자 정보 -->
            <p>장르: ${story.stGenrecode}</p> <!-- 장르 정보 -->
        </header>

        <main>
            <h2>씬 목록</h2>
            <ul>
                <c:forEach var="scene" items="${scenes}">
                    <li>
                        <a href="<c:url value='/startStory/scene/${scene.scNum}'/>">
                            씬 제목: ${scene.scText} <br>
                            선택지: ${scene.scChoices}
                        </a>
                    </li>
                </c:forEach>
            </ul>
        </main>

        <footer>
            <button onclick="location.href='<c:url value='/storylist'/>';">스토리 목록으로 돌아가기</button>
        </footer>
    </div>
    <script src="<c:url value='/resources/js/startstory.js'/>"></script>
</body>
</html>
