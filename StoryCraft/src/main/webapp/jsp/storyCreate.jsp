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
    
    <!-- 컨텍스트 경로를 JavaScript 변수로 설정 -->
    <script>
        let contextPath = '<c:url value="/" />';
        <c:if test="${not empty editMode}">
            let editMode = true;
        </c:if>
        <c:if test="${empty editMode}">
            let editMode = false;
        </c:if>
        <c:if test="${not empty story}">
            let stNum = ${story.stNum};
        </c:if>
        <c:if test="${empty story}">
            let stNum = null;
        </c:if>

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
    <div class="header">
        <a href="${contextPath}/StoryCraft/main">
            <img src="${pageContext.request.contextPath}/resources/img/logo.png" alt="로고" class="logo" id="logo">
        </a>
        <!-- 테마 변경 버튼 추가 -->
        <button id="themeButton" onclick="changeTheme()" style="margin-left: 20px; padding: 10px; cursor: pointer;">
            테마 변경
        </button>
        <!-- 로그인 상태와 관계없이 헤더 간소화 -->
    </div>
    <!-- 헤더 부분 끝 -->

    <div class="container">
        <!-- 페이지 제목 설정 -->
        <c:set var="pageTitle" value="스토리 제작" />
        <c:if test="${editMode}">
            <c:set var="pageTitle" value="스토리 수정" />
        </c:if>
        <h1>${pageTitle}</h1>
        
        <div class="login-box">
            <c:choose>
                <c:when test="${not empty sessionScope.user}">
                    <span>${sessionScope.user}님</span> <!-- 사용자 이름 표시 -->
                </c:when>
                <c:otherwise>
                    <button class="login-button" onclick="location.href='<c:url value='/login'/>'">로그인</button>
                    <button class="login-button" onclick="location.href='<c:url value='/register'/>'">회원가입</button>
                </c:otherwise>
            </c:choose>
        </div>
        
        <form id="storyForm" enctype="multipart/form-data">
            <c:if test="${editMode}">
                <input type="hidden" name="stNum" value="${story.stNum}">
            </c:if>
            <!-- 스토리 기본 정보 -->
            <div class="story-info">
                <label for="title">제목:</label>
                <input type="text" id="title" name="title" required value="<c:out value='${editMode ? story.stTitle : ""}'/>">

                <label for="cover">표지 이미지:</label>
                <input type="file" id="cover" name="cover" accept="image/*" onchange="previewImage(this, 'coverPreview')">
                
                <!-- 이미지 미리보기 설정 -->
                <c:set var="coverPreviewSrc" value="#" />
                <c:set var="coverPreviewDisplay" value="none" />
                <c:if test="${editMode and not empty story.stCover}">
                    <c:set var="coverPreviewSrc" value="/StoryCraft/uploads/${story.stCover}" />
                    <c:set var="coverPreviewDisplay" value="block" />
                </c:if>
                <img id="coverPreview" src="${coverPreviewSrc}" alt="이미지 미리보기" style="display:${coverPreviewDisplay}; width:200px; height:200px; object-fit:cover;">

                <label for="genre">장르:</label>
                <select id="genre" name="genre" required>
                    <option value="">선택하세요</option>
                    <c:forEach var="code" items="${genreList}">
                        <option value="${code.CODE}" <c:if test="${editMode and code.CODE == story.stGenrecode}">selected</c:if>>${code.CODE_NAME}</option>
                    </c:forEach>
                </select>
             
            </div>

            <!-- 스토리 내용 및 선택지 -->
            <div class="scene-editor" id="sceneEditor">
                <c:if test="${editMode}">
                    <c:forEach var="scene" items="${story.scenes}">
                        <div class="scene" data-scene-num="${scene.tempSceneNum}" data-parent-scene-num="${scene.parentScNum}">
                            <h2>장면 ${scene.tempSceneNum}</h2>
                            <label for="sceneText_${scene.tempSceneNum}">스토리 내용:</label>
                            <textarea id="sceneText_${scene.tempSceneNum}" name="sceneText_${scene.tempSceneNum}" required>${scene.scText}</textarea>

                            <label for="sceneImage_${scene.tempSceneNum}">삽화 이미지:</label>
                            <input type="file" id="sceneImage_${scene.tempSceneNum}" name="sceneImage_${scene.tempSceneNum}" accept="image/*" onchange="previewImage(this, 'sceneImagePreview_${scene.tempSceneNum}')">
                            
                            <!-- 장면 이미지 미리보기 설정 -->
                            <c:set var="sceneImageSrc" value="#" />
                            <c:set var="sceneImageDisplay" value="none" />
                            <c:if test="${not empty scene.scIllus}">
                                <c:set var="sceneImageSrc" value="/uploads/${scene.scIllus}" />
                                <c:set var="sceneImageDisplay" value="block" />
                            </c:if>
                            <img id="sceneImagePreview_${scene.tempSceneNum}" src="${sceneImageSrc}" alt="이미지 미리보기" style="display:${sceneImageDisplay}; width:200px; height:200px; object-fit:cover;">

                            <!-- 부모 장면 번호를 폼 데이터에 포함하기 위한 hidden input 추가 -->
                            <input type="hidden" name="parentSceneNum_${scene.tempSceneNum}" value="${scene.parentScNum}">

                            <!-- 선택지 추가 버튼 -->
                            <button type="button" class="add-choice-btn" onclick="addChoice(${scene.tempSceneNum})">선택지 추가</button>

                            <!-- 선택지 목록 -->
                            <div class="choices" id="choices_${scene.tempSceneNum}">
                                <c:forEach var="choice" items="${scene.choices}">
                                    <div class="choice" data-choice-num="${choice.choiceNum}">
                                        <h3>선택지 ${choice.choiceNum}</h3>
                                        <label>선택지 이름:</label>
                                        <input type="text" name="choiceName_scene_${scene.tempSceneNum}_choice_${choice.choiceNum}" required value="${choice.choiceName}">
                                        
                                        <label>선택지 내용:</label>
                                        <textarea name="choiceContent_scene_${scene.tempSceneNum}_choice_${choice.choiceNum}" required>${choice.choiceContent}</textarea>

                                        <!-- 선택지 돈 변화량 및 체력 변화량 필드 제거 -->

                                        <!-- 선택지 저장 버튼 -->
                                        <button type="button" onclick="saveChoice(${scene.tempSceneNum}, ${choice.choiceNum})">선택지 저장</button>

                                        <!-- 선택지에 따른 스토리 작성 버튼 -->
                                        <c:set var="createSceneBtnDisplay" value="none" />
                                        <c:if test="${choice.nextScNum != null}">
                                            <c:set var="createSceneBtnDisplay" value="inline-block" />
                                        </c:if>
                                        <button type="button" id="createSceneBtn_${scene.tempSceneNum}_${choice.choiceNum}" style="display: ${createSceneBtnDisplay};" onclick="createScene(${scene.tempSceneNum}, ${choice.choiceNum})">${choice.choiceName} 선택지에 따른 스토리 작성</button>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>
                <c:if test="${not editMode}">
                    <!-- 루트 장면 -->
                    <div class="scene" data-scene-num="1" data-parent-scene-num="0">
                        <h2>장면 1</h2>
                        <label for="sceneText_1">스토리 내용:</label>
                        <textarea id="sceneText_1" name="sceneText_1" required></textarea>

                        <label for="sceneImage_1">삽화 이미지:</label>
                        <input type="file" id="sceneImage_1" name="sceneImage_1" accept="image/*" onchange="previewImage(this, 'sceneImagePreview_1')">
                        <img id="sceneImagePreview_1" src="#" alt="이미지 미리보기" style="display:none; width:200px; height:200px; object-fit:cover;">

                        <!-- 부모 장면 번호를 폼 데이터에 포함하기 위한 hidden input 추가 -->
                        <input type="hidden" name="parentSceneNum_1" value="0">

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
