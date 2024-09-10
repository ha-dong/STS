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
                <span class="username">홍길동님</span>
                <button class="logout-button">로그아웃</button>
            </div>
        </header>

        <main>
            <h1>스토리 목록</h1>

            <!-- 장르 탭 -->
            <div class="genre-tabs">
                <button class="genre-tab">전체</button>
                <button class="genre-tab">사극</button>
                <button class="genre-tab">스릴러</button>
                <button class="genre-tab">판타지</button>
                <button class="genre-tab">코미디</button>
                <button class="genre-tab">SF</button>
            </div>

<!-- 스토리 리스트 -->
            <div class="story-list">
                <div class="story-item">
                    <div class="story-image"></div>
                    <div class="story-info">
                        <p>스토리 이름: 무더운 여름 밤</p>
                        <p>장르: 스릴러</p>
                        <p>제작자: 사과</p>
                    </div>
                    <div class="story-like">
                        <span>101</span>
                        <img src="../images/heart.png" alt="Heart Icon" class="heart-icon">
                    </div>
                </div>

                <div class="story-item">
                    <div class="story-image"></div>
                    <div class="story-info">
                        <p>스토리 이름: 옆자리 그녀</p>
                        <p>장르: 코미디</p>
                        <p>제작자: 포도</p>
                    </div>
                    <div class="story-like">
                        <span>51</span>
                        <img src="../images/heart.png" alt="Heart Icon" class="heart-icon">
                    </div>
                </div>

                <div class="story-item">
                    <div class="story-image"></div>
                    <div class="story-info">
                        <p>스토리 이름: 나으리</p>
                        <p>장르: 사극</p>
                        <p>제작자: 오렌지</p>
                    </div>
                    <div class="story-like">
                        <span>21</span>
                        <img src="../images/heart.png" alt="Heart Icon" class="heart-icon">
                    </div>
                </div>

                <div class="story-item">
                    <div class="story-image"></div>
                    <div class="story-info">
                        <p>스토리 이름: 여름미어따</p>
                        <p>장르: SF</p>
                        <p>제작자: 블루베리</p>
                    </div>
                    <div class="story-like">
                        <span>101</span>
                        <img src="../images/heart.png" alt="Heart Icon" class="heart-icon">
                    </div>
                </div>
            </div>

            <!-- 정렬 드롭다운 -->
            <div class="sort-dropdown">
                <select>
                    <option disabled selected>--인기--</option>
                    <option>인기순</option>
                    <option>최신순</option>
                </select>
            </div>

            <!-- 커스텀 설정 버튼 -->
            <button class="customize-button">커스텀 설정</button>
        </main>
    </div>
    <script src="<c:url value='/resources/js/storylist.js'/>"></script>
</body>
</html>
