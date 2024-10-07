<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>스토리 목록</title>
    <!-- Bootstrap CSS 추가 -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- 커스텀 CSS -->
    <link rel="stylesheet" href="<c:url value='/resources/css/storyList.css'/>">
    <!-- jQuery 및 Bootstrap JS 추가 -->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <!-- 컨텍스트 경로 및 userId 설정 -->
    <script>
        var contextPath = '${pageContext.request.contextPath}/';
        var userId = "${userId != null ? fn:replace(userId, '\"', '\\\"') : 'subo'}";
        console.log("User ID:", userId); // 디버깅용 로그
    </script>
</head>
<body>
    <!-- 헤더 부분 시작 -->
    <div class="header">
        <a href="${contextPath}/StoryCraft/main">
            <img src="${pageContext.request.contextPath}/resources/img/logo.png" alt="로고" class="logo" id="logo">
        </a>
    </div>
    <!-- 헤더 부분 끝 -->
    
    <div class="login-box">
        <c:choose>
            <c:when test="${not empty sessionScope.user}">
                <span>${sessionScope.user}님</span> <!-- 사용자 이름 표시 -->
                <button class="login-button" onclick="logout()">로그아웃</button>
            </c:when>
            <c:otherwise>
                <button class="login-button" onclick="location.href='<c:url value='/login'/>'">로그인</button>
                <button class="login-button" onclick="location.href='<c:url value='/register'/>'">회원가입</button>
            </c:otherwise>
        </c:choose>
    </div>

    <div class="container mt-4">
        <h1>스토리 목록</h1>
        <!-- 드롭다운 메뉴 -->
        <div class="row mb-3">
            <div class="col-md-6">
                <select id="categorySelect" class="form-control" onchange="filterAndSort()">
                    <option value="">전체 카테고리</option>
                    <c:forEach var="code" items="${genreList}">
                        <option value="${code.CODE}" <c:if test="${param.genre == code.CODE}">selected</c:if>>${code.CODE_NAME}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-md-6">
                <select id="sortSelect" class="form-control" onchange="filterAndSort()">
                    <option value="recent" <c:if test="${param.sort == 'recent'}">selected</c:if>>최신순</option>
                    <option value="popular" <c:if test="${param.sort == 'popular'}">selected</c:if>>인기순</option>
                </select>
            </div>
        </div>
        <!-- 스토리 목록 -->
        <div class="story-list row">
            <c:forEach var="story" items="${storyList}">
                <!-- 장르 이름 추출 -->
                <c:set var="genreName" value="" />
                <c:forEach var="code" items="${genreList}">
                    <c:if test="${code.CODE == story.stGenrecode}">
                        <c:set var="genreName" value="${code.CODE_NAME}" />
                    </c:if>
                </c:forEach>
                <div class="story-item col-md-3 mb-4" data-genre="${story.stGenrecode}" data-genre-name="${genreName}" data-stnum="${story.stNum}"
                     data-title="${story.stTitle}" data-cover="${story.stCover}" data-uid="${story.uId}"
                     data-viewnum="${story.stViewnum}" data-sugnum="${story.stSugnum}" data-crdate="${story.stCrdate}">
                    <div class="card">
                        <img src="<c:url value='/uploads/${story.stCover}'/>" class="card-img-top" alt="스토리 표지">
                        <div class="card-body">
                            <h5 class="card-title">${story.stTitle}</h5>
                            <p class="card-text">작성자: ${story.uId}</p>
                            <p class="card-text">조회수: ${story.stViewnum}</p>
                            <p class="card-text">추천수: ${story.stSugnum}</p>
                            <button class="btn btn-primary" onclick="openStoryModal(this)">자세히 보기</button>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

    <!-- 스토리 상세 모달 -->
    <div class="modal fade" id="storyModal" tabindex="-1" role="dialog" aria-labelledby="storyModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <!-- 모달 헤더 -->
                <div class="modal-header">
                    <h5 class="modal-title">스토리 제목</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="닫기">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <!-- 모달 바디 -->
                <div class="modal-body">
                    <img src="#" alt="스토리 표지" class="img-fluid">
                    <p class="genre">장르: </p>
                    <p class="uId">작성자: </p>
                    <p class="viewNum">조회수: </p>
                    <p class="sugNum">추천수: </p>
                    <p class="crDate">생성일: </p>
                    <!-- 기타 정보 추가 가능 -->
                </div>
                <!-- 모달 푸터 -->
                <div class="modal-footer">
                    <button class="btn btn-secondary edit-btn" onclick="">수정</button>
                    <button class="btn btn-danger delete-btn" onclick="">삭제</button>
                    <button class="btn btn-warning report-btn" onclick="">신고</button>
                    <button class="btn btn-success recommend-btn" onclick="">추천하기</button>
                    <button class="btn btn-primary play-btn" onclick="">플레이하기</button>
                </div>
            </div>
        </div>
    </div>

    <!-- JavaScript 파일 -->
    <script src="<c:url value='/resources/js/storyList.js'/>"></script>
    <script>
        // 로그아웃 함수 추가
        function logout() {
            fetch('<c:url value="/api/logout" />', { // AuthController의 /logout 엔드포인트 확인
                method: 'POST'
            })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    alert('로그아웃 되었습니다.');
                    window.location.href = '<c:url value="/login" />';
                } else {
                    alert('로그아웃에 실패했습니다.');
                }
            })
            .catch(error => {
                console.error('로그아웃 중 오류 발생:', error);
                alert('로그아웃 중 오류가 발생했습니다.');
            });
        }

        // 필터링 및 정렬 함수
      function filterAndSort() {
          const genre = document.getElementById('categorySelect').value;
          const sort = document.getElementById('sortSelect').value;
          window.location.href = contextPath + 'story/list?genre=' + encodeURIComponent(genre) + '&sort=' + encodeURIComponent(sort);
      }
    </script>
</body>
</html>
