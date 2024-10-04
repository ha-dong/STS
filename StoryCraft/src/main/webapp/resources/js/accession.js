document.addEventListener('DOMContentLoaded', function () {
    let currentImageIndex = 1;

    // 배경 이미지를 변경하는 함수
    function changeBackgroundImage() {
        currentImageIndex = (currentImageIndex % 5) + 1;
        console.log(`Setting background image class to: bg-image-${currentImageIndex}`);
        document.body.className = '';
        document.body.classList.add(`bg-image-${currentImageIndex}`);
    }

    // 두 번 클릭 이벤트 추가
    let clickCount = 0;
    document.body.addEventListener('click', function () {
        clickCount++;
        console.log(`Click count: ${clickCount}`);
        if (clickCount === 2) {
            changeBackgroundImage();
            clickCount = 0;
        }
    });
    
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
            redirectUri: 'https://6cb4-123-142-55-115.ngrok-free.app/StoryCraft/callback'  // 새로운 ngrok URL로 수정
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
