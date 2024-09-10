document.addEventListener('DOMContentLoaded', function () {
    const token = localStorage.getItem('token');
    const nicknameElement = document.getElementById('nickname');

    // 로그인 확인 로직
    if (token) {
        fetch('/StoryCraft/api/check-login', {
            method: 'GET',
            headers: {
                'Authorization': 'Bearer ' + token
            }
        })
        .then(response => {
            if (!response.ok && response.status === 401) {
                throw new Error('Unauthorized');
            }
            return response.json();
        })
        .then(data => {
            if (data.loggedIn) {
                document.getElementById('loginButton').style.display = 'none';
                document.getElementById('signupButton').style.display = 'none';
                document.getElementById('logoutButton').style.display = 'inline-block';

                nicknameElement.textContent = data.nickname;
                nicknameElement.style.display = 'inline-block';

                // 항상 사용자 스토리 버튼을 활성화
                enableUserStoryButton();
            }
        })
        .catch(error => {
            if (error.message === 'Unauthorized') {
                alert('토큰이 만료되었습니다. 다시 로그인 해주세요.');
                localStorage.removeItem('token'); // 만료된 토큰 제거
                window.location.href = '/StoryCraft/login'; // 로그인 페이지로 이동
            } else {
                console.error('로그인 상태 확인 중 오류:', error);
            }
        });
    } else {
        document.getElementById('logoutButton').style.display = 'none';
        nicknameElement.style.display = 'none';

        // 로그인 여부와 관계없이 사용자 스토리 버튼을 항상 활성화
        enableUserStoryButton();
    }
});

// 로그아웃 함수
function logout() {
    localStorage.removeItem('token');
    window.location.href = '/StoryCraft/main';
}

// 메인 스토리 시작 함수
function startMainStory() {
    const token = localStorage.getItem('token');

    if (!token) {
        alert('로그인을 먼저 진행해주세요.');
        window.location.href = '/StoryCraft/login';
        return;
    }

    fetch('/StoryCraft/api/check-main-story-completed', {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${token}`
        }
    })
    .then(response => response.json())
    .then(data => {
        if (data.mainStoryCompleted) {
            window.location.href = '/StoryCraft/mainStory'; // MAIN_COMPLETE가 Y인 경우 바로 이동
        } else {
            const skipConfirm = confirm('메인 스토리를 스킵하시겠습니까?');
            if (skipConfirm) {
                // 메인 스토리 스킵 API 호출하여 MAIN_COMPLETE를 Y로 설정
                fetch('/StoryCraft/api/skip-main-story', {
                    method: 'POST',
                    headers: {
                        'Authorization': `Bearer ${token}`
                    }
                })
                .then(response => {
                    if (response.ok) {
                        alert('사용자 스토리가 해금되었습니다.');
                        enableUserStoryButton();  // 사용자 스토리 버튼 활성화
                    }
                })
                .catch(error => {
                    console.error('메인 스토리 스킵 중 오류:', error);
                });
            } else {
                window.location.href = '/StoryCraft/mainStory'; // 메인 스토리 페이지로 이동
            }
        }
    })
    .catch(error => {
        console.error('메인 스토리 상태 확인 중 오류:', error);
    });
}

// 사용자 스토리 확인 함수
function checkUserStoryUnlocked() {
    const token = localStorage.getItem('token');

    if (!token) {
        alert('로그인을 먼저 진행해주세요.');
        window.location.href = '/StoryCraft/login';
        return;
    }

    fetch('/StoryCraft/api/check-user-story-unlocked', {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${token}`
        }
    })
    .then(response => response.json())
    .then(data => {
        if (!data.userStoryUnlocked) {
            alert('메인 스토리를 먼저 클리어해 주세요.');
        } else {
            openUserStoryModal();
        }
    })
    .catch(error => {
        console.error('사용자 스토리 해금 여부 확인 중 오류:', error);
    });
}

// 사용자 스토리 버튼 활성화 함수
function enableUserStoryButton() {
    const userStoryButton = document.getElementById('userStory');
    userStoryButton.style.opacity = '1'; // 버튼을 보이게
    userStoryButton.style.backgroundColor = '#4CAF50';  // 사용자 스토리 활성화 상태 색상
    userStoryButton.style.pointerEvents = 'auto';  // 클릭 가능 상태로 설정
    userStoryButton.disabled = false;  // 버튼을 활성화
}

// 사용자 스토리 모달 열기
function openUserStoryModal() {
    const modal = document.createElement('div');
    modal.className = 'modal';
    modal.innerHTML = `
        <div class="modal-content">
            <span class="close" onclick="closeModal(this)">&times;</span>
            <button onclick="location.href='/StoryCraft/story'">스토리 제작</button>
            <button onclick="location.href='/StoryCraft/storylist'">스토리 목록</button>
        </div>
    `;
    document.body.appendChild(modal);
    modal.style.display = "block";
}

// 모달 닫기 함수
function closeModal(element) {
    const modal = element.closest('.modal');
    modal.style.display = "none";
}

// 설정 모달 열기
function openSettingsModal() {
    const modal = document.getElementById('settingsModal');
    modal.style.display = 'block';
}

// 설정 모달 닫기
function closeSettingsModal() {
    const modal = document.getElementById('settingsModal');
    modal.style.display = 'none';
}

// 슬라이더 비활성화/활성화
function toggleMute(sliderId) {
    const slider = document.getElementById(sliderId);
    const muteCheckbox = document.getElementById(sliderId === 'bgmSlider' ? 'muteBGM' : 'muteSFX');
    
    if (muteCheckbox.checked) {
        slider.disabled = true;  // 슬라이더 비활성화
    } else {
        slider.disabled = false;  // 슬라이더 활성화
    }
}

// 탈퇴 확인 모달 열기
function openDeleteAccountModal() {
    const modal = document.getElementById('deleteAccountModal');
    modal.style.display = 'block';
}

// 탈퇴 확인 모달 닫기
function closeDeleteAccountModal() {
    const modal = document.getElementById('deleteAccountModal');
    modal.style.display = 'none';
}

// 탈퇴 확인 후 계정 삭제 처리
function confirmDeleteAccount() {
    fetch('/StoryCraft/api/delete-account', { method: 'POST' })
        .then(response => {
            if (response.ok) {
                alert('계정이 탈퇴되었습니다.');
                localStorage.removeItem('token');  // 로그아웃
                window.location.href = '/StoryCraft/login';  // 로그인 페이지로 이동
            }
        })
        .catch(error => console.error('탈퇴 처리 중 오류 발생:', error));
}
