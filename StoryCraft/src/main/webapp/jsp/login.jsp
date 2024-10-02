<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>ICS - 로그인</title>
<link rel="stylesheet" href="/StoryCraft/resources/css/login.css">
<script src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>
</head>
<body>
   <!-- 로고를 오른쪽 위로 이동 -->
   <div class="logo-container">
      <a href="main"> <img src="/StoryCraft/resources/img/logo.png"
         alt="로고" class="small-logo logo-animation">
      </a>
   </div>

   <div class="container">
      <div class="form-container">
         <form id="login-form" class="form-signin">
            <h2>로그인</h2>
            <div class="form-floating">
               <input type="text"
                  id="login-userid" name="userid" class="form-control"
                  placeholder="아이디" required>
            </div>
            <div class="form-floating">
                <input type="password"
                  id="login-password" name="password" class="form-control"
                  placeholder="비밀번호" required>
            </div>
            <button class="btn btn-lg btn-primary" type="submit">로그인</button>
            <button type="button" id="kakao-login-btn">
               <img src="/StoryCraft/resources/img/KICON.png" alt="카카오 로그인 아이콘">
               카카오 로그인
            </button>
            <div class="options">
               <a href="accession">회원가입</a> <a href="find"
                  class="find-id-password">아이디/비밀번호 찾기</a>
            </div>
         </form>
      </div>
   </div>

   <!-- 계정 복구 모달 (사용자가 스스로 비활성화 시) -->
   <div id="reactivate-modal" class="modal">
      <div class="modal-content">
         <p>계정을 복구 하시겠습니까?</p>
         <button id="confirm-reactivate" class="modal-button">네</button>
         <button id="cancel-reactivate" class="modal-button">아니요</button>
      </div>
   </div>

   <!-- 계정 이용 제한 모달 (관리자가 비활성화 시) -->
   <div id="account-restricted-modal" class="modal">
      <div class="modal-content">
         <h2>계정 이용 제한</h2>
         <p>이용중인 계정에서는 운영 정책 위반 내역이 확인되어 계정이 정지 되었습니다.</p>
         <p>
            <strong>제재 사유:</strong> <span id="dreason"></span>
         </p>
         <p>
            <strong>문의하기:</strong> 010-0000-0000
         </p>
         <button id="close-modal" class="modal-button">확인</button>
      </div>
   </div>

   <script src="/StoryCraft/resources/js/login.js"></script>
</body>
</html>
