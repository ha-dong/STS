// storyControl.js

// 사운드 효과 파일 목록
const soundEffectFiles = [
    '/StoryCraft/resources/audio/click.mp3'
];

let soundEffectsVolume = 0.5; // 효과음 기본 볼륨 (슬라이더 초기 값 50에 따라 설정)
let sfxMuted = false; // 효과음 음소거 상태
const EFFECT_VOLUME_SCALE = 5; // 효과음 볼륨 스케일링 팩터

// 오디오 컨텍스트는 사용자 상호작용 후에 생성
let audioContext = null;

// 오디오 버퍼 캐시 생성
const audioBuffers = {}; // 오디오 버퍼 캐시

document.addEventListener("DOMContentLoaded", function () {
    const nicknameElement = document.getElementById('nickname');
    const profileImageElement = document.getElementById('profileImage'); // 'profileImage' 추가
    const urlParams = new URLSearchParams(window.location.search);
    const tokenFromURL = urlParams.get('token'); // URL에서 토큰을 추출
    let storedToken = localStorage.getItem('token'); // localStorage에서 토큰 확인

    const backgroundMusic = document.getElementById('backgroundMusic');
    const bgmSlider = document.getElementById('bgmSlider');
    const sfxSlider = document.getElementById('sfxSlider');

    // '음악 끄기' 체크박스
    const muteBGMCheckbox = document.getElementById('muteBGM');
    const muteSFXCheckbox = document.getElementById('muteSFX');

    // 슬라이더 값으로 초기 볼륨 설정
    if (backgroundMusic && bgmSlider) {
        backgroundMusic.volume = bgmSlider.value / 100;
    }
    if (sfxSlider) {
        soundEffectsVolume = sfxSlider.value / 100;
    }

    // 사용자 상호작용 시 AudioContext 생성 및 배경 음악 재생
    const resumeAudioContext = () => {
        if (!audioContext) {
            audioContext = new (window.AudioContext || window.webkitAudioContext)();
        }
        if (audioContext.state === 'suspended') {
            audioContext.resume().then(() => {
                console.log("AudioContext가 재개되었습니다.");
            }).catch(error => {
                console.error("AudioContext 재개 실패:", error);
            });
        }
        if (backgroundMusic && backgroundMusic.paused) {
            backgroundMusic.play().catch(error => {
                console.log("음악 재생에 실패했습니다:", error);
            });
        }
    };

    document.body.addEventListener('click', resumeAudioContext, { once: true });

    // 사운드 효과 이벤트 리스너 추가
    addSoundEffectListeners();

    // 오디오 파일 미리 로드
    soundEffectFiles.forEach((audioURL) => {
        fetch(audioURL)
            .then(response => response.arrayBuffer())
            .then(arrayBuffer => {
                if (!audioContext) {
                    // AudioContext가 아직 생성되지 않았으면 생성
                    audioContext = new (window.AudioContext || window.webkitAudioContext)();
                }
                return audioContext.decodeAudioData(arrayBuffer);
            })
            .then(audioBuffer => {
                audioBuffers[audioURL] = audioBuffer;
            })
            .catch(error => console.error('오디오 파일 로드 실패:', error));
    });

    if (tokenFromURL) {
        // URL에서 가져온 토큰이 있으면 localStorage에 저장
        localStorage.setItem('token', tokenFromURL);
        storedToken = tokenFromURL; // 저장한 토큰을 다시 storedToken 변수에 설정
    }

    // 로그인 확인 로직
    if (storedToken) {
        fetch('/StoryCraft/api/check-login', {
            method: 'GET',
            credentials: 'same-origin' // 세션 쿠키를 포함시키기 위해 추가
        })
        .then(response => {
            if (response.ok) {
                return response.json();
            } else if (response.status === 401) {
                throw new Error('Unauthorized');
            } else {
                throw new Error('로그인 상태 확인 실패');
            }
        })
        .then(data => {
            if (data.loggedIn) {
                const loginButton = document.getElementById('loginButton');
                const signupButton = document.getElementById('signupButton');
                const logoutButton = document.getElementById('logoutButton');
                const nicknameElement = document.getElementById('nickname'); // 닉네임 요소 ID 확인
                const profileImageElement = document.getElementById('profileImage'); // 프로필 이미지 요소 ID 확인

                if (loginButton) loginButton.style.display = 'none';
                if (signupButton) signupButton.style.display = 'none';
                if (logoutButton) logoutButton.style.display = 'inline-block';

                // 닉네임 업데이트
                if (nicknameElement) {
                    nicknameElement.textContent = data.nickname;
                    nicknameElement.style.display = 'inline-block';
                }

                // 프로필 이미지 업데이트
                if (profileImageElement) {
                    if (data.profileImage) {
                        profileImageElement.src = `${contextPath}${data.profileImage}?t=${new Date().getTime()}`;
                    } else {
                        profileImageElement.src = `${contextPath}/resources/img/default_profile.png`;
                    }
                    profileImageElement.style.display = 'inline-block';
                }

                // 사용자 스토리 버튼 활성화
                enableUserStoryButton();
            } else {
                // 로그인 페이지로 리다이렉트
                window.location.href = '/StoryCraft/login';
            }
        })
        .catch(error => {
            if (error.message === 'Unauthorized') {
                alert('로그인 상태가 아닙니다. 다시 로그인 해주세요.');
                window.location.href = '/StoryCraft/login'; // 로그인 페이지로 이동
            } else {
                console.error('로그인 상태 확인 중 오류:', error);
            }
        });
    } else {
        const logoutButton = document.getElementById('logoutButton');
        if (logoutButton) logoutButton.style.display = 'none';
        if (nicknameElement) nicknameElement.style.display = 'none';
        if (profileImageElement) profileImageElement.style.display = 'none';

        // 사용자 스토리 버튼 활성화
        enableUserStoryButton();
    }

    // '음악 끄기' 체크박스 상태에 따라 초기 볼륨 설정
    if (muteBGMCheckbox && muteBGMCheckbox.checked && backgroundMusic) {
        backgroundMusic.volume = 0;
    } else if (backgroundMusic && bgmSlider) {
        backgroundMusic.volume = bgmSlider.value / 100;
    }

    if (muteSFXCheckbox && muteSFXCheckbox.checked) {
        sfxMuted = true;
    } else {
        sfxMuted = false;
    }

    // 볼륨 슬라이더 이벤트 리스너 추가
    if (bgmSlider) {
        bgmSlider.addEventListener('input', adjustVolume);
    }

    if (sfxSlider) {
        sfxSlider.addEventListener('input', adjustSFXVolume);
    }

    // 음소거 체크박스 이벤트 리스너 추가
    if (muteBGMCheckbox) {
        muteBGMCheckbox.addEventListener('change', function() {
            toggleMute('bgmSlider');
        });
    }

    if (muteSFXCheckbox) {
        muteSFXCheckbox.addEventListener('change', function() {
            toggleMute('sfxSlider');
        });
    }

    // 공지사항 모달 관련 코드
    var noticeModal = document.getElementById("noticeModal");
    var closeNoticeModalButton = document.getElementById("closeNoticeModal");
    var noticeIcon = document.querySelector(".notice-icon");

    // 모달 열기
    if (noticeIcon) {
        noticeIcon.addEventListener("click", function() {
            openNoticeModal();
        });
    }

    // 모달 닫기 버튼
    if (closeNoticeModalButton) {
        closeNoticeModalButton.addEventListener("click", function() {
            closeNoticeModalFunction();
        });
    }

    // 모달 외부 클릭 시 닫기
    window.addEventListener("click", function(event) {
        if (event.target == noticeModal) {
            closeNoticeModalFunction();
        }
    });

    function openNoticeModal() {
        // 공지사항 데이터 가져오기
        fetch(contextPath + '/notice/api/list')
            .then(response => response.json())
            .then(data => {
                populateNoticeTable(data);
                noticeModal.style.display = "block";
            })
            .catch(error => {
                console.error('Error fetching notices:', error);
                alert('공지사항을 불러오는 중 오류가 발생했습니다.');
            });
    }

    function closeNoticeModalFunction() {
        noticeModal.style.display = "none";
        clearNoticeTable();
        // 공지사항 내용 초기화
        document.getElementById('noticeText').textContent = '제목을 클릭하면 내용이 표시됩니다.';
    }

    function populateNoticeTable(notices) {
        var tbody = document.querySelector("#noticeTable tbody");
        tbody.innerHTML = ''; // 기존 내용 삭제

        notices.forEach(function(notice) {
            var tr = document.createElement('tr');

            var tdNum = document.createElement('td');
            tdNum.textContent = notice.ntNum;
            tr.appendChild(tdNum);

            var tdType = document.createElement('td');
            switch(notice.ntTypeCode) {
                case 'CN-01':
                    tdType.textContent = '업데이트';
                    break;
                case 'CN-02':
                    tdType.textContent = '일반';
                    break;
                case 'CN-03':
                    tdType.textContent = '버그수정';
                    break;
                default:
                    tdType.textContent = '알 수 없음';
            }
            tr.appendChild(tdType);

            var tdTitle = document.createElement('td');
            // 제목을 클릭 가능하게 하기 위해 <a> 태그로 감싸기
            var titleLink = document.createElement('a');
            titleLink.href = "#";
            titleLink.textContent = notice.ntTitle;
            titleLink.dataset.ntText = notice.ntText; // ntText를 data 속성으로 저장
            titleLink.style.color = '#333'; // 링크 색상 조정
            titleLink.style.textDecoration = 'underline'; // 링크 밑줄 추가
            titleLink.style.cursor = 'pointer';

            // 클릭 이벤트 리스너 추가
            titleLink.addEventListener('click', function(event) {
                event.preventDefault(); // 기본 링크 동작 방지
                var ntText = this.dataset.ntText;
                displayNoticeContent(ntText);
            });

            tdTitle.appendChild(titleLink);
            tr.appendChild(tdTitle);

            var tdCrdate = document.createElement('td');
            var date = new Date(notice.ntCrdate);
            var formattedDate = date.getFullYear() + '-' +
                                String(date.getMonth()+1).padStart(2, '0') + '-' +
                                String(date.getDate()).padStart(2, '0') + ' ' +
                                String(date.getHours()).padStart(2, '0') + ':' +
                                String(date.getMinutes()).padStart(2, '0') + ':' +
                                String(date.getSeconds()).padStart(2, '0');
            tdCrdate.textContent = formattedDate;
            tr.appendChild(tdCrdate);

            var tdUid = document.createElement('td');
            tdUid.textContent = notice.uid;
            tr.appendChild(tdUid);

            tbody.appendChild(tr);
        });
    }

    function clearNoticeTable() {
        var tbody = document.querySelector("#noticeTable tbody");
        tbody.innerHTML = '';
    }

    // 공지사항 내용 표시 함수
    function displayNoticeContent(ntText) {
        var noticeTextElement = document.getElementById('noticeText');
        if (noticeTextElement) {
            noticeTextElement.textContent = ntText;
        }
    }

    // 설정 모달 관련 코드 (기존 코드 유지)
    var settingsModal = document.getElementById("settingsModal");
    var closeSettingsButton = document.getElementById("closeSettingsButton");
    var settingsIcon = document.getElementById("settingsIcon");

    if (settingsIcon) {
        settingsIcon.addEventListener("click", function() {
            openSettingsModal();
        });
    }

    if (closeSettingsButton) {
        closeSettingsButton.addEventListener("click", function() {
            closeSettingsModal();
        });
    }

    window.addEventListener("click", function(event) {
        if (event.target == settingsModal) {
            settingsModal.style.display = "none";
        }
    });

    function openSettingsModal() {
        settingsModal.style.display = "block";
    }

    function closeSettingsModal() {
        settingsModal.style.display = "none";
    }

    // 기타 기존 함수들...

});

