<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ICS - 로그인</title>
    <link rel="stylesheet" href="/StoryCraft/resources/css/login.css">
</head>
<body>
    <!-- 로고를 오른쪽 위로 이동 -->
    <div class="logo-container">
        <a href="main">
            <img src="/StoryCraft/resources/img/logo.png" alt="로고" class="small-logo">
        </a>
    </div>
    
    <div class="container">
        <div class="form-container">
            <form id="login-form" class="form-signin">
                <h2>로그인</h2>
                <div class="form-floating">
                    <label for="login-userid">아이디</label>
                    <input type="text" id="login-userid" name="userid" class="form-control" placeholder="아이디" required>
                </div>
                <div class="form-floating">
                    <label for="login-password">비밀번호</label>
                    <input type="password" id="login-password" name="password" class="form-control" placeholder="비밀번호" required>
                </div>
                <button class="w-100 btn btn-lg btn-primary" type="submit">로그인</button>
                <div class="options">
                    <a href="accession">회원가입 &nbsp; &nbsp; &nbsp; &nbsp;</a>
                    <a href="find" class="find-id-password">아이디/비밀번호 찾기</a>
                </div>
            </form>
        </div>
    </div>

    <!-- 계정 복구 모달 -->
    <div id="reactivate-modal" class="modal">
        <div class="modal-content">
            <p>계정을 복구 하시겠습니까?</p>
            <button id="confirm-reactivate" class="modal-button">네</button>
            <button id="cancel-reactivate" class="modal-button">아니요</button>
        </div>
    </div>

    <script src="/StoryCraft/resources/js/login.js"></script>
</body>
</html>
