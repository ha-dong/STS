<!-- adminUserManagement.jsp -->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> <!-- 날짜 포맷팅을 위한 fmt 태그 추가 -->

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>관리자 유저 관리</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/adminUserManagement.css">
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
            <div class="title">유저 관리</div>
        </header>
        
        <div class="dashboard">
            <!-- 사용자 관리 섹션 -->
            <div class="user-management">
                <table id="user-table">
                    <thead>
                        <tr>
                            <th>아이디</th>
                            <th>이름</th>
                            <th>이메일</th>
                            <th>활성화 상태</th>
                            <th>비활성화 이유</th>
                            <th>비활성화 날짜</th>
                            <th>동작</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="user" items="${users}">
                            <tr>
                                <td>${user.uId}</td>
                                <td>${user.uName}</td>
                                <td>${user.uEmail}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${user.uActivate == 'Y'}">
                                            활성화
                                        </c:when>
                                        <c:otherwise>
                                            비활성화
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${not empty user.uDreason}">
                                            ${user.uDreason}
                                        </c:when>
                                        <c:otherwise>
                                            -
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${not empty user.uDdate}">
                                            <fmt:formatDate value="${user.uDdate}" pattern="yyyy-MM-dd HH:mm:ss" />
                                        </c:when>
                                        <c:otherwise>
                                            -
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${user.uActivate == 'Y'}">
                                            <button class="deactivate-btn" data-userid="${user.uId}">비활성화</button>
                                        </c:when>
                                        <c:otherwise>
                                            <button class="activate-btn" data-userid="${user.uId}">활성화</button>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    
    <!-- 사용자 관리 스크립트 추가 -->
    <script>
        document.addEventListener('DOMContentLoaded', function () {
            // 비활성화 버튼 이벤트 리스너 추가
            document.querySelectorAll('.deactivate-btn').forEach(button => {
                button.addEventListener('click', function () {
                    const userId = this.getAttribute('data-userid');
                    const reason = prompt('비활성화 이유를 입력해주세요:');
                    if (reason) {
                        deactivateUser(userId, reason);
                    }
                });
            });

            // 활성화 버튼 이벤트 리스너 추가
            document.querySelectorAll('.activate-btn').forEach(button => {
                button.addEventListener('click', function () {
                    const userId = this.getAttribute('data-userid');
                    if (confirm('사용자를 활성화하시겠습니까?')) {
                        activateUser(userId);
                    }
                });
            });

            // 사용자 비활성화 함수
            function deactivateUser(userId, reason) {
                fetch('${pageContext.request.contextPath}/adminUser/api/users/deactivate', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({ userId, reason })
                })
                .then(response => response.json())
                .then(data => {
                    if (data.success) {
                        alert(data.message);
                        location.reload(); // 페이지 새로고침하여 목록 갱신
                    } else {
                        alert(data.message);
                    }
                })
                .catch(error => {
                    console.error('사용자 비활성화 요청 중 오류:', error);
                    alert('사용자 비활성화 요청 중 문제가 발생했습니다.');
                });
            }

            // 사용자 활성화 함수
            function activateUser(userId) {
                fetch('${pageContext.request.contextPath}/adminUser/api/users/activate', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({ userId })
                })
                .then(response => response.json())
                .then(data => {
                    if (data.success) {
                        alert(data.message);
                        location.reload(); // 페이지 새로고침하여 목록 갱신
                    } else {
                        alert(data.message);
                    }
                })
                .catch(error => {
                    console.error('사용자 활성화 요청 중 오류:', error);
                    alert('사용자 활성화 요청 중 문제가 발생했습니다.');
                });
            }
        });
    </script>
</body>
</html>
