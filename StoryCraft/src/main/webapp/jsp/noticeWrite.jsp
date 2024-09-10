<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>공지사항 작성</title>
</head>
<body>
    <h2>공지사항 작성</h2>
    <form action="<%=request.getContextPath()%>/notices/save" method="post">
        <label for="title">제목</label>
        <input type="text" id="title" name="title" required>
        
        <label for="content">내용</label>
        <textarea id="content" name="content" required></textarea>

        <button type="submit">저장하기</button>
    </form>
</body>
</html>
