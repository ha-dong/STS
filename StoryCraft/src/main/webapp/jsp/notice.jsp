<!-- notice.jsp -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>공지사항 관리</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/notice.css">
    <script>
        function confirmDelete(id) {
            if (confirm("정말로 이 공지사항을 삭제하시겠습니까?")) {
                // 폼을 제출하여 삭제 요청
                document.getElementById('delete-form-' + id).submit();
            }
        }
    </script>
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

    <!-- 메인 콘텐츠를 .main-content로 감싸기 시작 -->
    <div class="main-content">
        <h1>공지사항 관리</h1>
        <div class="button-group">
            <a href="${pageContext.request.contextPath}/notice?action=new">작성하기</a>
        </div>
        <!-- 공지사항 목록 보기 -->
        <c:if test="${empty action and empty notice}">
            <table>
                <tr>
                    <th>번호</th>
                    <th>타입</th>
                    <th>제목</th>
                    <th>작성일</th>
                    <th>작성자</th>
                    <th>관리</th> <!-- 삭제 버튼 추가 -->
                </tr>
                <c:forEach var="notice" items="${notices}">
                    <tr>
                        <td>${notice.ntNum}</td>
                        <td>
                            <c:choose>
                                <c:when test="${notice.ntTypeCode == 'CN-01'}">업데이트</c:when>
                                <c:when test="${notice.ntTypeCode == 'CN-02'}">일반</c:when>
                                <c:when test="${notice.ntTypeCode == 'CN-03'}">버그수정</c:when>
                                <c:otherwise>알 수 없음</c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/notice?action=view&id=${notice.ntNum}">
                                ${notice.ntTitle}
                            </a>
                        </td>
                        <td>${notice.ntCrdate}</td>
                        <td>${notice.uid}</td>
                        <td class="action-buttons">
                            <!-- 삭제 버튼 -->
                            <form id="delete-form-${notice.ntNum}" action="${pageContext.request.contextPath}/notice" method="post">
                                <input type="hidden" name="action" value="delete">
                                <input type="hidden" name="id" value="${notice.ntNum}">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> <!-- CSRF 토큰 추가 (Spring Security 사용 시) -->
                                <button type="button" onclick="confirmDelete(${notice.ntNum})">삭제</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>

        <!-- 공지사항 작성 폼 -->
        <c:if test="${action == 'new'}">
            <div class="form-container">
                <h2>공지사항 작성</h2>
                <form action="${pageContext.request.contextPath}/notice?action=save" method="post">
                    <label for="ntTypeCode">공지 타입 코드:</label>
                    <select id="ntTypeCode" name="ntTypeCode" required>
                        <option value="">선택하세요</option>
                        <option value="CN-01">업데이트</option>
                        <option value="CN-02">일반</option>
                        <option value="CN-03">버그수정</option>
                    </select><br><br>

                    <label for="ntTitle">제목:</label>
                    <input type="text" id="ntTitle" name="ntTitle" required><br><br>

                    <label for="ntText">내용:</label><br>
                    <textarea id="ntText" name="ntText" rows="10" cols="50" required></textarea><br><br>

                    <button type="submit">저장</button>
                    <a href="${pageContext.request.contextPath}/notice">취소</a>
                </form>
            </div>
        </c:if>

        <!-- 공지사항 상세 보기 및 수정 폼 -->
        <c:if test="${action == 'view' and not empty notice}">
            <div class="form-container">
                <h2>공지사항 상세 내용</h2>
                <p><strong>제목:</strong> ${notice.ntTitle}</p>
                <p><strong>내용:</strong> ${notice.ntText}</p>
                <p><strong>작성일:</strong> ${notice.ntCrdate}</p>
                <p>
                    <strong>작성자:</strong> ${notice.uid}
                </p>

                <h3>공지사항 수정</h3>
                <form action="${pageContext.request.contextPath}/notice?action=update" method="post">
                    <input type="hidden" name="ntNum" value="${notice.ntNum}">

                    <label for="ntTypeCode">공지 타입 코드:</label>
                    <select id="ntTypeCode" name="ntTypeCode" required>
                        <option value="">선택하세요</option>
                        <option value="CN-01" <c:if test="${notice.ntTypeCode == 'CN-01'}">selected</c:if>>업데이트</option>
                        <option value="CN-02" <c:if test="${notice.ntTypeCode == 'CN-02'}">selected</c:if>>일반</option>
                        <option value="CN-03" <c:if test="${notice.ntTypeCode == 'CN-03'}">selected</c:if>>버그수정</option>
                    </select><br><br>

                    <label for="ntTitle">제목:</label>
                    <input type="text" id="ntTitle" name="ntTitle" value="${notice.ntTitle}" required><br><br>

                    <label for="ntText">내용:</label><br>
                    <textarea id="ntText" name="ntText" rows="10" cols="50" required>${notice.ntText}</textarea><br><br>

                    <button type="submit">수정하기</button>
                    <a href="${pageContext.request.contextPath}/notice">취소</a>
                </form>
            </div>
        </c:if>
    </div>
    <!-- .main-content로 감싸기 끝 -->

</body>
</html>
