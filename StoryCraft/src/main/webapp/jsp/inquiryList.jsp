<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>문의 목록</title>
    
    <script src="${pageContext.request.contextPath}/resources/js/inquiryList.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/inquiryList.css">
</head>
<body>
    <h2>문의 목록</h2>
    <ul id="inquiryList"></ul>
    
    <!-- 삭제 확인 모달 -->
    <div id="deleteModal" style="display:none;">
        <h3>삭제 확인</h3>
        <p>삭제하면 복구할 수 없습니다. 계속하시겠습니까?</p>
        <button onclick="confirmDelete()">네</button>
        <button onclick="closeModal()">취소</button>
    </div>

    <!-- 문의 상세 모달 -->
    <div id="detailModal" style="display:none;">
        <h3 id="detailTitle"></h3>
        <p id="detailText"></p>
        <p><strong>종류: </strong><span id="detailType"></span></p>
        <p><strong>상태: </strong><span id="detailStatus"></span></p>
        <img id="detailImage" src="" alt="첨부 이미지" style="max-width:100%;">
        <button onclick="closeDetailModal()">닫기</button>
    </div>
</body>
</html>
