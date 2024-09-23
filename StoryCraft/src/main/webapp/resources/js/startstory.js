document.addEventListener('DOMContentLoaded', function () {
    // 스토리 관련 자바스크립트 로직을 여기에 추가할 수 있습니다.
    console.log("StartStory 페이지 로드됨");

    // 씬 선택 시 페이지 이동
    const sceneLinks = document.querySelectorAll('a');
    sceneLinks.forEach(link => {
        link.addEventListener('click', function (event) {
            event.preventDefault();
            const sceneUrl = this.getAttribute('href');
            window.location.href = sceneUrl;
        });
    });
});
