<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>스토리 커스텀</title>
    <link rel="stylesheet" href="<c:url value='/resources/css/story.css'/>">
    <style>
        /* 이미지 크기 고정 */
        #preview {
            width: 300px;
            height: 120px;
            object-fit: cover;
        }
    </style>
</head>
<body>
	<div class="container">
	<header>
	    <div class="header-logo-text">
	       <a href="<c:url value='/main'/>">
            <img src="/StoryCraft/resources/img/logo.png" alt="로고" class="small-logo">
       		</a>
	        <h1 class="header-title">스토리 커스텀</h1>
	    </div>
	    <div class="login-box">
	        <c:choose>
	            <c:when test="${not empty sessionScope.user}">
	                <span>${sessionScope.user}님</span>
	                <button class="login-button" onclick="location.href='/storycraft/logout'">로그아웃</button>
	            </c:when>
	            <c:otherwise>
	                <span>로그인이 필요합니다.</span>
	                <button class="login-button" onclick="location.href='/storycraft/login'">로그인</button>
	            </c:otherwise>
	        </c:choose>
	    </div>
	</header>

    <main>
        <form id="storyForm" method="POST" action="/saveStory" enctype="multipart/form-data">
            <!-- 제목 입력 -->
            <div class="title-input">
                <label for="title">스토리 제목:</label>
                <input type="text" id="title" name="title" placeholder="스토리 제목을 입력하세요" required>
            </div>

            <!-- 제작자 입력 -->
            <div class="creator-input">
                <label for="creator">제작자 이름: </label>
                <input type="text" id="creator" name="creator" value="${sessionScope.user}" readonly>
            </div>

            <!-- 장르 선택 -->
            <div class="genre-selection">
                <label for="genre">장르 선택 :</label>
                <select name="genre" id="genre">
                    <option value="fantasy">판타지</option>
                    <option value="romance">로맨스</option>
                    <option value="thriller">스릴러</option>
                    <option value="horror">호러</option>
                    <option value="science-fiction">SF</option>
                </select>
            </div>

            <!-- 이미지 업로드 -->
            <div class="image-upload" onclick="document.getElementById('file-input').click();">
                <input type="file" id="file-input" name="coverImage" accept="image/*" style="display: none;">
                <img id="preview" src="#" alt="이미지 미리보기" style="display: none;">
                <span id="upload-text">클릭하여 이미지를 업로드하세요</span>
            </div>

            <!-- 돈, 체력 입력 -->
            <div class="money-input">
                <label for="money">초기 돈 설정:</label>
                <input type="number" id="money" name="money" min="0" max="50" value="0">
            </div>

            <div class="hp-input">
                <label for="hp">초기 체력 설정:</label>
                <input type="number" id="hp" name="hp" min="10" max="100" value="100">
            </div><br/>

            <!-- 스토리 내용 작성 -->
            <div class="story-content">
                <textarea name="storyContent" placeholder="여기에 스토리를 작성하세요..." required></textarea>
            </div>

            <!-- 선택지 추가 -->
            <div class="additional-content">
                <div class="choice">
                    <input type="text" name="choice1-name" maxlength="5" placeholder="선택지 이름 (최대 5글자)" required>
                    <textarea name="choice1" placeholder="선택지 1" required></textarea>
                    <label for="choice1-money">돈 변화:</label>
                    <input type="number" name="choice1-money" min="-50" max="50" value="0">
                    <label for="choice1-hp">체력 변화:</label>
                    <input type="number" name="choice1-hp" min="-100" max="100" value="0">
                </div><br/>
                <button id="add-choice" class="add-choice-button" type="button">+</button>
            </div><br/>

            <!-- 선택지 드롭다운 -->
            <div class="choices-dropdown">
                <label for="choices-dropdown">선택지 이름 확인:</label>
                <select id="choices-dropdown">
                    <option value="">선택지 선택</option>
                </select>
            </div>
        </form>
    </main>

    <footer>
        <button class="reset-button" type="button" onclick="document.getElementById('storyForm').reset();">초기화</button>
        <button class="save-button" type="button">저장</button>
    </footer>

    <script src="<c:url value='/resources/js/story.js'/>"></script>

    <script>
        let choiceCount = 1;

        // 이미지 미리보기 기능
        document.getElementById('file-input').addEventListener('change', function(event) {
            const file = event.target.files[0];
            const reader = new FileReader();

            reader.onload = function(e) {
                const preview = document.getElementById('preview');
                preview.src = e.target.result;
                preview.style.display = 'block';
                document.getElementById('upload-text').style.display = 'none';
            };
            
            if (file) {
                reader.readAsDataURL(file);
            } else {
                const preview = document.getElementById('preview');
                preview.src = '';
                preview.style.display = 'none';
                document.getElementById('upload-text').style.display = 'block';
            }
        });

        // 선택지 추가 기능
        document.getElementById('add-choice').addEventListener('click', function() {
            if (choiceCount < 3) {
                choiceCount++;
                const choiceContainer = document.querySelector('.additional-content');
                const newChoice = document.createElement('div');
                newChoice.className = 'choice';
                newChoice.innerHTML = `
                    <input type="text" name="choice${choiceCount}-name" maxlength="5" placeholder="선택지 이름 (최대 5글자)" required>
                    <textarea name="choice${choiceCount}" placeholder="선택지 ${choiceCount}" required></textarea>
                    <label for="choice${choiceCount}-money">돈 변화:</label>
                    <input type="number" name="choice${choiceCount}-money" min="-50" max="50" value="0">
                    <label for="choice${choiceCount}-hp">체력 변화:</label>
                    <input type="number" name="choice${choiceCount}-hp" min="-100" max="100" value="0">
                `;
                choiceContainer.insertBefore(newChoice, this);

                // 드롭다운에 선택지 추가
                const dropdown = document.getElementById('choices-dropdown');
                const option = document.createElement('option');
                option.value = `choice${choiceCount}`;
                option.text = `선택지 ${choiceCount}`;
                dropdown.appendChild(option);
            } else {
                alert('선택지는 최대 3개까지만 추가할 수 있습니다.');
            }
        });

        // 저장 버튼 클릭 시 선택지별 스토리 제작 버튼 추가
        document.querySelector('.save-button').addEventListener('click', function (event) {
            event.preventDefault(); // 기본 폼 제출 방지
            alert('스토리가 저장되었습니다.');

            const choices = document.querySelectorAll('.choice');
            choices.forEach((choice, index) => {
                const storyButton = document.createElement('button');
                storyButton.textContent = `선택지 ${index + 1}에 따른 스토리 작성`;
                storyButton.classList.add('story-buttons');
                storyButton.type = 'button';

                // 클릭 시 스토리 제작 페이지로 이동
                storyButton.addEventListener('click', function () {
                    location.href = `/storyadd?choice=${index + 1}`;
                });

                choice.appendChild(storyButton);
            });
        });
    </script>
</body>
</html>