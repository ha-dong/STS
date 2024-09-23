document.addEventListener('DOMContentLoaded', function () {
    // 이미지 미리보기 기능
    document.getElementById('file-input').addEventListener('change', function (event) {
        const file = event.target.files[0];
        const reader = new FileReader();

        reader.onload = function (e) {
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

    // 선택지 추가 기능 (최대 3개의 선택지까지 추가 가능)
    let choiceCount = 1;  // 기본으로 하나의 선택지 제공
    document.getElementById('add-choice').addEventListener('click', function () {
        if (choiceCount < 3) {
            choiceCount++;
            const choiceContainer = document.querySelector('.additional-content');

            // 새로운 선택지 요소를 생성
            const newChoice = document.createElement('div');
            newChoice.className = 'choice';

            // 새로운 textarea와 돈/체력 입력칸 추가
            newChoice.innerHTML = `
                <input type="text" name="choice${choiceCount}-name" maxlength="5" placeholder="선택지 이름 (최대 5글자)" required>
                <textarea name="choice${choiceCount}" placeholder="선택지 ${choiceCount}" required></textarea>
                <label for="choice${choiceCount}-money">돈 변화:</label>
                <input type="number" name="choice${choiceCount}-money" min="-50" max="50" value="0">
                <label for="choice${choiceCount}-hp">체력 변화:</label>
                <input type="number" name="choice${choiceCount}-hp" min="-100" max="100" value="0">
            `;

            // 새로운 선택지를 버튼 위에 삽입
            choiceContainer.insertBefore(newChoice, this);

            // 드롭다운에도 새로운 선택지 추가
            const dropdown = document.getElementById('choices-dropdown');
            const option = document.createElement('option');
            option.value = `choice${choiceCount}`;
            option.text = `선택지 ${choiceCount}`;
            dropdown.add(option);
        } else {
            alert('선택지는 최대 3개까지만 추가할 수 있습니다.');
        }
    });

    // 선택지 제거 기능 (최소 1개의 선택지는 남겨야 함)
    document.getElementById('remove-choice').addEventListener('click', function () {
        if (choiceCount > 1) {
            const choiceContainer = document.querySelector('.additional-content');
            choiceContainer.removeChild(choiceContainer.children[choiceCount - 1]);

            // 드롭다운에서 선택지 제거
            const dropdown = document.getElementById('choices-dropdown');
            dropdown.remove(choiceCount);

            choiceCount--;
        } else {
            alert('선택지는 최소 1개 이상 있어야 합니다.');
        }
    });

    // 저장 버튼 기능
    document.querySelector('.save-button').addEventListener('click', function (event) {
        event.preventDefault();  // 기본 폼 제출 방지
        alert('스토리가 저장되었습니다.');

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
                // storyadd.jsp로 이동
                location.href = `/storyadd?choice=${index + 1}`;
            });

            // 선택지 아래에 버튼 추가
            choice.appendChild(storyButton);
        });
    });

    // 제출 버튼 기능
    document.querySelector('.submit-button').addEventListener('click', function (event) {
        event.preventDefault();  // 기본 폼 제출 방지

        const confirmSubmit = confirm('내용을 제출하시겠습니까?');
        if (confirmSubmit) {
            // 폼 데이터를 전송하고 storylist.jsp로 이동
            const form = document.getElementById('storyForm');
            form.submit();  // 폼 제출 트리거
        } else {
            alert('제출이 취소되었습니다.');
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
});
