<!-- inquiryDetail.jsp -->

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>문의 상세 정보</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/inquiryDetail.css">
</head>
<body>
	<div class="logo-container">
		<a href="main"> <img src="/StoryCraft/resources/img/logo.png"
			alt="로고" class="small-logo logo-animation">
		</a>
	</div>

	<h2>문의 상세 정보</h2>
	<!-- 문의 정보 -->
	<div class="inquiry-info">
		<!-- 제목 -->
		<div class="inquiry-title">
			<strong>제목:</strong> ${inquiry.inqTitle}
		</div>

		<!-- 작성자, 작성일, 문의 종류, 상태를 가로로 정렬 -->
		<div class="inquiry-meta">
			<p>
				<strong>작성자:</strong> ${inquiry.userId}
			</p>
			<p>
				<strong>문의 종류:</strong> ${inquiry.inqTypecode}
			</p>
			<p>
				<strong>상태:</strong> ${statusName}
			</p>
			<p>
				<strong>작성일:</strong> ${inquiry.inqCrdate}
			</p>
				<!-- 첨부 파일이 있을 경우 표시 -->
	<c:if test="${not empty inquiry.inqFile}">
		<p>
			<a href="${pageContext.request.contextPath}/uploads/${inquiry.inqFile}">첨부 파일</a>
		</p>
	</c:if>
			
			
		</div>

		<!-- 문의 내용 -->
		<div class="inquiry-content">
			<p>${inquiry.inqText}</p>
		</div>
	</div>


	<!-- 댓글 섹션 -->
	<div class="comment-section">
		<a>댓글</a>
		<ul>
			<c:forEach var="comment" items="${comments}">
				<li>${comment.cmtDate}:${comment.cmtText}</li>
			</c:forEach>
		</ul>
		<c:if test="${empty comments}">
			<p>댓글이 없습니다.</p>
		</c:if>
	</div>



<!-- 버튼들을 동일한 높이에 배치하고 중앙에서 띄움 -->
<div class="buttons-container">
	<!-- 목록으로 버튼 -->
	<div class="list-button">
		<button
			onclick="location.href='${pageContext.request.contextPath}/inquiry'">목록으로</button>
	</div>
	<!-- 수정 및 삭제 버튼 -->
	<c:if test="${currentUserId == inquiry.userId}">
    <!-- 수정 및 삭제 버튼을 오른쪽 아래에 배치 -->
    <div class="inquiry-actions">
        <button onclick="location.href='${pageContext.request.contextPath}/editInquiry?inqNum=${inquiry.inqNum}'">수정</button>
        <button onclick="deleteInquiry(${inquiry.inqNum})">삭제</button>
    </div>
	</c:if>
</div>

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
