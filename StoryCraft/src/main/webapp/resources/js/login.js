document.addEventListener('DOMContentLoaded', function () {
    const loginForm = document.getElementById('login-form'); 
    const modal = document.getElementById('reactivate-modal');
    const confirmReactivateButton = document.getElementById('confirm-reactivate');
    const cancelReactivateButton = document.getElementById('cancel-reactivate');

    if (loginForm) {
    loginForm.addEventListener('submit', function (event) {
        event.preventDefault(); // 폼의 기본 제출 동작 막기

        const username = document.getElementById('login-userid').value.trim(); 
        const password = document.getElementById('login-password').value.trim(); 

        // 로그인 요청
        fetch('/StoryCraft/api/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                username: username,
                password: password
            })
        }).then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error('로그인 실패');
            }
        }).then(data => {
            if (data.loggedIn) {
                // 로그인 성공 후 서버에서 받은 redirectUrl로 이동
                window.location.href = data.redirectUrl;
            } else {
                alert('로그인에 실패했습니다.');
            }
        }).catch(error => {
            console.error('로그인 요청 중 오류:', error);
            alert('로그인에 실패했습니다. 다시 시도해주세요.');
        });
    });
}


    // 계정 복구 여부 모달을 표시하는 함수
    function showReactivateModal(username) {
        modal.style.display = 'block';

        confirmReactivateButton.onclick = function () {
            reactivateAccount(username);
            modal.style.display = 'none';
        };

        cancelReactivateButton.onclick = function () {
            alert('계정 복구에 실패했습니다.');
            modal.style.display = 'none';
        };
    }

    // 계정 복구 API 호출 함수
    function reactivateAccount(username) {
        fetch('/StoryCraft/api/reactivate-account', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                username: username
            })
        }).then(response => {
            if (response.ok) {
                alert('계정이 복구되었습니다. 다시 로그인해주세요.');
                window.location.href = '/StoryCraft/login'; // 복구 후 로그인 페이지로 리다이렉트
            } else {
                throw new Error('계정 복구 실패');
            }
        }).catch(error => {
            console.error('계정 복구 중 오류:', error);
            alert('계정 복구에 실패했습니다. 다시 시도해주세요.');
        });
    }
    
        // Kakao SDK 초기화
    if (typeof Kakao !== 'undefined') {
        Kakao.init('745300ecc56521bf5042a97731296db5');  // JavaScript 키 사용
        console.log(Kakao.isInitialized());  // SDK 초기화 여부 확인
    } else {
        console.error('Kakao SDK가 로드되지 않았습니다.');
    }

    // 카카오 로그인 함수
    function kakaoLogin() {
        Kakao.Auth.authorize({
            redirectUri: 'https://0b98-123-142-55-115.ngrok-free.app/StoryCraft/callback'  // 새로운 ngrok URL로 수정
        });
    }

    // 카카오 로그인 버튼 클릭 이벤트
	document.getElementById('kakao-login-btn').addEventListener('click', function(event) {
   	 if (!event.target.disabled) {
       	 event.target.disabled = true;
       	 kakaoLogin();  // 로그인 요청
        	
       	 // 인증 실패 시 버튼을 다시 활성화하는 로직
        	setTimeout(function() {
           	 event.target.disabled = false;
       	 }, 5000);  // 예시로 5초 후 버튼을 다시 활성화
    	}
	});
});
