// storyPlay.js

let stNum = window.stNum || 0;
let currentSceneNum = ${scene.scNum};
let money = 0;
let hp = 100;

// 선택지를 선택했을 때 호출되는 함수
function selectChoice(nextSceneNum) {
    // 서버로부터 다음 장면 정보를 가져옵니다.
    fetch(`${contextPath}story/getScene?stNum=${stNum}&scNum=${nextSceneNum}`)
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                updateScene(data.scene);
            } else {
                alert('다음 장면을 불러오는 데 실패했습니다.');
            }
        })
        .catch(error => {
            console.error('에러 발생:', error);
            alert('에러가 발생했습니다.');
        });
}

// 현재 장면을 업데이트하는 함수
function updateScene(scene) {
    // 장면 텍스트와 삽화 업데이트
    document.querySelector('.scene p').textContent = scene.scText;
    if (scene.scIllus) {
        const sceneImage = document.querySelector('.scene-image');
        sceneImage.src = `${contextPath}uploads/${scene.scIllus}`;
        sceneImage.style.display = 'block';
    } else {
        document.querySelector('.scene-image').style.display = 'none';
    }

    // 선택지 업데이트
    const choicesDiv = document.querySelector('.choices');
    choicesDiv.innerHTML = '';
    scene.choices.forEach(choice => {
        const button = document.createElement('button');
        button.textContent = choice.choiceName;
        button.onclick = () => selectChoice(choice.nextScNum);
        choicesDiv.appendChild(button);
    });

    // 현재 장면 번호 업데이트
    currentSceneNum = scene.scNum;
}

// 페이지 로드 시 돈과 체력 표시 업데이트
document.getElementById('money').textContent = money;
document.getElementById('hp').textContent = hp;
