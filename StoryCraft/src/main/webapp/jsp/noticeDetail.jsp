<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>공지사항 상세 내용</title>
</head>
<body>
    <h2>${notice.title}</h2>
    <p>${notice.content}</p>
    
    <form action="<%=request.getContextPath()%>/notices/update" method="post">
        <input type="hidden" name="id" value="${notice.id}">
        <label for="title">제목</label>
        <input type="text" id="title" name="title" value="${notice.title}" required>
        
        <label for="content">내용</label>
        <textarea id="content" name="content" required>${notice.content}</textarea>

        <button type="submit">수정하기</button>
    </form>
</body>
</html>
