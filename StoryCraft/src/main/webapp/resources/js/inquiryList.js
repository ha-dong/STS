document.addEventListener('DOMContentLoaded', function () {
    fetch('/StoryCraft/api/inquiryList', {
        method: 'GET',
    })
    .then(response => response.json())
    .then(data => {
        const inquiryList = document.getElementById('inquiryList');
        inquiryList.innerHTML = ''; // 기존 목록 초기화

        data.forEach(inquiry => {
            const listItem = document.createElement('li');
            listItem.innerHTML = `
                <strong>${inquiry.inqTitle}</strong> - ${inquiry.inqText} | ${inquiry.inqTypecode} | ${inquiry.inqGenrecode} | ${new Date(inquiry.inqCrdate).toLocaleString()}
                <button onclick="showDetail(${inquiry.inqNum})">상세보기</button>
                <button onclick="window.location.href='/StoryCraft/jsp/editInquiry.jsp?inqNum=${inquiry.inqNum}'">수정</button>
                <button onclick="confirmDelete(${inquiry.inqNum})">삭제</button>
                
            `;
            inquiryList.appendChild(listItem);
        });
    })
    .catch(error => console.error('문의 목록 로드 중 오류 발생:', error));
});

function confirmDelete(inqNum) {
    const confirmation = confirm("삭제하면 복구할 수 없습니다. 계속하시겠습니까?");
    if (confirmation) {
        fetch(`/StoryCraft/api/inquiry/${inqNum}`, {
            method: 'DELETE'
        })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                alert('삭제되었습니다.');
                location.reload(); // 삭제 후 페이지 새로고침
            } else {
                alert('삭제 실패');
            }
        });
    }
}

function showDetail(inqNum) {
    fetch(`/StoryCraft/api/inquiry/${inqNum}`)
        .then(response => response.json())
        .then(data => {
            document.getElementById('detailTitle').textContent = data.inqTitle;
            document.getElementById('detailText').textContent = data.inqText;
            document.getElementById('detailType').textContent = data.inqTypecode;
            document.getElementById('detailStatus').textContent = data.inqGenrecode;
            document.getElementById('detailImage').src = `/uploads/${data.inqFile}`;
            document.getElementById('detailModal').style.display = 'block';
        });
}

function closeDetailModal() {
    document.getElementById('detailModal').style.display = 'none';
}
