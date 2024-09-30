<!-- adminInquiryList.jsp -->

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>문의 관리</title>
</head>
<body>
    <h2>문의 관리</h2>
    <table border="1">
        <thead>
            <tr>
                <th>문의 번호</th>
                <th>제목</th>
                <th>작성자</th>
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
                    <td>${inquiry.inqTitle}</td>
                    <td>${inquiry.userId}</td>
                    <td>${inquiry.inqGenrecode}</td>
                    <td>
                        <form action="${pageContext.request.contextPath}/admin/updateInquiryStatus" method="post">
                            <input type="hidden" name="inqNum" value="${inquiry.inqNum}" />
                            <select name="newStatus">
                                <option value="CI-05">접수 완료</option>
                                <option value="CI-06">처리중</option>
                                <option value="CI-07">처리 완료</option>
                            </select>
                            <button type="submit">변경</button>
                        </form>
                    </td>
                    <td>
                        <button onclick="deleteInquiry(${inquiry.inqNum})">삭제</button>
                    </td>
                    <td>
                        <form action="${pageContext.request.contextPath}/admin/addComment" method="post">
                            <input type="hidden" name="inqNum" value="${inquiry.inqNum}" />
                            <textarea name="commentText"></textarea>
                            <button type="submit">댓글 추가</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <script>
        function deleteInquiry(inqNum) {
            if (confirm('삭제하시겠습니까?')) {
                fetch('${pageContext.request.contextPath}/admin/deleteInquiry/' + inqNum, {
                    method: 'DELETE'
                }).then(response => response.json())
                  .then(data => {
                      if (data.success) {
                          alert('삭제되었습니다.');
                          location.reload();
                      } else {
                          alert('삭제 실패: ' + data.message);
                      }
                  });
            }
        }
    </script>
</body>
</html>
