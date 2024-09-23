<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입</title>
    <link rel="stylesheet" href="/StoryCraft/resources/css/accession.css">
    <script src="/StoryCraft/resources/js/accession.js"></script> <!-- 분리된 JS 파일 불러오기 -->
     <script src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>
</head>
<body class="bg-image-1">
    <div class="container">
        <!-- 로고 이미지 - 오른쪽 위로 배치 -->
        <a href="main">
        <img src="/StoryCraft/resources/img/logo.png" alt="로고" class="logo">
        </a>

        <!-- 회원가입 버튼 - 왼쪽 위로 더 크게 배치 -->
        <div class="signup-buttons">
            <button onclick="location.href='register'">
                <img src="/StoryCraft/resources/img/logo.png" alt="일반 회원가입 아이콘"> 일반 회원가입
            </button>
            <button id="kakao-login-btn">
    			<img src="/StoryCraft/resources/img/KICON.png" alt="카카오 회원가입 아이콘"> 카카오 회원가입
			</button>
        </div>
    </div>
</body>
</html>
