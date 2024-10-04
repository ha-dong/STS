<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입</title>
    <link rel="stylesheet" href="/StoryCraft/resources/css/register.css">
</head>
<body>
    <!-- 로고를 오른쪽 위로 이동 -->
    <div class="logo-container">
        <a href="main">
            <img src="/StoryCraft/resources/img/logo.png" alt="로고" class="logo">
        </a>
    </div>
    
    <div class="container">
        <div class="form-container">
            <form id="register-form" class="form-signin">
                <h2>회원가입</h2>
                <div class="form-group">
                    <label for="register-userid">ID</label>
                    <div class="input-group">
                        <input type="text" id="register-userid" class="form-control" placeholder="ID" required>
                        <button type="button" class="btn-check-userid">중복 확인</button>
                    </div>
                </div>
                <div class="form-group">
                    <label for="register-password">비밀번호</label>
                    <input type="password" id="register-password" class="form-control" placeholder="****" required>
                </div>
                <div class="form-group">
                    <label for="register-confirm-password">비밀번호 확인</label>
                    <input type="password" id="register-confirm-password" class="form-control" placeholder="****" required>
                </div>
                <div class="form-group">
                    <label for="register-username">이름</label>
                    <input type="text" id="register-username" class="form-control" placeholder="본인 이름" required>
                </div>
                <div class="form-group">
                    <label for="register-nickname">닉네임</label>
                    <input type="text" id="register-nickname" class="form-control" placeholder="사용될 닉네임" required>
                </div>
                <div class="form-group">
                    <label for="register-birthday">생일</label>
                    <input type="date" id="register-birthday" class="form-control" required>
                </div>
                <div class="form-group">
                    <label for="register-gender">성별</label>
                    <select id="register-gender" class="form-control" required>
                        <option value="">선택</option>
                        <option value="남성">남성</option>
                        <option value="여성">여성</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="register-phonenumber">전화번호</label>
                    <input type="tel" id="register-phonenumber" class="form-control" placeholder="ex: 010-0000-0000" required>
                </div>
                <div class="form-group email-group">
                    <label for="register-email">이메일</label>
                    <div class="input-group email-input">
                        <input type="text" id="register-email-local" class="form-control" placeholder="이메일주소" required>
                        <span>@</span>
                        <input type="text" id="register-email-domain" class="form-control" placeholder="naver.com" required>
                    </div>
                </div>
                <div class="form-group">
                    <label for="register-address">주소</label>
                    <input type="text" id="register-address" class="form-control" placeholder="ADD" required>
                </div>
                <button class="w-100 btn btn-lg btn-primary" type="submit">회원가입</button>
            </form>
        </div>
    </div>
    <script src="/StoryCraft/resources/js/register.js"></script>
</body>
</html>
