// 페이지 로드 시 실행
window.onload = function() {
    const nicknameElement = document.getElementById('nickname');
    const urlParams = new URLSearchParams(window.location.search);
    const tokenFromURL = urlParams.get('token'); // URL에서 토큰을 추출
    let storedToken = localStorage.getItem('token'); // localStorage에서 토큰 확인

    if (tokenFromURL && tokenFromURL !== "undefined") {
        // URL에서 가져온 토큰이 있으면 localStorage에 저장
        localStorage.setItem('token', tokenFromURL);
        storedToken = tokenFromURL; // 저장한 토큰을 다시 storedToken 변수에 설정
    }

    // 로그인 확인 로직
    if (storedToken) {
        fetch(`${contextPath}/api/check-login`, {
            method: 'GET',
            headers: {
                'Authorization': 'Bearer ' + storedToken
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

                nicknameElement.value = data.nickname;
                nicknameElement.style.display = 'inline-block';

                // storedUsername 변수에 사용자 이름 저장
                window.storedUsername = data.username;

                // 사용자 스토리 버튼 활성화
                enableUserStoryButton();
            }
        })
        .catch(error => {
            if (error.message === 'Unauthorized') {
                alert('토큰이 만료되었습니다. 다시 로그인 해주세요.');
                localStorage.removeItem('token'); // 만료된 토큰 제거
                window.location.href = `${contextPath}/login`; // 로그인 페이지로 이동
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
};

// 카카오 로그인 함수
function kakaoLogin() {
    Kakao.Auth.authorize({
        redirectUri: 'https://0b98-123-142-55-115.ngrok-free.app/StoryCraft/callback'  // 새로운 ngrok URL로 수정
    });
}

// 로그아웃 함수
function logout() {
    localStorage.removeItem('token');
    window.location.href = `${contextPath}/main`;
}

// 설정 모달 띄우기
function showSettingsModal() {
    document.getElementById('settingsModal').style.display = 'block';
}

// 설정 모달 닫기
function closeSettingsModal() {
    document.getElementById('settingsModal').style.display = 'none';
}

// 프로필 이미지 업로드 처리
const profileUpload = document.getElementById('profile-upload');
const profileImagePlaceholder = document.getElementById('profileImagePlaceholder');

if (profileImagePlaceholder && profileUpload) {
    profileImagePlaceholder.addEventListener('click', function() {
        profileUpload.click(); // 파일 선택 창 열기
    });

    profileUpload.addEventListener('change', function() {
        const file = this.files[0];
        if (file) {
            const formData = new FormData();
            formData.append('profileImage', file);

            const token = localStorage.getItem('token');

            // username 파라미터 추가
            formData.append('username', window.storedUsername);

            fetch(`${contextPath}/api/upload-profile`, {
                method: 'POST',
                headers: {
                    'Authorization': 'Bearer ' + token
                },
                body: formData
            })
            .then(response => {
                if (!response.ok) {
                    return response.text().then(text => { throw new Error(text); });
                }
                return response.json();
            })
            .then(data => {
                if (data.success === "true") {
                    alert('이미지가 성공적으로 업로드되었습니다.');

                    // 서버에서 받은 이미지 URL로 프로필 이미지 업데이트
                    const profileImageElement = document.getElementById('profileImage');
                    if (profileImageElement && data.profileImageUrl) {
                        // 캐시 방지 파라미터 추가
                        const updatedImageUrl = `${data.profileImageUrl}?t=${new Date().getTime()}`;
                        profileImageElement.src = updatedImageUrl;
                    }

                    // 프로필 이미지 placeholder도 업데이트
                    profileImagePlaceholder.style.backgroundImage = `url(${data.profileImageUrl})`;
                    profileImagePlaceholder.style.backgroundSize = 'cover';
                    profileImagePlaceholder.style.backgroundPosition = 'center';
                    profileImagePlaceholder.classList.add('image-uploaded');
                    
                    // 텍스트 숨기기
                    const span = profileImagePlaceholder.querySelector('span');
                    if (span) {
                        span.style.display = 'none';
                    }
                } else {
                    alert('이미지 업로드에 실패했습니다.');
                }
            })
            .catch(error => console.error('프로필 이미지 업로드 오류:', error));
        }
    });
}

// 프로필 업데이트 처리 함수
document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('profile-confirm-btn').addEventListener('click', function() {
        updateProfile();
    });

    function updateProfile() {
        const nickname = document.getElementById('nickname').value.trim();
        const gender = document.getElementById('gender').value;
        const birthdayInput = document.getElementById('birthday').value;
        const intro = document.getElementById('intro').value.trim();

        const token = localStorage.getItem('token');

        // 날짜 형식 변환
        let birthday = null;
        if (birthdayInput) {
            const date = new Date(birthdayInput);
            // 'yyyy-MM-dd HH:mm:ss' 형식으로 변환
            birthday = date.getFullYear() + '-' +
                       ('0' + (date.getMonth() + 1)).slice(-2) + '-' +
                       ('0' + date.getDate()).slice(-2) + ' ' +
                       '00:00:00';
        }

        if (nickname && gender && birthday && intro) {
            fetch(`${contextPath}/api/update-profile`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + token
                },
                body: JSON.stringify({
                    username: window.storedUsername,
                    nickname: nickname,
                    gender: gender,
                    birthday: birthday,
                    intro: intro
                })
            })
            .then(response => {
                if (!response.ok) {
                    return response.text().then(text => { throw new Error(text); });
                }
                alert('프로필이 성공적으로 업데이트되었습니다.');
                window.location.reload(); // 페이지 새로고침하여 업데이트 내용 반영
            })
            .catch(error => {
                console.error('프로필 업데이트 중 오류 발생:', error);
                alert('프로필 업데이트에 실패했습니다. 다시 시도해주세요.');
            });
        } else {
            alert('모든 필드를 채워주세요.');
        }
    }
});

// 사용자 스토리 버튼 활성화 함수
function enableUserStoryButton() {
    const userStoryButton = document.getElementById('userStoryButton');
    if (userStoryButton) {
        userStoryButton.disabled = false;
        userStoryButton.addEventListener('click', function() {
            window.location.href = `${contextPath}/userStory`;
        });
    }
}
