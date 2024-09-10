// 문의 상세 정보 로드
document.addEventListener('DOMContentLoaded', function () {
    const params = new URLSearchParams(window.location.search);
    const inqNum = params.get('inqNum');

    // GET 요청으로 문의 상세 정보를 가져옵니다.
    fetch(`/StoryCraft/api/inquiry/${inqNum}`, {
        method: 'GET',
    })
    .then(response => response.json())  // JSON으로 변환
    .then(data => {
        // data가 JSON 형태로 제대로 받아지는지 확인
        console.log("문의 상세 정보:", data);
        document.getElementById('inquiryTitle').value = data.inqTitle;
        document.getElementById('inquiryText').value = data.inqText;
        document.getElementById('inquiryType').value = data.inqTypecode;
    })
    .catch(error => {
        console.error("문의 상세 정보 로드 중 오류 발생:", error);
    });
});

// 수정 요청
function submitEdit() {
    const params = new URLSearchParams(window.location.search);
    const inqNum = params.get('inqNum');

    const updatedInquiry = {
        inqTitle: document.getElementById('inquiryTitle').value,
        inqText: document.getElementById('inquiryText').value,
        inqTypecode: document.getElementById('inquiryType').value
    };

    fetch(`/StoryCraft/api/inquiry/${inqNum}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(updatedInquiry),
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            alert('수정이 완료되었습니다.');
            window.location.href = '/StoryCraft/jsp/inquiryList.jsp';  // 수정 완료 후 목록 페이지로 이동
        } else {
            alert('수정 중 오류가 발생했습니다.');
        }
    })
    .catch(error => {
        console.error('수정 중 오류 발생:', error);
    });
}
