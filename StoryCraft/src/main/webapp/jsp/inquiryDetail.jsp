<!-- inquiryDetail.jsp -->

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>문의 상세 정보</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/inquiryDetail.css">
</head>
<body>
        <div class="logo-container">
        <a href="main">
            <img src="/StoryCraft/resources/img/logo.png" alt="로고" class="small-logo logo-animation">
        </a>
    </div>
    
    <h2>문의 상세 정보</h2>
    <!-- 문의 정보 -->
	<div class="inquiry-info">
	    <p><strong>제목:</strong> ${inquiry.inqTitle}</p>
	    <p><strong>작성자:</strong> ${inquiry.userId}</p>
	    <p><strong>작성일:</strong> ${inquiry.inqCrdate}</p>
	    <p><strong>문의 종류:</strong> ${inquiry.inqTypecode}</p>
	    <p><strong>상태:</strong> ${statusName}</p> <!-- 상태를 한국어로 출력 -->
	    <p><strong>문의 내용:</strong></p>
	    <p>${inquiry.inqText}</p>
	</div>


    <!-- 첨부 파일이 있을 경우 표시 -->
    <c:if test="${not empty inquiry.inqFile}">
        <p><strong>첨부 파일:</strong> 
            <a href="${pageContext.request.contextPath}/uploads/${inquiry.inqFile}">${inquiry.inqFile}</a>
        </p>
    </c:if>

    <!-- 수정 및 삭제 버튼 -->
<!-- 수정 및 삭제 버튼 -->
<c:if test="${currentUserId == inquiry.userId}">
    <div class="inquiry-actions">
        <button onclick="location.href='${pageContext.request.contextPath}/editInquiry?inqNum=${inquiry.inqNum}'">수정</button>
        <button onclick="deleteInquiry(${inquiry.inqNum})">삭제</button>
    </div>
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


fetch('/StoryCraft/api/inquiry/' + inquiryId)
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
        document.getElementById('inquiryText').value = inquiry.inqText;
    } else {
        alert(data.message);
    }
})
.catch(error => {
    console.error('문의 상세 정보 로드 중 오류 발생:', error);
});


function deleteInquiry(inqNum) {
    if (confirm('삭제하시겠습니까?')) {
        // COMMENTS 삭제 요청
        fetch('/StoryCraft/api/comments/deleteByInquiry/' + inqNum, {
            method: 'DELETE'
        })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                // COMMENTS 삭제 성공 후 INQUIRY 삭제 요청
                fetch('/StoryCraft/api/inquiry/delete/' + inqNum, {
                    method: 'DELETE'
                })
                .then(response => response.json())
                .then(data => {
                    if (data.success) {
                        alert('문의가 성공적으로 삭제되었습니다.');
                        location.href = '/StoryCraft/inquiry'; // 문의 목록 페이지로 이동
                    } else {
                        alert('문의 삭제 실패: ' + data.message);
                    }
                });
            } else {
                alert('댓글 삭제 실패: ' + data.message);
            }
        })
        .catch(error => {
            console.error('댓글 삭제 중 오류 발생:', error);
            alert('댓글 삭제 중 오류가 발생했습니다.');
        });
    }
}
</script>
</body>
</html>
