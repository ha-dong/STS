<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>공지사항</title>
    <link rel="stylesheet" href="/StoryCraft/resources/css/notice.css">
</head>
<body>
    <h2>공지사항</h2>
    <div class="container">
        <table class="notice-table">
            <thead>
                <tr>
                    <th>번호</th>
                    <th>제목</th>
                    <th>작성일</th>
                </tr>
            </thead>
            <tbody id="noticeList">
                <c:forEach var="notice" items="${notices}" varStatus="status">
                    <tr>
                        <td>${status.index + 1}</td>
                        <td><a href="/notices/${notice.id}">${notice.title}</a></td>
                        <td><fmt:formatDate value="${notice.createDate}" pattern="yyyy-MM-dd" /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div class="button-container">
            <a href="<%=request.getContextPath()%>/notices/new" class="write-button">작성하기</a>
        </div>
    </div>
</body>
</html>