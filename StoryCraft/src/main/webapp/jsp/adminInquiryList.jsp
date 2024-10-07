<!-- adminInquiryList.jsp -->

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>문의 관리</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/resources/css/adminInquiryList.css">
</head>
<body>
    <div class="sidebar">
        <div class="logo">
            <img
                src="${pageContext.request.contextPath}/resources/img/Story_Craft_white-remove.png"
                alt="Logo">
        </div>
        <nav>
            <ul>
                <li><a href="${pageContext.request.contextPath}/admin/stories">스토리 관리</a></li>
                <li><a href="${pageContext.request.contextPath}/adminUser">유저 관리</a></li>
                <li><a href="${pageContext.request.contextPath}/notice">공지
                        관리</a></li>
                <li><a
                    href="${pageContext.request.contextPath}/adminInquiryList">문의
                        관리</a></li>
            </ul>
        </nav>

        <div class="sidebar-buttons">
            <a href="${pageContext.request.contextPath}/main" class="btn">메인
                페이지</a> <a href="${pageContext.request.contextPath}/manager" class="btn">관리자
                페이지</a>
        </div>
    </div>

<div class="main-content">
    <header>
        <div class="title">문의 관리</div>
    </header>

    <!-- 필터링 기능 추가 -->
    <div class="filter-section">
        <form action="${pageContext.request.contextPath}/adminInquiryList" method="get" class="filter-form">
            <label for="inquiryType">문의 종류:</label>
            <select name="inquiryType" id="inquiryType">
                <option value="">전체</option>
                <option value="서비스 문의" ${param.inquiryType == '서비스 문의' ? 'selected' : ''}>서비스 문의</option>
                <option value="스토리 문의" ${param.inquiryType == '스토리 문의' ? 'selected' : ''}>스토리 문의</option>
                <option value="계정 관련 문의" ${param.inquiryType == '계정 관련 문의' ? 'selected' : ''}>계정 관련 문의</option>
                <option value="신고 문의" ${param.inquiryType == '신고 문의' ? 'selected' : ''}>신고 문의</option>
                <option value="기타 문의" ${param.inquiryType == '기타 문의' ? 'selected' : ''}>기타 문의</option>
            </select>

            <label for="inquiryStatus">상태:</label>
            <select name="inquiryStatus" id="inquiryStatus">
                <option value="">전체</option>
                <option value="CI-05" ${param.inquiryStatus == 'CI-05' ? 'selected' : ''}>접수 완료</option>
                <option value="CI-06" ${param.inquiryStatus == 'CI-06' ? 'selected' : ''}>처리중</option>
                <option value="CI-07" ${param.inquiryStatus == 'CI-07' ? 'selected' : ''}>처리 완료</option>
            </select>

            <button type="submit">필터 적용</button>
        </form>
    </div>

    <table border="1">
        <thead>
        <tr>
            <th>문의 번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>문의 종류</th>
            <th>상태</th>
            <th>변경</th>
            <th>삭제</th>
            <th>댓글 추가</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="inquiry" items="${inquiryList}">
            <tr>
                <td>${inquiry.inqNum}</td>
                <td><a href="${pageContext.request.contextPath}/inquiryDetail?inqNum=${inquiry.inqNum}">${inquiry.inqTitle}</a></td>
                <td>${inquiry.userId}</td>
                <td>${inquiry.inqTypecode}</td>
                <td>${inquiry.inqGenrecode}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/admin/updateInquiryStatus" method="post" class="status-form">
                        <input type="hidden" name="inqNum" value="${inquiry.inqNum}"/>
                        <div class="status-wrapper">
                            <select name="newStatus">
                                <option value="CI-05" ${inquiry.inqGenrecode == 'CI-05' ? 'selected' : ''}>접수 완료</option>
                                <option value="CI-06" ${inquiry.inqGenrecode == 'CI-06' ? 'selected' : ''}>처리중</option>
                                <option value="CI-07" ${inquiry.inqGenrecode == 'CI-07' ? 'selected' : ''}>처리 완료</option>
                            </select>
                            <button type="submit">변경</button>
                        </div>
                    </form>
                </td>
                <td><button onclick="deleteInquiry(${inquiry.inqNum})">삭제</button></td>
                <td>
                    <form action="${pageContext.request.contextPath}/admin/addComment" method="post" class="comment-form">
                        <input type="hidden" name="inqNum" value="${inquiry.inqNum}"/>
                        <div class="comment-wrapper">
                            <textarea name="commentText"></textarea>
                            <button type="submit">댓글 추가</button>
                        </div>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script>
    function deleteInquiry(inqNum) {
        if (confirm('삭제하시겠습니까?')) {
            fetch('/StoryCraft/api/comments/deleteByInquiry/' + inqNum, {
                method: 'DELETE'
            })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    fetch('/StoryCraft/api/inquiry/delete/' + inqNum, {
                        method: 'DELETE'
                    })
                    .then(response => response.json())
                    .then(data => {
                        if (data.success) {
                            alert('문의가 성공적으로 삭제되었습니다.');
                            location.href = '/StoryCraft/adminInquiryList';
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
