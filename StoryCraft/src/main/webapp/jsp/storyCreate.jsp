<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>스토리 제작</title>
    
    <!-- 기본 CSS 파일 설정 -->
    <link id="themeStylesheet" rel="stylesheet" href="<c:url value='/resources/css/storyCreate.css'/>">

    <script src="<c:url value='/resources/js/storyCreate.js'/>"></script>
    
    <!-- 컨텍스트 경로를 JavaScript 변수으로 설정 -->
    <script>
        let contextPath = '<c:url value="/" />';
        let editMode = ${editMode != null ? editMode : false};
        let stNum = '<c:out value="${story != null ? story.stNum : ''}" />';
        stNum = stNum !== '' ? parseInt(stNum) : null;

        // 색상 변경 함수 - CSS 파일을 동적으로 변경
        function changeTheme() {
            const themeStylesheet = document.getElementById('themeStylesheet');
            const themeButton = document.getElementById('themeButton');
            const currentTheme = themeStylesheet.getAttribute('href');
            const newTheme = currentTheme.includes('storyCreate.css') ? 'storyCreate2.css' : 'storyCreate.css';
            
            // 경로 설정에 맞게 CSS 파일 교체
            themeStylesheet.setAttribute('href', contextPath + 'resources/css/' + newTheme);
            
            // 버튼 스타일 변경
            if (newTheme === 'storyCreate.css') {
                themeButton.style.backgroundColor = '#fff';  // 하얀 배경
                themeButton.style.color = '#000';  // 검은색 글자
            } else {
                themeButton.style.backgroundColor = '#000';  // 검은 배경
                themeButton.style.color = '#fff';  // 흰색 글자
            }
        }
    </script>