// 페이지 이동을 0.5초 지연시키는 함수
function navigateWithDelay(url) {
    playRandomSoundEffect(); // 효과음 재생
    setTimeout(function () {
        window.location.href = url;
    }, 500); // 0.5초 지연 후 이동
}

// 회원가입 및 로그인 버튼에 이벤트 리스너 추가
function initializeAuthButtons() {
    const signupButton = document.getElementById('signupButton');
    const loginButton = document.getElementById('loginButton');
    const logoutButton = document.getElementById('logoutButton');

    if (signupButton) {
        signupButton.addEventListener('click', function () {
            navigateWithDelay(`${contextPath}/accession`);
        });
    }

    if (loginButton) {
        loginButton.addEventListener('click', function () {
            navigateWithDelay(`${contextPath}/login`);
        });
    }

    if (logoutButton) {
        logoutButton.addEventListener('click', function () {
            logout();
        });
    }
}

// 로그아웃 함수
function logout() {
    localStorage.removeItem('token');
    window.location.href = `${contextPath}/main`;
}

// 랜덤 사운드 효과 재생 함수
function playRandomSoundEffect() {
    if (sfxMuted || !audioContext) return;

    const randomIndex = Math.floor(Math.random() * soundEffectFiles.length);
    const audioURL = soundEffectFiles[randomIndex];

    const audioBuffer = audioBuffers[audioURL];
    if (audioBuffer) {
        const source = audioContext.createBufferSource();
        source.buffer = audioBuffer;

        // GainNode 생성
        const gainNode = audioContext.createGain();
        gainNode.gain.value = soundEffectsVolume * EFFECT_VOLUME_SCALE;

        source.connect(gainNode);
        gainNode.connect(audioContext.destination);

        source.start(0);
    } else {
        console.error('오디오 버퍼가 로드되지 않았습니다:', audioURL);
    }
}

