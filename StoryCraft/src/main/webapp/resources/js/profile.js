// profile.js
window.onload = function () {
    // 필요한 DOM 요소들을 변수로 선언
    const nicknameElement = document.getElementById('nickname');
    const profileImageElement = document.getElementById('profileImage');
    const userNicknameElement = document.getElementById('userNickname');
    const userEmailElement = document.getElementById('userEmail');
    const userGenderElement = document.getElementById('userGender');
    const userBirthdayElement = document.getElementById('userBirthday');
    const userBioElement = document.getElementById('userBio');
    const logoutButton = document.getElementById('logoutButton');
    const loginButton = document.getElementById('loginButton');
    const signupButton = document.getElementById('signupButton');
    const profileUpload = document.getElementById('profileUpload');
    const profileImagePlaceholder = document.getElementById('profileImagePlaceholder');

    // 로그인 확인 로직
    fetch(`${contextPath}/api/check-login`, { 
        method: 'GET',
        credentials: 'include' 
    })
    .then(response => {
        if (!response.ok && response.status === 401) {
            throw new Error('Unauthorized');
        }
        return response.json();
    })
    .then(data => {
        if (data.loggedIn) {
            // 이메일 업데이트
            if (userEmailElement) {
                userEmailElement.textContent = data.email || '정보 없음';
            }
            // 닉네임 업데이트
            if (userNicknameElement) {
                userNicknameElement.textContent = data.nickname || '정보 없음';
            }
            // 성별 업데이트
            if (userGenderElement) {
                userGenderElement.textContent = data.gender || '정보 없음';
            }
            // 생일 업데이트
            if (userBirthdayElement) {
                userBirthdayElement.textContent = data.birthday ? new Date(data.birthday).toLocaleDateString() : '정보 없음';
            }
            // 한 줄 소개 업데이트
            if (userBioElement) {
                userBioElement.textContent = data.bio || '정보 없음';
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
            // 기타 처리...
            if (logoutButton) {
                logoutButton.style.display = 'inline-block';
            }
            if (loginButton) {
                loginButton.style.display = 'none';
            }
            if (signupButton) {
                signupButton.style.display = 'none';
            }
            // 사용자 포스트 버튼 활성화
            enableUserPostButton();
        } else {
            handleLoggedOutState();
        }
    })
    .catch(error => {
        console.error('로그인 상태 확인 중 오류:', error);
        handleLoggedOutState();
    });

    // 프로필 이미지 업로드 처리
    if (profileImagePlaceholder && profileUpload) { 
        profileImagePlaceholder.addEventListener('click', function () {
            profileUpload.click(); 
        });

        profileUpload.addEventListener('change', function () {
            const file = this.files[0];
            if (file) {
                const formData = new FormData();
                formData.append('profileImage', file);

                fetch(`${contextPath}/api/upload-profile`, {
                    method: 'POST',
                    body: formData,
                    credentials: 'include' 
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
                        updateProfileImage(data.profileImageUrl);
                    } else {
                        alert('이미지 업로드에 실패했습니다.');
                    }
                })
                .catch(error => console.error('프로필 이미지 업로드 오류:', error));
            }
        });
    } else {
        console.error('profileUpload 또는 profileImagePlaceholder 요소가 정의되지 않았습니다.');
    }

    // 프로필 수정 함수
    const editProfileForm = document.getElementById('editProfileForm');
    if (editProfileForm) {
        editProfileForm.addEventListener('submit', function(event) {
            event.preventDefault();
            const email = document.getElementById('editEmail').value.trim();
            const nickname = document.getElementById('editNickname').value.trim();
            const gender = document.getElementById('editGender').value;
            const birthday = document.getElementById('editBirthday').value;
            const intro = document.getElementById('editIntro').value.trim();

            if (email && nickname && gender && birthday && intro) {
                fetch(`${contextPath}/api/update-profile`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({
                        email: email,
                        nickname: nickname,
                        gender: gender,
                        birthday: birthday,
                        intro: intro
                    }),
                    credentials: 'include' 
                })
                .then(response => response.json())
                .then(data => {
                    if (data.success === "true") {
                        alert('프로필이 성공적으로 업데이트되었습니다.');
                        window.location.reload(); 
                    } else {
                        alert('프로필 업데이트에 실패했습니다: ' + data.message);
                    }
                })
                .catch(error => {
                    console.error('프로필 업데이트 중 오류 발생:', error);
                    alert('프로필 업데이트에 실패했습니다. 다시 시도해주세요.');
                });
            } else {
                alert('모든 필드를 정확하게 입력해주세요.');
            }
        });
    }

    // 로그아웃 버튼 클릭 시
    if (logoutButton) {
        logoutButton.onclick = function() {
            logout();
        };
    }

    // 로그인 버튼 클릭 시
    if (loginButton) {
        loginButton.onclick = function() {
            window.location.href = `${contextPath}/login`;
        };
    }

    // 회원가입 버튼 클릭 시
    if (signupButton) {
        signupButton.onclick = function() {
            window.location.href = `${contextPath}/accession`;
        };
    }

    // 추가적인 이벤트 리스너 설정
    addEventListeners();
};

