document.addEventListener('DOMContentLoaded', function() {
    // 장르 탭 기능
    const genreTabs = document.querySelectorAll('.genre-tab');
    const storyItems = document.querySelectorAll('.story-item');

    genreTabs.forEach(tab => {
        tab.addEventListener('click', function() {
            // 모든 탭에서 active 클래스 제거
            genreTabs.forEach(t => t.classList.remove('active'));
            // 클릭한 탭에 active 클래스 추가
            this.classList.add('active');

            const genre = this.textContent;

            storyItems.forEach(item => {
                const itemGenre = item.querySelector('.story-info p:nth-child(2)').textContent.split(': ')[1];
                
                // 선택된 장르가 "전체"이거나 아이템의 장르가 탭의 장르와 일치하면 표시
                if (genre === '전체' || genre === itemGenre) {
                    item.style.display = 'block';
                } else {
                    item.style.display = 'none';
                }
            });
        });
    });

    // 정렬 드롭다운 기능
    const sortDropdown = document.querySelector('.sort-dropdown select');
    sortDropdown.addEventListener('change', function() {
        const sortType = this.value;
        const sortedItems = Array.from(storyItems).sort((a, b) => {
            const likeA = parseInt(a.querySelector('.story-like span').textContent, 10);
            const likeB = parseInt(b.querySelector('.story-like span').textContent, 10);

            if (sortType === '인기순') {
                return likeB - likeA;
            } else if (sortType === '최신순') {
                // 여기에서는 최신순 정렬을 위한 별도의 로직을 넣을 수 있습니다.
                // 현재는 단순히 내림차순 정렬이므로 같은 결과를 출력합니다.
                return likeB - likeA;
            }
        });

        // 정렬된 아이템을 DOM에 다시 추가
        const storyList = document.querySelector('.story-list');
        storyList.innerHTML = '';
        sortedItems.forEach(item => storyList.appendChild(item));
    });

    // 커스텀 설정 버튼 기능
    const customizeButton = document.querySelector('.customize-button');
    customizeButton.addEventListener('click', function() {
        alert('커스텀 설정 기능은 현재 구현 중입니다.');
        // 실제 커스텀 설정 로직을 여기에 추가할 수 있습니다.
    });
});
