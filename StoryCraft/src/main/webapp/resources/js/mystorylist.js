window.onload = function() {
    const nicknameElement = document.getElementById('nickname');
    const urlParams = new URLSearchParams(window.location.search);
    const tokenFromURL = urlParams.get('token'); // URL에서 토큰을 추출
    let storedToken = localStorage.getItem('token'); // localStorage에서 토큰 확인

    if (tokenFromURL) {
        // URL에서 가져온 토큰이 있으면 localStorage에 저장
        localStorage.setItem('token', tokenFromURL);
        storedToken = tokenFromURL; // 저장한 토큰을 다시 storedToken 변수에 설정
        console.log('Token saved to Local Storage:', tokenFromURL);
    } else if (storedToken) {
        console.log('Token already exists in Local Storage:', storedToken);
    } else {
        console.log('No token found in URL or Local Storage');
    }

    // 로그인 확인 로직
    if (storedToken) {
        fetch('/StoryCraft/api/check-login', {
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

                nicknameElement.textContent = data.nickname;
                nicknameElement.style.display = 'inline-block';

                // 사용자 스토리 버튼 활성화
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
    window.location.href = '/StoryCraft/main';
}

// 설정 모달 띄우기
function showSettingsModal() {
    document.getElementById('settingsModal').style.display = 'block';
}

// 설정 모달 닫기
function closeSettingsModal() {
    document.getElementById('settingsModal').style.display = 'none';
}

// 장르 필터링 기능
document.addEventListener('DOMContentLoaded', function() {
    const genreTabs = document.querySelectorAll('.genre-tab');
    const storyItems = document.querySelectorAll('.story-item');

    genreTabs.forEach(tab => {
        tab.addEventListener('click', function() {
            genreTabs.forEach(t => t.classList.remove('active'));
            this.classList.add('active');

            const genre = this.textContent;

            storyItems.forEach(item => {
                const itemGenre = item.querySelector('.story-info p:nth-child(2)').textContent.split(': ')[1];

                if (genre === '전체' || genre === itemGenre) {
                    item.style.display = 'block';
                } else {
                    item.style.display = 'none';
                }
            });
        });
    });
});

// 정렬 기능
const sortDropdown = document.querySelector('.sort-dropdown select');
sortDropdown.addEventListener('change', function() {
    const sortType = this.value;
    const storyItems = document.querySelectorAll('.story-item');
    const sortedItems = Array.from(storyItems).sort((a, b) => {
        const likeA = parseInt(a.querySelector('.story-like span').textContent, 10);
        const likeB = parseInt(b.querySelector('.story-like span').textContent, 10);

        if (sortType === '인기순') {
            return likeB - likeA;
        } else if (sortType === '최신순') {
            return likeB - likeA;
        }
    });

    const storyList = document.querySelector('.story-list');
    storyList.innerHTML = '';
    sortedItems.forEach(item => storyList.appendChild(item));
});
