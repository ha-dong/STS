<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>문의 수정</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/editInquiry.css">
</head>
<body>
		<div class="sidebar-buttons">
			<a href="${pageContext.request.contextPath}/main" class="btn">메인페이지</a> 
			<a href="${pageContext.request.contextPath}/inquiry" class="btn">돌아가기</a>
		</div>
    <h2>문의 수정</h2>
    
    
    <!-- 문의 수정 폼 -->
    <form action="${pageContext.request.contextPath}/editInquiry" method="post" enctype="multipart/form-data">
        <input type="hidden" name="inqNum" value="${inquiry.inqNum}">
        
        <!-- 제목 입력 필드 -->
        <label for="inquiryTitle">제목:</label>
        <input type="text" id="inquiryTitle" name="inquiryTitle" value="${inquiry.inqTitle}" required>
        
        <!-- 문의 종류 선택 -->
        <label for="inquiryType">문의 종류:</label>
        <select id="inquiryType" name="inquiryType" required>
            <option value="서비스 문의" ${inquiry.inqTypecode == '서비스 문의' ? 'selected' : ''}>서비스 문의</option>
            <option value="스토리 문의" ${inquiry.inqTypecode == '스토리 문의' ? 'selected' : ''}>스토리 문의</option>
            <option value="계정 관련 문의" ${inquiry.inqTypecode == '계정 관련 문의' ? 'selected' : ''}>계정 관련 문의</option>
            <option value="신고 문의" ${inquiry.inqTypecode == '신고 문의' ? 'selected' : ''}>신고 문의</option>
            <option value="기타 문의" ${inquiry.inqTypecode == '기타 문의' ? 'selected' : ''}>기타 문의</option>
        </select>
        
        <!-- 문의 내용 입력 필드 -->
        <label for="inquiryText">내용:</label>
        <textarea id="inquiryText" name="inquiryText" required>${inquiry.inqText}</textarea>
        
        <!-- 파일 첨부 -->
        <label for="inquiryFile">첨부 파일:</label>
        <input type="file" id="inquiryFile" name="inquiryFile">
        
        <!-- 수정 버튼 -->
        <button type="submit">수정</button>
    </form>

    <script>
    document.addEventListener('DOMContentLoaded', function() {
        var inquiryId = 22; // 예시 ID
        var contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf('/', 1));

        fetch(contextPath + '/api/inquiry/' + inquiryId)
            .then(response => {
                if (!response.ok) {
                    throw new Error('문의 상세 정보를 불러오는 중 오류 발생');
                }
                return response.json();
            })
            .then(data => {
                if (data.success) {
                    var inquiry = data.inquiry;
                    document.getElementById('inquiryTitle').value = inquiry.inqTitle;
                    document.getElementById('inquiryType').value = inquiry.inqTypecode;
                    document.getElementById('inquiryText').value = inquiry.inqText;
                } else {
                    alert(data.message);
                }
            })
            .catch(error => {
                console.error('문의 상세 정보 로드 중 오류 발생:', error);
            });
    });

    </script>
</body>
</html>
