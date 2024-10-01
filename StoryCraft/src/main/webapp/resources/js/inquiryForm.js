// inquiryForm.js

document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('inquiryForm');
    form.addEventListener('submit', function (e) {
        e.preventDefault();
        const formData = new FormData(form);

        fetch('/StoryCraft/api/inquiry', {
            method: 'POST',
            body: formData,
        })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                alert('문의가 성공적으로 등록되었습니다.');
                window.location.href = '/StoryCraft/jsp/inquiry.jsp'; // 문의 목록 페이지로 이동
            } else {
                alert('문의 등록에 실패했습니다.');
            }
        })
        .catch(error => {
            console.error('문의 등록 중 오류 발생:', error);
            alert('문의 등록 중 오류가 발생했습니다.');
        });
    });
});
