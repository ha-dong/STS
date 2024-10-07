<!-- inquiryForm.jsp -->

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>문의 등록</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/inquiryForm.css">
</head>
<body>
    <div class="container">
        <div class="logo-container">
            <a href="main">
                <img src="/StoryCraft/resources/img/logo.png" alt="로고" class="small-logo logo-animation">
            </a>
        </div>
        <h2 class="form-title">문의 등록</h2>
        
        <form action="${pageContext.request.contextPath}/inquiry" method="post" enctype="multipart/form-data" class="inquiry-form">
            <div class="form-group">
                <label for="inquiryTitle">제목</label>
                <input type="text" id="inquiryTitle" name="inquiryTitle" required />
            </div>

            <div class="form-group">
                <label for="inquiryType">문의 종류</label>
                <select id="inquiryType" name="inquiryType" required>
                    <option value="서비스 문의">서비스 문의</option>
                    <option value="스토리 문의">스토리 문의</option>
                    <option value="계정 관련 문의">계정 관련 문의</option>
                    <option value="신고 문의">신고 문의</option>
                    <option value="기타 문의">기타 문의</option>
                </select>
            </div>

            <div class="form-group">
                <label for="inquiryText">내용</label>
                <textarea id="inquiryText" name="inquiryText" rows="10" required></textarea>
            </div>

            <div class="form-group">
                <label for="inquiryFile">첨부 파일</label>
                <input type="file" id="inquiryFile" name="inquiryFile" />
            </div>

            <div class="form-actions">
                <button type="submit" class="submit-btn">등록</button>
                <button type="button" class="cancel-btn" onclick="location.href='${pageContext.request.contextPath}/inquiry'">취소</button>
            </div>
        </form>
    </div>
</body>
</html>