// 요소들에 클릭 이벤트 리스너 추가
function addSoundEffectListeners() {
    // 버튼들에 이벤트 리스너 추가
    document.querySelectorAll('button').forEach(element => {
        const excludeIds = ['signupButton', 'loginButton', 'logoutButton', 'createStoryButton', 'storyListButton', 'deleteAccountButton', 'confirmDeleteButton'];
        if (!excludeIds.includes(element.id)) {
            element.addEventListener('click', playRandomSoundEffect);
        }
    });

    // 메인 스토리와 사용자 스토리에 이벤트 리스너 추가
    const mainStory = document.getElementById('mainStory');
    const userStory = document.getElementById('userStory');

    if (mainStory) {
        mainStory.addEventListener('click', playRandomSoundEffect);
    }

    if (userStory) {
        userStory.addEventListener('click', playRandomSoundEffect);
    }

    // 로고 이미지에 이벤트 리스너 추가
    const logo = document.getElementById('logo');
    if (logo) {
        logo.addEventListener('click', function () {
            navigateWithDelay(`${contextPath}/main`);
        });
    }

// 문의사항 아이콘에 이벤트 리스너 추가
const inquiryIcon = document.querySelector('.inquiry-icon');
if (inquiryIcon) {
    inquiryIcon.addEventListener('click', function () {
        navigateWithDelay(`${contextPath}/inquiry`);
    });
}

    // 설정 아이콘에 이벤트 리스너 추가
    const settingIcon = document.querySelector('.setting-icon');
    if (settingIcon) {
        settingIcon.addEventListener('click', function () {
            playRandomSoundEffect();
            openSettingsModal();
        });
    }
}

