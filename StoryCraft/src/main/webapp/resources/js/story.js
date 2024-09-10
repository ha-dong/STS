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
    const choiceContainer = document.querySelector('.additional-content');
    
    // 새로운 선택지 요소를 만듭니다.
    const newChoice = document.createElement('div');
    newChoice.className = 'choice';
    
    // 새로운 textarea를 추가합니다.
    newChoice.innerHTML = '<textarea placeholder="새로운 선택지"></textarea>';
    
    // 새로운 선택지를 선택지 추가 버튼 위에 삽입합니다.
    choiceContainer.insertBefore(newChoice, this);
});

// 초기화 버튼 기능
document.querySelector('.reset-button').addEventListener('click', function(event) {
    event.preventDefault();  // 기본 초기화 동작을 막습니다.

    const confirmReset = confirm('모든 내용을 초기화하시겠습니까?');
    if (confirmReset) {
        const form = document.getElementById('storyForm');
        form.reset();  // 폼 내용을 초기화합니다.

        // 기본 두 개를 제외한 추가된 선택지 요소와 "선택지에 따른 스토리 작성" 버튼을 모두 삭제합니다.
        const choices = document.querySelectorAll('.additional-content .choice');
        choices.forEach((choice, index) => {
            if (index > 1) {  // 기본 두 개의 선택지를 남겨둡니다.
                choice.remove();
            }
        });

        // 동적으로 추가된 "선택지에 따른 스토리 작성" 버튼도 모두 삭제
        const storyButtons = document.querySelectorAll('.story-buttons');
        storyButtons.forEach(button => {
            button.remove();
        });

        // 이미지 미리보기 숨기기
        const preview = document.getElementById('preview');
        preview.src = '';
        preview.style.display = 'none';

        document.getElementById('upload-text').style.display = 'block';
    }
});

// 저장 버튼 기능
document.querySelector('.save-button').addEventListener('click', function(event) {
    event.preventDefault();  // 폼 제출 방지
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
        storyButton.addEventListener('click', function() {
            location.href = '/StoryCraft/storyadd';  // storyadd.jsp로 이동
        });

        // 선택지 아래에 버튼 추가
        choice.appendChild(storyButton);
    });
   
});

// 제출 버튼 기능 수정
document.querySelector('.submit-button').addEventListener('click', function(event) {
    event.preventDefault();  // 기본 폼 제출을 방지

    const confirmSubmit = confirm('내용을 제출하시겠습니까?');
    if (confirmSubmit) {
        // 폼 데이터를 전송하고 storylist.jsp로 이동
        const form = document.getElementById('storyForm');
        form.submit();  // 폼 제출을 트리거합니다.
        location.href = '/StoryCraft/storylist';  // storylist.jsp로 이동
    } else {
        alert('제출이 취소되었습니다.');
    }
});