// 로그아웃 함수
function logout() {
    fetch(`${contextPath}/api/logout`, { 
        method: 'POST',
        credentials: 'include' 
    })
    .then(response => {
        if (response.ok) {
            alert('로그아웃 되었습니다.');
            window.location.href = `${contextPath}/main`;
        } else {
            alert('로그아웃에 실패했습니다. 다시 시도해주세요.');
        }
    })
    .catch(error => {
        console.error('로그아웃 중 오류 발생:', error);
        alert('로그아웃에 실패했습니다. 다시 시도해주세요.');
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

// 로그인되지 않은 상태 처리 함수
function handleLoggedOutState() {
    // 로그아웃 버튼 숨기기
    const logoutButton = document.getElementById('logoutButton');
    if (logoutButton) {
        logoutButton.style.display = 'none';
    }

    // 로그인 및 회원가입 버튼 보이기
    const loginButton = document.getElementById('loginButton');
    const signupButton = document.getElementById('signupButton');

    if (loginButton) {
        loginButton.style.display = 'inline-block';
    }

    if (signupButton) {
        signupButton.style.display = 'inline-block';
    }

    // 사용자 포스트 버튼 숨기기 (필요한 경우)
    const userPostButton = document.getElementById('userPostButton');
    if (userPostButton) {
        userPostButton.style.display = 'none';
    }

    // 닉네임 요소 숨기기
    const nicknameElement = document.getElementById('nickname');
    if (nicknameElement) {
        nicknameElement.style.display = 'none';
    }

    // 프로필 이미지 기본 이미지로 설정
    const profileImageElement = document.getElementById('profileImage');
    if (profileImageElement) {
        profileImageElement.src = `${contextPath}/resources/img/default_profile.png`;
        profileImageElement.style.display = 'none';
    }
}

// 추가적인 이벤트 리스너 설정 함수
function addEventListeners() {
    // 모달 외부 클릭 시 모달 닫기
    window.onclick = function (event) {
        const editModal = document.getElementById('editModal');
        if (event.target == editModal) {
            editModal.style.display = "none";
        }

        const settingsModal = document.getElementById('settingsModal');
        if (event.target == settingsModal) {
            settingsModal.style.display = "none";
        }

        const deleteAccountModal = document.getElementById('deleteAccountModal');
        if (event.target == deleteAccountModal) {
            deleteAccountModal.style.display = "none";
        }
    };

    // 설정 모달 내부 닫기 버튼 클릭 시 모달 닫기
    const closeSettingsButton = document.getElementById('closeSettingsButton');
    if (closeSettingsButton) {
        closeSettingsButton.addEventListener('click', closeSettingsModal);
    }

    // 탈퇴 확인 모달 닫기 버튼 클릭 시 모달 닫기
    const closeDeleteAccountButton = document.querySelector('#deleteAccountModal .close');
    if (closeDeleteAccountButton) {
        closeDeleteAccountButton.addEventListener('click', closeDeleteAccountModal);
    }
}

// 설정 모달 띄우기
function showSettingsModal() {
    const settingsModal = document.getElementById('settingsModal');
    if (settingsModal) {
        settingsModal.style.display = 'block';
    } else {
        console.error('Settings modal element not found.');
    }
}

// 설정 모달 닫기
function closeSettingsModal() {
    const settingsModal = document.getElementById('settingsModal');
    if (settingsModal) {
        settingsModal.style.display = 'none';
    } else {
        console.error('Settings modal element not found.');
    }
}

// 프로필 수정 모달 열기
function openEditModal() {
    const editModal = document.getElementById('editModal');
    if (editModal) {
        editModal.style.display = 'block';
    }
}

// 프로필 수정 모달 닫기
function closeEditModal() {
    const editModal = document.getElementById('editModal');
    if (editModal) {
        editModal.style.display = 'none';
    }
}

// 탈퇴 모달 관련 함수
function showDeleteAccountModal() {
    const deleteModal = document.getElementById('deleteAccountModal');
    if (deleteModal) {
        deleteModal.style.display = 'block';
    }
}

function closeDeleteAccountModal() {
    const deleteModal = document.getElementById('deleteAccountModal');
    if (deleteModal) {
        deleteModal.style.display = 'none';
    }
}

function confirmDeleteAccount() {
    fetch(`${contextPath}/api/delete-account`, { 
        method: 'POST',
        credentials: 'include' 
    })
    .then(response => {
        if (response.ok) {
            alert('계정이 탈퇴되었습니다.');
            window.location.href = `${contextPath}/login`;  
        } else {
            alert('계정 탈퇴에 실패했습니다. 다시 시도해주세요.');
        }
    })
    .catch(error => console.error('탈퇴 처리 중 오류 발생:', error));
}

// 사용자 포스트 버튼 활성화 함수 (필요한 경우)
function enableUserPostButton() {
    const userPostButton = document.getElementById('userPostButton');
    if (userPostButton) {
        userPostButton.disabled = false;
        userPostButton.style.opacity = '1';
        userPostButton.style.pointerEvents = 'auto';
        userPostButton.addEventListener('click', function () {
            window.location.href = `${contextPath}/userPost`;
        });
    } else {
        console.error('User Post button not found.');
    }
}
