let sceneCounter = 1; // 장면 번호를 관리하는 변수

const choiceNames = {};     // 선택지 이름을 저장하는 객체
const choiceContents = {};  // 선택지 내용을 저장하는 객체
const nextSceneNums = {};   // 선택지의 다음 장면 번호를 저장하는 객체

// 이미지 미리보기 함수
function previewImage(input, previewId) {
    const preview = document.getElementById(previewId);
    if (input.files && input.files[0]) {
        const reader = new FileReader();
        reader.onload = function(e) {
            preview.src = e.target.result;
            preview.style.display = 'block';
        };
        reader.readAsDataURL(input.files[0]);
    } else {
        preview.src = '#';
        preview.style.display = 'none';
    }
}

// 선택지 추가 함수 (돈과 체력 필드 제거됨)
function addChoice(sceneNum) {
    const choicesDiv = document.getElementById(`choices_${sceneNum}`);
    const currentChoices = choicesDiv.childElementCount;
    if (currentChoices >= 3) {
        alert('선택지는 최대 3개까지 추가할 수 있습니다.');
        return;
    }

    const choiceNum = currentChoices + 1;

    const choiceDiv = document.createElement('div');
    choiceDiv.classList.add('choice');
    choiceDiv.setAttribute('data-choice-num', choiceNum);

    choiceDiv.innerHTML = `
        <h3>선택지 ${choiceNum}</h3>
        <label>선택지 이름:</label>
        <input type="text" name="choiceName_scene_${sceneNum}_choice_${choiceNum}" required>
        
        <label>선택지 내용:</label>
        <textarea name="choiceContent_scene_${sceneNum}_choice_${choiceNum}" required></textarea>
        
        <!-- 선택지 저장 버튼 -->
        <button type="button" onclick="saveChoice(${sceneNum}, ${choiceNum})">선택지 저장</button>

        <!-- 선택지에 따른 스토리 작성 버튼 -->
        <button type="button" id="createSceneBtn_${sceneNum}_${choiceNum}" style="display: none;" onclick="createScene(${sceneNum}, ${choiceNum})">선택지에 따른 스토리 작성</button>
    `;

    choicesDiv.appendChild(choiceDiv);
}

// 선택지 저장 함수
function saveChoice(sceneNum, choiceNum) {
    const choiceNameInput = document.querySelector(`input[name='choiceName_scene_${sceneNum}_choice_${choiceNum}']`);
    const choiceContentInput = document.querySelector(`textarea[name='choiceContent_scene_${sceneNum}_choice_${choiceNum}']`);

    const choiceName = choiceNameInput.value.trim();
    const choiceContent = choiceContentInput.value.trim();

    if (choiceName === '') {
        alert('선택지 이름을 입력하세요.');
        return;
    }
    if (choiceContent === '') {
        alert('선택지 내용을 입력하세요.');
        return;
    }

    // 선택지에 따른 스토리 작성 버튼 활성화
    const createSceneBtn = document.getElementById(`createSceneBtn_${sceneNum}_${choiceNum}`);
    createSceneBtn.style.display = 'inline-block';
    createSceneBtn.textContent = `${choiceName} 선택지에 따른 스토리 작성`;

    // 선택지 이름과 내용을 저장
    const choiceKey = `scene_${sceneNum}_choice_${choiceNum}`;
    choiceNames[choiceKey] = choiceName;
    choiceContents[choiceKey] = choiceContent;

    // 다음 장면 번호는 아직 생성되지 않았으므로 임시로 -1로 설정
    nextSceneNums[choiceKey] = nextSceneNums[choiceKey] || -1;

    alert('선택지가 저장되었습니다.');
}

