document.addEventListener('DOMContentLoaded', function () {
    // 장르 탭 기능
    const genreTabs = document.querySelectorAll('.genre-tab');
    const storyItems = document.querySelectorAll('.story-item');

    genreTabs.forEach(tab => {
        tab.addEventListener('click', function () {
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
    if (sortDropdown) {
        sortDropdown.addEventListener('change', function () {
            const sortType = this.value;
            const sortedItems = Array.from(storyItems).sort((a, b) => {
                const likeA = parseInt(a.querySelector('.story-like span').textContent, 10);
                const likeB = parseInt(b.querySelector('.story-like span').textContent, 10);

                if (sortType === 'like') {
                    return likeB - likeA;  // 인기순
                } else if (sortType === 'recent') {
                    return likeB - likeA;  // 최신순 정렬 (임시로 같은 로직, 실제 구현시 작성 날짜로 변경 가능)
                }
            });

            // 정렬된 아이템을 DOM에 다시 추가
            const storyList = document.querySelector('.story-list');
            if (storyList) {
                storyList.innerHTML = '';
                sortedItems.forEach(item => storyList.appendChild(item));
            }
        });
    }

    // 모달창 관련 함수
    function openStoryModal(title, genre, creator, likes, isCreator, thumbnail) {
        document.getElementById('storyTitle').innerText = title;
        document.getElementById('storyGenre').innerText = genre;
        document.getElementById('storyCreator').innerText = creator;
        document.getElementById('likeCount').innerText = likes;
        document.getElementById('storyThumbnail').src = thumbnail;

        if (isCreator) {
            document.getElementById('editButton').style.display = 'block';
            document.getElementById('deleteButton').style.display = 'block';
            document.getElementById('reportButton').style.display = 'none';
        } else {
            document.getElementById('editButton').style.display = 'none';
            document.getElementById('deleteButton').style.display = 'none';
            document.getElementById('reportButton').style.display = 'block';
        }

        document.getElementById('storyModal').style.display = 'block';
    }

    const closeModalButton = document.querySelector('.close');
    if (closeModalButton) {
        closeModalButton.addEventListener('click', function () {
            document.getElementById('storyModal').style.display = 'none';
        });
    }

    // 스토리 수정
    document.getElementById('editButton').addEventListener('click', function () {
        const storyTitle = document.getElementById('storyTitle').innerText;
        location.href = `/storyedit/${storyTitle}`;
    });

    // 스토리 삭제
    document.getElementById('deleteButton').addEventListener('click', function () {
        const confirmDelete = confirm('정말로 이 스토리를 삭제하시겠습니까?');
        if (confirmDelete) {
            // 서버에 스토리 삭제 요청
            alert('스토리가 삭제되었습니다.');
            closeStoryModal();
            location.reload();  // 페이지 새로고침
        }
    });

    // 신고 모달 열기
    const reportButton = document.getElementById('reportButton');
    if (reportButton) {
        reportButton.addEventListener('click', function () {
            document.getElementById('reportModal').style.display = 'block';
        });
    }

    // 신고 모달 닫기
    const closeReportButton = document.querySelector('.close-report');
    if (closeReportButton) {
        closeReportButton.addEventListener('click', function () {
            document.getElementById('reportModal').style.display = 'none';
        });
    }

    // 신고 제출
    document.getElementById('submitReport').addEventListener('click', function () {
        const reportReason = document.getElementById('reportReason').value;
        const reportDetails = document.getElementById('reportDetails').value;

        if (!reportDetails) {
            alert('신고 내용을 입력하세요.');
            return;
        }

        // 서버에 신고 데이터 전송 로직 추가 가능
        alert('신고가 접수되었습니다.');
        document.getElementById('reportModal').style.display = 'none';
    });

    // 하트(추천) 버튼 기능
    const likeButtons = document.querySelectorAll('.story-like img');
    if (likeButtons.length > 0) {
        likeButtons.forEach((button, index) => {
            button.addEventListener('click', function (event) {
                event.stopPropagation();  // 스토리 클릭 이벤트 중지
                const likeCountElement = button.previousElementSibling;
                let likeCount = parseInt(likeCountElement.textContent, 10);
                likeCount += 1;  // 추천 수 증가
                likeCountElement.textContent = likeCount;

                // 서버에 추천 수 업데이트 로직 추가 가능
                alert('추천되었습니다!');
            });
        });
    }

    // 스토리 시작
    document.getElementById('startButton').addEventListener('click', function () {
        alert('스토리를 시작합니다.');
        location.href = '/storyplay';
    });

    // 제작자 이름 클릭 시 프로필 페이지로 이동
    const storyCreatorElement = document.getElementById('storyCreator');
    if (storyCreatorElement) {
        storyCreatorElement.addEventListener('click', function () {
            const creatorName = this.innerText;
            location.href = `/profile/${creatorName}`;
        });
    }
});
