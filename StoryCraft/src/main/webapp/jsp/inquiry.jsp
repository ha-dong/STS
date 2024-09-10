<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>문의사항</title>
    <script src="${pageContext.request.contextPath}/resources/js/inquiry.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/inquiry.css">
</head>
<body>
    <h2>문의사항</h2>
    
    <form id="inquiryForm">
    <label for="inquiryTitle">문의 제목:</label>
    <input type="text" id="inquiryTitle" name="inquiryTitle" required>

    <label for="inquiryType">문의 종류:</label>
    <select id="inquiryType" name="inquiryType" required>
        <option value="스토리 문의">스토리 문의</option>
        <option value="서비스 문의">서비스 문의</option>
        <option value="계정 관련 문의">계정 관련 문의</option>
        <option value="신고 문의">신고 문의</option>
    </select>

    <label for="inquiryText">문의 내용:</label>
    <textarea id="inquiryText" name="inquiryText" required></textarea>

    <label for="inquiryFile">첨부 파일:</label>
    <input type="file" id="inquiryFile" name="inquiryFile">

    <button type="submit">등록</button>
	</form>
	
	<!-- 문의 목록으로 이동하는 버튼 추가 -->
	<button onclick="window.location.href='/StoryCraft/jsp/inquiryList.jsp'">문의 목록</button>


</body>
</html>
