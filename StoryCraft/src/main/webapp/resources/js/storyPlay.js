// storyPlay.js

// 선택지에 따라 다음 장면을 불러오는 함수
function selectChoice(choiceNum) {
    // 선택한 선택지에 따라 서버에서 다음 장면을 가져옴
    fetch(`${contextPath}story/nextScene`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ choiceNum: choiceNum })  // choiceNum을 전송
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            // 장면 이미지 및 텍스트 업데이트
            const sceneImage = document.getElementById('sceneImage');
            const sceneText = document.getElementById('sceneText');
            const choicesDiv = document.getElementById('choices');

            // 이미지가 있으면 업데이트, 없으면 숨김
            if (data.scene.scIllus) {
                if (!sceneImage) {
                    // 이미지 태그가 없으면 생성
                    const newImage = document.createElement('img');
                    newImage.id = 'sceneImage';
                    newImage.classList.add('card-img-top');
                    document.getElementById('storyDetails').appendChild(newImage);
                }
                document.getElementById('sceneImage').src = contextPath + 'uploads/' + data.scene.scIllus;
                document.getElementById('sceneImage').style.display = 'block';
            } else {
                if (sceneImage) {
                    sceneImage.style.display = 'none';
                }
            }

            // 텍스트 업데이트
            sceneText.innerHTML = '<p>' + data.scene.scText + '</p>';

            // 선택지 버튼들 업데이트
            choicesDiv.innerHTML = '';  // 기존 선택지 제거
            data.choices.forEach(choice => {
                const btn = document.createElement('button');
                btn.classList.add('choice-btn');
                btn.innerHTML = choice.choiceName;
                btn.setAttribute('data-choice-num', choice.choiceNum); // data-choice-num 설정
                btn.onclick = function() { selectChoice(choice.choiceNum); };  // choiceNum을 사용하여 다음 장면 요청
                choicesDiv.appendChild(btn);
            });
        } else {
            alert(data.message || '다음 장면을 불러오는 중 오류가 발생했습니다.');
        }
    })
    .catch(error => console.error('Error:', error));
}

// 문서 로드 시 이벤트 바인딩
document.addEventListener("DOMContentLoaded", function () {
    const choiceButtons = document.querySelectorAll('.choice-btn');
    
    // 선택지 클릭 이벤트 설정
    choiceButtons.forEach(button => {
        button.addEventListener('click', function () {
            const choiceNum = this.getAttribute('data-choice-num');
            selectChoice(choiceNum);
        });
    });
});
