<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
<title>스토리 관리 페이지</title>
<!-- Bootstrap CSS 추가 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<!-- 커스텀 CSS 추가 -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/adminStoryList.css">
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
				<li><a href="${pageContext.request.contextPath}/admin/stories">스토리
						관리</a></li>
				<li><a href="${pageContext.request.contextPath}/adminUser">유저
						관리</a></li>
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

	<div class="container mt-5">
		<h1 class="mb-4">스토리 관리</h1>

		<!-- 정렬 및 검색 폼 -->
		<form method="get"
			action="${pageContext.request.contextPath}/admin/stories"
			class="form-inline mb-3">
			<div class="form-group mr-2">
				<label for="orderBy" class="mr-2">정렬 기준:</label> <select
					name="orderBy" id="orderBy" class="form-control">
					<option value="ST_NUM" ${orderBy == 'ST_NUM' ? 'selected' : ''}>스토리 번호</option>
					<option value="ST_RENUM" ${orderBy == 'ST_RENUM' ? 'selected' : ''}>신고
						수</option>
					<option value="ST_SUGNUM"
						${orderBy == 'ST_SUGNUM' ? 'selected' : ''}>추천 수</option>
					<option value="ST_VIEWNUM"
						${orderBy == 'ST_VIEWNUM' ? 'selected' : ''}>조회 수</option>
					<!-- 필요한 정렬 기준 추가 -->
				</select>
			</div>

			<div class="form-group mr-2">
				<label for="orderDirection" class="mr-2">정렬 방향:</label> <select
					name="orderDirection" id="orderDirection" class="form-control">
					<option value="ASC" ${orderDirection == 'ASC' ? 'selected' : ''}>오름차순</option>
					<option value="DESC" ${orderDirection == 'DESC' ? 'selected' : ''}>내림차순</option>
				</select>
			</div>

			<div class="form-group mr-2">
				<label for="minReports" class="mr-2">신고 수 최소값:</label> <input
					type="number" name="minReports" id="minReports"
					class="form-control" value="${param.minReports}" placeholder="0">
				<label for="maxReports" class="mr-2">신고 수 최대값:</label> <input
					type="number" name="maxReports" id="maxReports"
					class="form-control" value="${param.maxReports}" placeholder="100">
			<!-- 버튼을 form-group으로 감싸기 -->
			<div class="form-group">
				<button type="submit" class="btn btn-primary">검색 및 정렬</button>
			</div>
			</div>

		</form>

		<!-- 스토리 테이블 -->
		<table class="table table-bordered table-hover">
			<thead class="thead-dark">
				<tr>
					<th></th>
					<th>제목</th>
					<th>장르</th>
					<th>생성일</th>
					<th>추천수</th>
					<th>신고 개수</th>
					<th>조회수</th>
					<th>제작자</th>
					<th>삭제</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="story" items="${stories}">
					<tr>
						<td>${story.stNum}</td>
						<td>${story.stTitle}</td>
						<td>${genreMap[story.stGenrecode]}</td>
						<td><fmt:formatDate value="${story.stCrdate}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td>${story.stSugnum}</td>
						<td>${story.stRenum}</td>
						<td>${story.stViewnum}</td>
						<td>${story.uId}</td>
						<td>
							<button class="btn btn-danger btn-sm"
								onclick="deleteStory(${story.stNum})">삭제</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<!-- Bootstrap 및 jQuery JS 추가 -->
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>

	<!-- 삭제 기능을 위한 JavaScript 코드 -->
	<script>
    function deleteStory(stNum) {
        if (confirm('정말로 이 스토리를 삭제하시겠습니까?')) {
            $.ajax({
                url: '${pageContext.request.contextPath}/admin/stories/delete',
                type: 'POST',
                data: {stNum: stNum},
                success: function (response) {
                    if (response.success) {
                        alert('스토리가 성공적으로 삭제되었습니다.');
                        location.reload();
                    } else {
                        alert('스토리 삭제에 실패하였습니다.');
                    }
                },
                error: function () {
                    alert('오류가 발생하였습니다.');
                }
            });
        }
    }
</script>
</body>
</html>