</head>
<body>
    <!-- 헤더 부분 시작 -->
    <div class="header" style="position: relative;">
        <img src="<c:url value='/resources/img/logo.png'/>" alt="로고" class="logo" id="logo" onclick="location.href='<c:url value='/main'/>'" style="float:left;">
        
        <!-- 색상 변경 버튼 -->
        <button id="themeButton" onclick="changeTheme()" style="position: absolute; right: 100px; top: 20px; background-color: #fff; color: #000;">색상 변경</button>
    </div>
    <!-- 헤더 부분 끝 -->

    <div class="container">
        <h1>${editMode ? '스토리 수정' : '스토리 제작'}</h1>

        <form id="storyForm" enctype="multipart/form-data">
            <c:if test="${editMode}">
                <input type="hidden" name="stNum" value="${story.stNum}">
            </c:if>
            <!-- 스토리 기본 정보 -->
            <div class="story-info">
                <label for="title">제목:</label>
                <input type="text" id="title" name="title" required value="${editMode ? story.stTitle : ''}">

                <label for="cover">표지 이미지:</label>
                <input type="file" id="cover" name="cover" accept="image/*" onchange="previewImage(this, 'coverPreview')">
                <img id="coverPreview" src="${editMode && story.stCover != null ? '/uploads/' + story.stCover : '#'}" alt="이미지 미리보기" style="display:${editMode && story.stCover != null ? 'block' : 'none'}; width:200px; height:200px; object-fit:cover;">

                <label for="genre">장르:</label>
                <select id="genre" name="genre" required>
                    <option value="">선택하세요</option>
                    <c:forEach var="code" items="${genreList}">
                        <option value="${code.CODE}" <c:if test="${editMode && code.CODE == story.stGenrecode}">selected</c:if>>${code.CODE_NAME}</option>
                    </c:forEach>
                </select>

                <label for="initialMoney">초기 돈 설정 (0~50):</label>
                <input type="number" id="initialMoney" name="initialMoney" min="0" max="50" value="${editMode ? story.stSugnum : 0}" required>

                <label for="initialHP">초기 체력 설정 (10~100):</label>
                <input type="number" id="initialHP" name="initialHP" min="10" max="100" value="${editMode ? story.initialHP : 100}" required>

                <!-- 엔딩 코드 선택 -->
                <label for="endCode">엔딩 코드:</label>
                <select id="endCode" name="endCode">
                    <option value="">선택하세요</option>
                    <option value="CE-01" <c:if test="${editMode && 'CE-01' == story.endCode}">selected</c:if>>해피엔딩</option>
                    <option value="CE-02" <c:if test="${editMode && 'CE-02' == story.endCode}">selected</c:if>>새드엔딩</option>
                    <option value="CE-03" <c:if test="${editMode && 'CE-03' == story.endCode}">selected</c:if>>열린결말</option>
                </select>
            </div>

            <!-- 스토리 내용 및 선택지 -->
            <div class="scene-editor" id="sceneEditor">
                <c:if test="${editMode}">
                    <c:forEach var="scene" items="${story.scenes}">
                        <div class="scene" data-scene-num="${scene.scNum}" data-parent-scene-num="${scene.parentScNum}">
                            <h2>장면 ${scene.scNum}</h2>
                            <label for="sceneText_${scene.scNum}">스토리 내용:</label>
                            <textarea id="sceneText_${scene.scNum}" name="sceneText_${scene.scNum}" required>${scene.scText}</textarea>

                            <label for="sceneImage_${scene.scNum}">삽화 이미지:</label>
                            <input type="file" id="sceneImage_${scene.scNum}" name="sceneImage_${scene.scNum}" accept="image/*" onchange="previewImage(this, 'sceneImagePreview_${scene.scNum}')">
                            <img id="sceneImagePreview_${scene.scNum}" src="${scene.scIllus != null ? '/uploads/' + scene.scIllus : '#'}" alt="이미지 미리보기" style="display:${scene.scIllus != null ? 'block' : 'none'}; width:200px; height:200px; object-fit:cover;">

                            <!-- 선택지 추가 버튼 -->
                            <button type="button" class="add-choice-btn" onclick="addChoice(${scene.scNum})">선택지 추가</button>

                            <!-- 선택지 목록 -->
                            <div class="choices" id="choices_${scene.scNum}">
                                <c:forEach var="choice" items="${scene.choices}">
                                    <div class="choice" data-choice-num="${choice.choiceNum}">
                                        <h3>선택지 ${choice.choiceNum}</h3>
                                        <label>선택지 이름:</label>
                                        <input type="text" name="choiceName_scene_${scene.scNum}_choice_${choice.choiceNum}" required value="${choice.choiceName}">
                                        
                                        <label>선택지 내용:</label>
                                        <textarea name="choiceContent_scene_${scene.scNum}_choice_${choice.choiceNum}" required>${choice.choiceContent}</textarea>

                                        <label>돈 변화량:</label>
                                        <input type="number" name="choiceMoney_scene_${scene.scNum}_choice_${choice.choiceNum}" value="${choice.money}">

                                        <label>체력 변화량:</label>
                                        <input type="number" name="choiceHP_scene_${scene.scNum}_choice_${choice.choiceNum}" value="${choice.hp}">

                                        <!-- 선택지 저장 버튼 -->
                                        <button type="button" onclick="saveChoice(${scene.scNum}, ${choice.choiceNum})">선택지 저장</button>

                                        <!-- 선택지에 따른 스토리 작성 버튼 -->
                                        <button type="button" id="createSceneBtn_${scene.scNum}_${choice.choiceNum}" style="display: ${choice.nextSceneNum != null ? 'inline-block' : 'none'}" onclick="createScene(${scene.scNum}, ${choice.choiceNum})">${choice.choiceName} 선택지에 따른 스토리 작성</button>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>
                <c:if test="${!editMode}">
                    <!-- 루트 장면 -->
                    <div class="scene" data-scene-num="1" data-parent-scene-num="0">
                        <h2>장면 1</h2>
                        <label for="sceneText_1">스토리 내용:</label>
                        <textarea id="sceneText_1" name="sceneText_1" required></textarea>

                        <label for="sceneImage_1">삽화 이미지:</label>
                        <input type="file" id="sceneImage_1" name="sceneImage_1" accept="image/*" onchange="previewImage(this, 'sceneImagePreview_1')">
                        <img id="sceneImagePreview_1" src="#" alt="이미지 미리보기" style="display:none; width:200px; height:200px; object-fit:cover;">

                        <!-- 선택지 추가 버튼 -->
                        <button type="button" class="add-choice-btn" onclick="addChoice(1)">선택지 추가</button>

                        <!-- 선택지 목록 -->
                        <div class="choices" id="choices_1">
                            <!-- 선택지가 추가되는 영역 -->
                        </div>
                    </div>
                </c:if>
            </div>

            <!-- 저장 및 제출 버튼 -->
            <div class="form-actions">
                <button type="button" onclick="saveScene()">저장</button>
                <button type="button" onclick="resetForm()">초기화</button>
                <c:choose>
                    <c:when test="${editMode}">
                        <button type="button" onclick="submitStory(true)">수정 완료</button>
                    </c:when>
                    <c:otherwise>
                        <button type="button" onclick="submitStory(false)">제출하기</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </form>
    </div>
</body>
</html>
