document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('inquiryForm');
    if (form) {
        form.addEventListener('submit', function (e) {
            e.preventDefault();
            const formData = new FormData(form);

            const fileInput = document.getElementById('inquiryFile');
            if (fileInput.files.length > 0) {
                formData.append('inquiryFile', fileInput.files[0]);  // 파일 추가
            }

            fetch('/StoryCraft/api/inquiry', {
                method: 'POST',
                body: formData,
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error('서버 응답 에러');
                }
                return response.json();
            })
            .then(data => {
                if (data.success) {
                    alert('문의가 성공적으로 등록되었습니다.');
                    // inquiryList로 리디렉션
                    window.location.href = '/StoryCraft/jsp/inquiryList.jsp';
                } else {
                    alert('문의 등록에 실패했습니다.');
                }
            })
            .catch(error => {
                console.error('문의 등록 중 오류 발생:', error);
                alert('문의 등록 중 오류가 발생했습니다.');
            });
        });
    }
});
