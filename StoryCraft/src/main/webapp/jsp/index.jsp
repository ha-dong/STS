<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ICS - 시작 화면</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/index.css">
</head>
<body>
    <div class="container">
        <img src="${pageContext.request.contextPath}/resources/img/ics_start_4.png" alt="ICS" class="start-image" onclick="redirectToMain()">
        <div class="click-text">화면을 클릭해주세요</div>
    </div>

    <script>
        function redirectToMain() {
            window.location.href = "${pageContext.request.contextPath}/main";
        }
    </script>
</body>
</html>