// 배경음악 볼륨 조절 함수
function adjustVolume() {
    const bgmSlider = document.getElementById('bgmSlider');
    const muteCheckbox = document.getElementById('muteBGM');
    const backgroundMusic = document.getElementById('backgroundMusic');
    if (bgmSlider && backgroundMusic && !muteCheckbox.checked) {
        backgroundMusic.volume = bgmSlider.value / 100;
    }
}

// 효과음 볼륨 조절 함수
function adjustSFXVolume() {
    const sfxSlider = document.getElementById('sfxSlider');
    if (sfxSlider) {
        soundEffectsVolume = sfxSlider.value / 100;
    }
}

// 음소거 토글 함수
function toggleMute(sliderId) {
    const muteCheckbox = document.getElementById(sliderId === 'bgmSlider' ? 'muteBGM' : 'muteSFX');
    if (sliderId === 'bgmSlider') {
        const backgroundMusic = document.getElementById('backgroundMusic');
        if (muteCheckbox && backgroundMusic) {
            if (muteCheckbox.checked) {
                // 배경음악 볼륨을 0으로 설정
                backgroundMusic.volume = 0;
            } else {
                // 배경음악 볼륨을 슬라이더의 값으로 복원
                adjustVolume();
            }
        }
    } else if (sliderId === 'sfxSlider') {
        if (muteCheckbox) {
            sfxMuted = muteCheckbox.checked;
        }
    }
}