// 새로운 장면 생성 함수
function createScene(parentSceneNum, choiceNum) {
    sceneCounter++;

    const sceneEditor = document.getElementById('sceneEditor');

    // 선택지 이름 가져오기
    const choiceKey = `scene_${parentSceneNum}_choice_${choiceNum}`;
    const choiceName = choiceNames[choiceKey] || '';

    const newSceneDiv = document.createElement('div');
    newSceneDiv.classList.add('scene');
    newSceneDiv.setAttribute('data-scene-num', sceneCounter);
    newSceneDiv.setAttribute('data-parent-scene-num', parentSceneNum);

    // 부모 장면 번호를 폼 데이터에 포함하기 위한 hidden input 추가
    newSceneDiv.innerHTML = `
        <input type="hidden" name="parentSceneNum_${sceneCounter}" value="${parentSceneNum}">
        <h2>장면 ${sceneCounter} (이전 선택지: ${choiceName})</h2>
        <label for="sceneText_${sceneCounter}">스토리 내용:</label>
        <textarea id="sceneText_${sceneCounter}" name="sceneText_${sceneCounter}" required></textarea>

        <label for="sceneImage_${sceneCounter}">삽화 이미지:</label>
        <input type="file" id="sceneImage_${sceneCounter}" name="sceneImage_${sceneCounter}" accept="image/*" onchange="previewImage(this, 'sceneImagePreview_${sceneCounter}')">
        <img id="sceneImagePreview_${sceneCounter}" src="#" alt="이미지 미리보기" style="display:none; width:200px; height:200px; object-fit:cover;">

        <!-- 선택지 추가 버튼 -->
        <button type="button" class="add-choice-btn" onclick="addChoice(${sceneCounter})">선택지 추가</button>

        <!-- 선택지 목록 -->
        <div class="choices" id="choices_${sceneCounter}">
            <!-- 선택지가 추가되는 영역 -->
        </div>
    `;

    // 장면을 폼 요소 내에 추가
    sceneEditor.appendChild(newSceneDiv);

    // 선택지와 연결된 다음 장면 번호를 현재 장면 번호로 설정
    nextSceneNums[choiceKey] = sceneCounter;
}

// 저장 버튼 클릭 시 호출되는 함수
function saveScene() {
    alert('스토리가 임시 저장되었습니다.');
    // 실제로는 임시 저장 로직을 구현해야 합니다.
}

// 초기화 버튼 클릭 시 호출되는 함수
function resetForm() {
    if (confirm('모든 내용을 초기화하시겠습니까?')) {
        document.getElementById('storyForm').reset();
        // 추가된 장면 및 선택지 초기화
        const sceneEditor = document.getElementById('sceneEditor');
        sceneEditor.innerHTML = '';
        sceneCounter = 1;
        // 선택지 데이터 초기화
        Object.keys(choiceNames).forEach(key => delete choiceNames[key]);
        Object.keys(choiceContents).forEach(key => delete choiceContents[key]);
        Object.keys(nextSceneNums).forEach(key => delete nextSceneNums[key]);
        // 루트 장면 다시 추가
        sceneEditor.innerHTML = `
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
        `;
    }
}

// 제출하기 버튼 클릭 시 호출되는 함수
function submitStory(isEdit) {
    if (confirm(isEdit ? '스토리를 수정하시겠습니까?' : '스토리를 제출하시겠습니까?')) {
        // 폼 데이터를 서버로 전송
        const form = document.getElementById('storyForm');
        const formData = new FormData(form);

        // 선택지 데이터 폼 데이터에 추가
        for (const key in choiceNames) {
            formData.append(`choiceName_${key}`, choiceNames[key]);
            formData.append(`choiceContent_${key}`, choiceContents[key]);
            
            // 돈과 체력 관련 필드 제거
            // formData.append(`choiceMoney_${key}`, choiceMoney[key] || '0');
            // formData.append(`choiceHP_${key}`, choiceHP[key] || '0');
            
            formData.append(`nextSceneNum_${key}`, nextSceneNums[key] || '-1'); // nextSceneNum 기본값을 '-1'로 설정
        }

        // 요청 URL을 항상 '/story/save'로 설정
        const url = `${contextPath}story/save`;

        fetch(url, {
            method: 'POST',
            body: formData
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('네트워크 응답이 올바르지 않습니다. 상태 코드: ' + response.status);
            }
            return response.json();
        })
        .then(data => {
            if (data.success) {
                alert(isEdit ? '수정이 완료되었습니다.' : '제출이 완료되었습니다.');
                window.location.href = `${contextPath}story/list`;
            } else {
                alert(data.message || (isEdit ? '스토리 수정에 실패했습니다.' : '스토리 제출에 실패했습니다.'));
            }
        })
        .catch(error => {
            console.error('스토리 제출 중 오류 발생:', error);
            alert(`스토리 ${isEdit ? '수정' : '제출'} 중 오류가 발생했습니다. (${error.message})`);
        });
    }
}
