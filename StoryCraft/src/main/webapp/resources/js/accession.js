document.addEventListener('DOMContentLoaded', function () {
    let currentImageIndex = 1;

    // 배경 이미지를 변경하는 함수
    function changeBackgroundImage() {
        currentImageIndex = (currentImageIndex % 5) + 1;  // 1~5까지의 클래스 번호로 순환
        console.log(`Setting background image class to: bg-image-${currentImageIndex}`);  // 현재 클래스 로그 확인
        document.body.className = '';  // 기존 모든 클래스를 제거
        document.body.classList.add(`bg-image-${currentImageIndex}`);  // 새로운 클래스 추가
    }

    // 두 번 클릭 이벤트 추가
    let clickCount = 0;
    document.body.addEventListener('click', function () {
        clickCount++;
        console.log(`Click count: ${clickCount}`);  // 클릭 카운트 로그 추가
        if (clickCount === 2) {
            changeBackgroundImage();
            clickCount = 0; // 클릭 카운터 초기화
        }
    });
});
