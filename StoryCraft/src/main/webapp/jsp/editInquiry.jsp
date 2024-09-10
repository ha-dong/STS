<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>문의 수정</title>
    <script src="${pageContext.request.contextPath}/resources/js/editInquiry.js"></script>
</head>
<body>
    <h2>문의 수정</h2>
    
    <form id="editInquiryForm">
        <label for="inquiryTitle">문의 제목:</label>
        <input type="text" id="inquiryTitle" name="inquiryTitle" required>

        <label for="inquiryType">문의 종류:</label>
        <select id="inquiryType" name="inquiryType" required>
            <option value="스토리 문의">스토리 문의</option>
            <option value="서비스 문의">서비스 문의</option>
            <option value="계정 관련 문의">계정 관련 문의</option>
            <option value="신고 문의">신고 문의</option>
        </select>

        <label for="inquiryText">문의 내용:</label>
        <textarea id="inquiryText" name="inquiryText" required></textarea>

        <button type="button" onclick="submitEdit()">수정 완료</button>
    </form>

    <script>
        document.addEventListener('DOMContentLoaded', function () {
            const params = new URLSearchParams(window.location.search);
            const inqNum = params.get('inqNum');
            
            fetch(`/StoryCraft/api/inquiry/${inqNum}`)
            .then(response => response.json())
            .then(data => {
                document.getElementById('inquiryTitle').value = data.inqTitle;
                document.getElementById('inquiryText').value = data.inqText;
                document.getElementById('inquiryType').value = data.inqTypecode;
            })
            .catch(error => console.error('문의 상세 정보 로드 중 오류 발생:', error));
        });

        function submitEdit() {
            const params = new URLSearchParams(window.location.search);
            const inqNum = params.get('inqNum');
            const updatedTitle = document.getElementById('inquiryTitle').value;
            const updatedText = document.getElementById('inquiryText').value;
            const updatedType = document.getElementById('inquiryType').value;

            fetch(`/StoryCraft/api/inquiry/${inqNum}`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    inqTitle: updatedTitle,
                    inqText: updatedText,
                    inqTypecode: updatedType
                })
            })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    alert('문의가 수정되었습니다.');
                    window.location.href = '/StoryCraft/jsp/inquiryList.jsp'; // 수정 후 문의 목록 페이지로 리디렉션
                } else {
                    alert('수정 실패');
                }
            })
            .catch(error => console.error('수정 중 오류 발생:', error));
        }
    </script>
</body>
</html>
