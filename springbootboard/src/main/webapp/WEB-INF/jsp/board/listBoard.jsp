<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글 목록</title>
<link rel="stylesheet" href="/css/board.css" />
</head>
<body>
<div id="wrapper">
	<div id="title">
		<h1>게시글 목록</h1>
	</div>
	<div id="content" class="listBoard">
		<table>
			<colgroup>
				<col width="50">
				<col width="400">
				<col width="100">
				<col width="100">
				<col width="50">
			</colgroup>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>등록일</th>
				<th>조회수</th>
			</tr>
			<c:forEach var="board" items="${boardList }">
				<tr>
					<td>${board.seq }</td>
					<td class="alignLeft">
						<a href="/board/getBoard?seq=${board.seq }">${board.title }</a>
					</td>
					<td>${board.writer }</td>
					<td><fmt:formatDate value="${board.createDate }"
							pattern="yyyy-MM-dd"></fmt:formatDate>
					</td>
					<td>${board.cnt }</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div>
		<a href="/board/insertBoard">새글 등록</a>
	</div>
</div>
</body>
</html>







