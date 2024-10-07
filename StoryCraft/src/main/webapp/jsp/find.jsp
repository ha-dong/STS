<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>아이디/비밀번호 찾기</title>
    <link rel="stylesheet" href="/StoryCraft/resources/css/find.css">
</head>
<body>
    <!-- 로고를 오른쪽 위로 이동 -->
   <div class="logo-container">
      <a href="main"> <img src="/StoryCraft/resources/img/logo.png"
         alt="로고" class="logo">
      </a>
   </div>

    <div class="container">
        <div class="form-container">
            <form id="find-id-form" class="form-find">
                <h2>아이디 찾기</h2>
                <div class="form-floating">
                    <input type="text" id="find-id-username" class="form-control" placeholder="이름" required>
                </div>
                <div class="form-floating">
                    <input type="email" id="find-id-email" class="form-control" placeholder="이메일" required>
                </div>
                <button class="w-100 btn btn-lg btn-primary" type="submit">아이디 찾기</button>
            </form>

            <form id="find-password-form" class="form-find">
                <h2>비밀번호 찾기</h2>
                <div class="form-floating">
                    <input type="text" id="find-password-userid" class="form-control" placeholder="아이디" required>
                </div>
                <div class="form-floating">
                    <input type="email" id="find-password-email" class="form-control" placeholder="이메일" required>
                </div>
                <button class="w-100 btn btn-lg btn-primary" type="submit">비밀번호 찾기</button>
            </form>
        </div>
    </div>
    <script src="<c:url value='/resources/js/find.js'/>"></script>
</body>
</html>
