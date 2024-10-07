<!-- inquiry.jsp -->

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>문의 사항</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/inquiry.css">
    <script src="${pageContext.request.contextPath}/resources/js/inquiry.js"></script>
</head>
<body>
    <!-- 로고 컨테이너 -->
    <div class="logo-container">
        <a href="${pageContext.request.contextPath}/main">
            <img src="${pageContext.request.contextPath}/resources/img/logo.png" alt="로고" class="small-logo logo-animation">
        </a>
    </div>

    <!-- 문의 등록 버튼 -->
    <button class="inquiry-button" onclick="location.href='${pageContext.request.contextPath}/inquiryForm'">문의 등록</button>

    <!-- 문의 목록 제목 -->
    <h2 class="inquiry-title">문의 사항</h2>

    <!-- 문의 목록 표시 영역 -->
    <table id="inquiryListTable">
        <thead>
            <tr>
                <th>제목</th>
                <th>종류</th>
                <th>상태</th>
                <th>작성일</th>
                <th>작성자</th>
            </tr>
        </thead>
        <tbody id="inquiryListBody">
            <!-- 문의 목록이 JavaScript로 동적으로 추가됩니다 -->
        </tbody>
    </table>

</body>
</html>
