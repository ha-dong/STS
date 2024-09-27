<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>비밀번호 재설정</title>
    <link rel="stylesheet" href="/StoryCraft/resources/css/resetpw.css"> <!-- CSS 파일 경로 -->
</head>
<body>
    <!-- 로고를 왼쪽 위로 이동 -->
    <div class="logo-container">
        <a href="main">
            <img src="/StoryCraft/resources/img/logo.png" alt="로고" class="small-logo">
        </a>
    </div>
    
    <div class="container">
        <form id="reset-password-form" class="form-find">
            <h2>비밀번호 재설정</h2>
            <div class="form-floating">
                <label for="reset-password-token">인증번호</label>
                <input type="text" id="reset-password-token" class="form-control" placeholder="인증번호" required>
            </div>
            <div class="form-floating">
                <label for="reset-password-new-password">새 비밀번호</label>
                <input type="password" id="reset-password-new-password" class="form-control" placeholder="새 비밀번호" required>
            </div>
            <div class="form-floating">
                <label for="reset-password-confirm-password">새 비밀번호 확인</label>
                <input type="password" id="reset-password-confirm-password" class="form-control" placeholder="새 비밀번호 확인" required>
            </div>
            <button class="w-100 btn btn-lg btn-primary" type="submit">비밀번호 재설정</button>
        </form>
    </div>

    <script src="/StoryCraft/resources/js/resetpw.js"></script> <!-- 자바스크립트 파일 경로 -->
</body>
</html>
