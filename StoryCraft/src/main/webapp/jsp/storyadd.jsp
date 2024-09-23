<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>스토리 추가</title>
    <link rel="stylesheet" href="<c:url value='/resources/css/storyadd.css'/>">
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
            <div class="header-logo">
                <a href="<c:url value='/main'/>">
                    <img src="/StoryCraft/resources/img/logo.png" alt="로고" class="small-logo">
                </a>
                <h1>선택지 별 스토리</h1> <!-- 제목 -->
            </div>
            <div class="header-right">
                <c:choose>
                    <c:when test="${not empty sessionScope.user}">
                        <span>${sessionScope.user}님</span> <!-- 사용자 이름 표시 -->
                        <button class="logout-button" onclick="location.href='/storycraft/logout'">로그아웃</button> <!-- 로그아웃 버튼 -->
                    </c:when>
                    <c:otherwise>
                        <button class="login-button" onclick="location.href='/storycraft/login'">로그인</button> <!-- 로그인 버튼 -->
                    </c:otherwise>
                </c:choose>
            </div>
        </header>

        <main>
            <form id="storyForm" method="POST" action="/saveStory" enctype="multipart/form-data">
                <!-- 이미지 업로드 -->
                <div class="image-upload" onclick="document.getElementById('file-input').click();">
                    <input type="file" id="file-input" name="coverImage" accept="image/*" style="display: none;">
                    <img id="preview" src="#" alt="이미지 미리보기" style="display: none;">
                    <span id="upload-text">클릭하여 이미지를 업로드하세요</span>
                </div>

                <!-- 스토리 내용 작성 -->
                <div class="story-content">
                    <textarea name="storyContent" placeholder="여기에 스토리를 작성하세요..." required></textarea>
                </div>

                <!-- 선택지 추가 -->
                <div class="additional-content">
                    <!-- 기본 선택지 1개 -->
                    <div class="choice">
                        <input type="text" name="choice1-name" maxlength="5" placeholder="선택지 이름 (최대 5글자)" required>
                        <textarea name="choice1" placeholder="선택지 1" required></textarea>
                        <label for="choice1-money">돈 변화:</label>
                        <input type="number" name="choice1-money" min="-50" max="50" value="0">
                        <label for="choice1-hp">체력 변화:</label>
                        <input type="number" name="choice1-hp" min="-100" max="100" value="0">
                    </div>

                    <!-- +버튼을 클릭하여 최대 3개의 선택지 추가 가능 -->
                    <button id="add-choice" class="add-choice-button" type="button">+</button>
                </div>

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
            <button class="submit-button" type="submit" form="storyForm">제출하기</button>
        </footer>
    </div>

    <script src="<c:url value='/resources/js/storyadd.js'/>"></script>

    <!-- JavaScript로 스토리 추가 및 선택지 기능 구현 -->
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

                // 드롭다운에 새로운 선택지 이름 추가
                const dropdown = document.getElementById('choices-dropdown');
                const option = document.createElement('option');
                option.value = `choice${choiceCount}`;
                option.text = `선택지 ${choiceCount}`;
                dropdown.add(option);
            } else {
                alert('선택지는 최대 3개까지만 추가할 수 있습니다.');
            }
        });

        // 선택지 저장 기능
        document.querySelector('.save-button').addEventListener('click', function (event) {
            event.preventDefault();  // 기본 폼 제출 방지
            alert('선택지가 저장되었습니다.');

            // 선택지별 스토리 작성 버튼이 이미 있으면 다시 추가하지 않음
            if (document.querySelector('.story-buttons')) {
                return;
            }

            // 선택지별 스토리 작성 버튼을 동적으로 추가
            const choices = document.querySelectorAll('.choice');
            choices.forEach((choice, index) => {
                const storyButton = document.createElement('button');
                storyButton.textContent = `선택지 ${index + 1}에 따른 스토리 작성`;
                storyButton.classList.add('story-buttons');
                storyButton.type = 'button';

                // 클릭 시 storyadd.jsp로 이동
                storyButton.addEventListener('click', function () {
                    location.href = `/storyadd?choice=${index + 1}`;
                });

                // 선택지 아래에 버튼 추가
                choice.appendChild(storyButton);
            });
        });

        // 드롭다운 메뉴에서 선택된 항목을 클릭하면 선택지 제작 페이지로 이동
        document.getElementById('choices-dropdown').addEventListener('change', function () {
            const selectedChoice = this.value;
            if (selectedChoice) {
                location.href = `/storyadd?choice=${selectedChoice}`;
            }
        });

        // 초기화 버튼 기능 (폼 리셋)
        document.querySelector('.reset-button').addEventListener('click', function (event) {
            event.preventDefault();  // 기본 초기화 동작 방지

            const confirmReset = confirm('모든 내용을 초기화하시겠습니까?');
            if (confirmReset) {
                const form = document.getElementById('storyForm');
                form.reset();  // 폼 내용을 초기화

                // 기본 선택지 하나를 제외한 추가된 선택지 삭제
                const choices = document.querySelectorAll('.additional-content .choice');
                choices.forEach((choice, index) => {
                    if (index > 0) {
                        choice.remove();
                    }
                });
                choiceCount = 1;  // 기본 선택지 하나로 리셋

                // 동적으로 추가된 선택지 작성 버튼 삭제
                const storyButtons = document.querySelectorAll('.story-buttons');
                storyButtons.forEach(button => button.remove());

                // 이미지 미리보기 숨기기
                const preview = document.getElementById('preview');
                preview.src = '';
                preview.style.display = 'none';
                document.getElementById('upload-text').style.display = 'block';
            }
        });
    </script>
</body>
</html>