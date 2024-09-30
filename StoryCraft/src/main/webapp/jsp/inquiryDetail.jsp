<!-- inquiryDetail.jsp -->

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>문의 상세 정보</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/inquiry.css">
</head>
<body>
    <h2>문의 상세 정보</h2>
    
    <!-- 문의 정보 -->
    <p><strong>제목:</strong> ${inquiry.inqTitle}</p>
    <p><strong>작성자:</strong> ${inquiry.userId}</p>
    <p><strong>작성일:</strong> ${inquiry.inqCrdate}</p>
    <p><strong>문의 종류:</strong> ${inquiry.inqTypecode}</p>
    <p><strong>상태:</strong> ${statusName}</p> <!-- 상태를 한국어로 출력 -->
    <p><strong>문의 내용:</strong></p>
    <p>${inquiry.inqText}</p>

    <!-- 첨부 파일이 있을 경우 표시 -->
    <c:if test="${not empty inquiry.inqFile}">
        <p><strong>첨부 파일:</strong> 
            <a href="${pageContext.request.contextPath}/uploads/${inquiry.inqFile}">${inquiry.inqFile}</a>
        </p>
    </c:if>

    <!-- 수정 및 삭제 버튼 -->
    <c:if test="${currentUserId == inquiry.userId}">
        <button onclick="location.href='${pageContext.request.contextPath}/editInquiry?inqNum=${inquiry.inqNum}'">수정</button>
        <button onclick="deleteInquiry(${inquiry.inqNum})">삭제</button>
    </c:if>

    <!-- 댓글 섹션 -->
    <h3>댓글</h3>
    <c:if test="${not empty comments}">
        <ul>
            <c:forEach var="comment" items="${comments}">
                <li>${comment.cmtDate}: ${comment.cmtText}</li>
            </c:forEach>
        </ul>
    </c:if>
    <c:if test="${empty comments}">
        <p>댓글이 없습니다.</p>
    </c:if>

    <!-- 목록으로 버튼 -->
    <button onclick="location.href='${pageContext.request.contextPath}/inquiry'">목록으로</button>

    <script>
        var contextPath = '${pageContext.request.contextPath}';

        // 문의 삭제 기능
        function deleteInquiry(inqNum) {
            if (confirm("삭제하면 복구할 수 없습니다. 계속하시겠습니까?")) {
                fetch(contextPath + '/api/inquiry/' + inqNum, {
                    method: 'DELETE'
                })
                .then(response => response.json())
                .then(data => {
                    if (data.success) {
                        alert('삭제되었습니다.');
                        location.href = contextPath + '/inquiry';
                    } else {
                        alert('삭제 실패: ' + data.message);
                    }
                })
                .catch(error => {
                    console.error('삭제 중 오류 발생:', error);
                    alert('삭제 중 오류가 발생했습니다.');
                });
            }
        }
    </script>
</body>
</html>
