<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>스토리 목록</title>
    <link rel="stylesheet" href="<c:url value='/resources/css/storylist.css'/>">
</head>
<body>
    <div class="container">
        <header>
            <div class="header-left">
                <div class="logo-placeholder">로고</div>
            </div>
            <div class="header-right">
                <button class="notice-button">공지사항</button>
                <span class="username">${sessionScope.user}님</span>
                <button class="logout-button" onclick="location.href='/storycraft/logout'">로그아웃</button>
            </div>
        </header>

        <main>
            <h1>스토리 목록</h1>

            <!-- 장르 탭 -->
            <div class="genre-tabs">
                <button class="genre-tab">전체</button>
                <button class="genre-tab">사극</button>
                <button class="genre-tab">스릴러</button>
                <button class="genre-tab">판타지</button>
                <button class="genre-tab">코미디</button>
                <button class="genre-tab">SF</button>
            </div>

            <!-- 스토리 리스트 -->
            <div class="story-list">
                <c:forEach var="story" items="${stories}">
                    <div class="story-item" onclick="openStoryModal('${story.title}', '${story.genre}', '${story.creator}', '${story.likes}', ${story.isCreator}, '${story.image}')">
                        <div class="story-image">
                            <img src="<c:url value='/resources/img/${story.image}'/>" alt="썸네일" width="60" height="60">
                        </div>
                        <div class="story-info">
                            <p>스토리 이름: ${story.title}</p>
                            <p>장르: ${story.genre}</p>
                            <p>제작자: ${story.creator}</p>
                        </div>
                        <div class="story-like">
                            <span>${story.likes}</span>
                            <img src="<c:url value='/resources/img/heart.jpg'/>" alt="Heart Icon" class="heart-icon" onclick="likeStory(event, ${story.id})">
                        </div>
                    </div>
                </c:forEach>
            </div>

            <!-- 정렬 드롭다운 -->
            <div class="sort-dropdown">
                <select id="sortBy" onchange="sortStories()">
                    <option disabled selected>--인기--</option>
                    <option value="like">인기순</option>
                    <option value="recent">최신순</option>
                </select>
            </div>

            <!-- 커스텀 설정 버튼 -->
            <button class="customize-button" onclick="location.href='/storyadd'">커스텀 설정</button>
        </main>
    </div>

    <!-- 스토리 상세 모달창 -->
    <div id="storyModal" class="modal" style="display: none;">
        <div class="modal-content">
            <span class="close" onclick="closeStoryModal()">&times;</span>
            <img src="#" id="storyThumbnail" alt="썸네일">
            <h2 id="storyTitle"></h2>
            <p id="storyGenre"></p>
            <p id="storyCreator" onclick="goToProfile()"></p>
            <p id="storySummary">스토리에 대한 한줄평</p>
            <button id="startButton" onclick="startStory()">시작</button>
            <button id="heartButton" onclick="likeStory()">하트 <span id="likeCount">0</span></button>
            <button id="editButton" style="display:none;" onclick="editStory()">수정</button>
            <button id="deleteButton" style="display:none;" onclick="deleteStory()">삭제</button>
            <button id="reportButton" style="display:none;" onclick="openReportModal()">신고</button>
        </div>
    </div>

    <!-- 신고 모달창 -->
    <div id="reportModal" class="modal" style="display: none;">
        <div class="modal-content">
            <span class="close" onclick="closeReportModal()">&times;</span>
            <h2>신고하기</h2>
            <label for="reportReason">신고 사유 선택:</label>
            <select id="reportReason">
                <option value="user">사용자 신고</option>
                <option value="story">스토리 신고</option>
            </select>
            <label for="reportDetails">상세 내용:</label>
            <textarea id="reportDetails" placeholder="신고 내용을 작성하세요"></textarea>
            <button id="submitReport" onclick="submitReport()">제출하기</button>
        </div>
    </div>

    <script src="<c:url value='/resources/js/storylist.js'/>"></script>

    <script>
        // 모달창을 여는 함수
        function openStoryModal(title, genre, creator, likes, isCreator, thumbnail) {
            document.getElementById('storyTitle').innerText = title;
            document.getElementById('storyGenre').innerText = genre;
            document.getElementById('storyCreator').innerText = creator;
            document.getElementById('likeCount').innerText = likes;
            document.getElementById('storyThumbnail').src = thumbnail;

            if (isCreator) {
                document.getElementById('editButton').style.display = 'block';
                document.getElementById('deleteButton').style.display = 'block';
                document.getElementById('reportButton').style.display = 'none';
            } else {
                document.getElementById('editButton').style.display = 'none';
                document.getElementById('deleteButton').style.display = 'none';
                document.getElementById('reportButton').style.display = 'block';
            }

            document.getElementById('storyModal').style.display = 'block';
        }

        // 모달창을 닫는 함수
        function closeStoryModal() {
            document.getElementById('storyModal').style.display = 'none';
        }

        // 프로필 페이지로 이동
        function goToProfile() {
            const creator = document.getElementById('storyCreator').innerText;
            location.href = '/profile/' + creator;
        }

        // 신고 모달창 열기
        function openReportModal() {
            document.getElementById('reportModal').style.display = 'block';
        }

        // 신고 모달창 닫기
        function closeReportModal() {
            document.getElementById('reportModal').style.display = 'none';
        }

        // 신고 제출
        function submitReport() {
            const reason = document.getElementById('reportReason').value;
            const details = document.getElementById('reportDetails').value;

            if (!details) {
                alert('신고 내용을 입력하세요.');
                return;
            }

            // 서버로 신고 데이터 전송 로직 추가 가능
            alert('신고가 접수되었습니다.');
            closeReportModal();
        }

        // 하트(추천) 기능
        function likeStory(event, storyId) {
            event.stopPropagation(); // 모달창이 열리지 않도록 이벤트 중지

            const heartIcon = event.target;
            heartIcon.style.pointerEvents = 'none'; // 하트 중복 클릭 방지

            // 서버에 하트 클릭 데이터 전송 로직 추가 가능
            alert('하트를 눌렀습니다!');

            const likeCount = heartIcon.previousElementSibling;
            likeCount.innerText = parseInt(likeCount.innerText) + 1;
        }

        // 스토리 수정 기능
        function editStory() {
            const storyTitle = document.getElementById('storyTitle').innerText;
            location.href = '/storyedit/' + storyTitle;
        }

        // 스토리 삭제 기능
        function deleteStory() {
            const confirmDelete = confirm('정말로 이 스토리를 삭제하시겠습니까?');
            if (confirmDelete) {
                // 서버에 스토리 삭제 요청
                alert('스토리가 삭제되었습니다.');
                closeStoryModal();
                location.reload();
            }
        }

        // 스토리 시작 기능
        function startStory() {
            alert('스토리를 시작합니다.');
            location.href = '/storyplay';
        }

        // 스토리 정렬 기능
        function sortStories() {
            const sortBy = document.getElementById('sortBy').value;
            alert(`${sortBy == 'like' ? '인기순' : '최신순'}으로 정렬합니다.`);
            // 정렬된 데이터 로딩 로직 추가 가능
        }
    </script>
</body>
</html>