<!-- inquiryForm.jsp -->

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>문의 등록</title>
</head>
<body>
    <h2>문의 등록</h2>
    <form action="${pageContext.request.contextPath}/inquiry" method="post" enctype="multipart/form-data">
        <p>
            <label for="inquiryTitle">제목:</label>
            <input type="text" id="inquiryTitle" name="inquiryTitle" required />
        </p>
        <p>
            <label for="inquiryType">문의 종류:</label>
            <select id="inquiryType" name="inquiryType" required>
                <option value="질문">질문</option>
                <option value="버그">버그</option>
                <option value="건의">건의</option>
                <!-- 필요한 문의 종류 추가 -->
            </select>
        </p>
        <p>
            <label for="inquiryText">내용:</label><br />
            <textarea id="inquiryText" name="inquiryText" rows="10" cols="50" required></textarea>
        </p>
        <p>
            <label for="inquiryFile">첨부 파일:</label>
            <input type="file" id="inquiryFile" name="inquiryFile" />
        </p>
        <p>
            <button type="submit">등록</button>
            <button type="button" onclick="location.href='${pageContext.request.contextPath}/inquiry'">취소</button>
        </p>
    </form>
</body>
</html>