// 카카오 로그인 함수
function kakaoLogin() {
    Kakao.Auth.authorize({
        redirectUri: 'https://a16f-123-142-55-115.ngrok-free.app/StoryCraft/callback'  // 실제 배포 시점에 맞게 수정
    });
}

// 프로필 이미지 업데이트 함수
function updateProfileImage(url) {
    const profileImageElement = document.getElementById('profileImage');
    if (profileImageElement) {
        profileImageElement.src = `${url}?t=${new Date().getTime()}`;
    }

    const profileImagePlaceholder = document.getElementById('profileImagePlaceholder');
    if (profileImagePlaceholder) {
        profileImagePlaceholder.style.backgroundImage = `url(${url})`;
        profileImagePlaceholder.style.backgroundSize = 'cover';
        profileImagePlaceholder.style.backgroundPosition = 'center';
        profileImagePlaceholder.classList.add('image-uploaded');

        const span = profileImagePlaceholder.querySelector('span');
        if (span) {
            span.style.display = 'none';
        }
    }
}

// 메인 스토리 시작 함수
function startMainStory() {
    const token = localStorage.getItem('token');

    if (!token) {
        alert('로그인을 먼저 진행해주세요.');
        navigateWithDelay(`${contextPath}/login`);
        return;
    }

    fetch(`${contextPath}/api/check-main-story-completed`, {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${token}`
        }
    })
    .then(response => response.json())
    .then(data => {
        if (data.mainStoryCompleted) {
            window.location.href = `${contextPath}/mainStory`; // MAIN_COMPLETE가 Y인 경우 바로 이동
        } else {
            const skipConfirm = confirm('메인 스토리를 스킵하시겠습니까?');
            if (skipConfirm) {
                // 메인 스토리 스킵 API 호출하여 MAIN_COMPLETE를 Y로 설정
                fetch(`${contextPath}/api/skip-main-story`, {
                    method: 'POST',
                    headers: {
                        'Authorization': `Bearer ${token}`
                    }
                })
                .then(response => {
                    if (response.ok) {
                        alert('사용자 스토리가 해금되었습니다.');
                        enableUserStoryButton();  // 사용자 스토리 버튼 활성화
                    } else {
                        alert('메인 스토리 스킵에 실패했습니다. 다시 시도해주세요.');
                    }
                })
                .catch(error => {
                    console.error('메인 스토리 스킵 중 오류:', error);
                });
            } else {
                window.location.href = `${contextPath}/mainStory`; // 메인 스토리 페이지로 이동
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
        window.location.href = `${contextPath}/login`;
        return;
    }

    fetch(`${contextPath}/api/check-user-story-unlocked`, {
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
    if (userStoryButton) {
        userStoryButton.style.opacity = '1'; // 버튼을 보이게
        userStoryButton.style.pointerEvents = 'auto';  // 클릭 가능 상태로 설정
        userStoryButton.disabled = false;  // 버튼을 활성화
    }
}

// 사용자 스토리 모달 열기 함수
function openUserStoryModal() {
    const modal = document.createElement('div');
    modal.className = 'user-story-modal';  // 고유한 클래스명 추가
    modal.innerHTML = `
        <div class="user-story-modal-overlay"></div>
        <div class="user-story-modal-content animated fadeIn">
            <span class="close" onclick="closeModal(this)">&times;</span>
            <h2 class="modal-title">사용자 스토리</h2>
            <button id="createStoryButton" class="button-primary">스토리 제작</button>
            <button id="storyListButton" class="button-secondary">스토리 목록</button>
        </div>
    `;
    document.body.appendChild(modal);
    modal.style.display = "block";

    // 이벤트 리스너 추가
    const createStoryButton = document.getElementById('createStoryButton');
    const storyListButton = document.getElementById('storyListButton');

    if (createStoryButton) {
        createStoryButton.addEventListener('click', function () {
            navigateWithDelay(`${contextPath}/story/create`);
        });
    }

    if (storyListButton) {
        storyListButton.addEventListener('click', function () {
            navigateWithDelay(`${contextPath}/story/list`);
        });
    }
}

// 모달 닫기 함수
function closeModal(element) {
    const modal = element.closest('.user-story-modal');  // 고유한 클래스명으로 검색
    if (modal) {
        modal.classList.add('fadeOut');
        setTimeout(() => modal.remove(), 500); // 부드럽게 닫히도록 약간의 지연을 줌
    }
}


// 설정 모달 열기 함수
function openSettingsModal() {
    const modal = document.getElementById('settingsModal');
    if (modal) {
        modal.style.display = 'block';
    }
}

// 설정 모달 닫기 함수
function closeSettingsModal() {
    const modal = document.getElementById('settingsModal');
    if (modal) {
        modal.style.display = 'none';
    }
}

// 탈퇴 확인 모달 열기 함수
function openDeleteAccountModal() {
    const modal = document.getElementById('deleteAccountModal');
    if (modal) {
        modal.style.display = 'block';
    }
}

// 탈퇴 확인 모달 닫기 함수
function closeDeleteAccountModal() {
    const modal = document.getElementById('deleteAccountModal');
    if (modal) {
        modal.style.display = 'none';
    }
}

// 탈퇴 확인 후 계정 삭제 처리 함수
function confirmDeleteAccount() {
    const token = localStorage.getItem('token');
    if (!token) {
        alert('로그인이 필요합니다.');
        window.location.href = `${contextPath}/login`;
        return;
    }

    fetch(`${contextPath}/api/delete-account`, { 
        method: 'POST',
        headers: {
            'Authorization': `Bearer ${token}`
        }
    })
    .then(response => {
        if (response.ok) {
            alert('계정이 탈퇴되었습니다.');
            localStorage.removeItem('token');  // 로그아웃
            window.location.href = `${contextPath}/login`;  // 로그인 페이지로 이동
        } else {
            alert('계정 탈퇴에 실패했습니다. 다시 시도해주세요.');
        }
    })
    .catch(error => console.error('탈퇴 처리 중 오류 발생:', error));
}

// 공지사항 내용 표시 함수
function displayNoticeContent(ntText) {
    var noticeTextElement = document.getElementById('noticeText');
    if (noticeTextElement) {
        noticeTextElement.textContent = ntText;
    }
}
