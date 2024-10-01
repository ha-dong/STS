// inquiry.js

document.addEventListener('DOMContentLoaded', function() {
    loadInquiryList();
});

function loadInquiryList() {
    var contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf('/', 1));
    fetch(contextPath + '/api/inquiryList')
        .then(response => response.json())
        .then(data => {
            var inquiryListBody = document.getElementById('inquiryListBody');
            inquiryListBody.innerHTML = '';

            var inquiries = data.inquiryList;

            inquiries.forEach(function(inquiry) {
                var tr = document.createElement('tr');

                // 제목
                var titleTd = document.createElement('td');
                var titleLink = document.createElement('a');
                titleLink.href = contextPath + '/inquiryDetail?inqNum=' + inquiry.inqNum;
                titleLink.textContent = inquiry.inqTitle;
                titleTd.appendChild(titleLink);
                tr.appendChild(titleTd);

                // 종류
                var typeTd = document.createElement('td');
                typeTd.textContent = inquiry.inqTypecode || '알 수 없음';
                tr.appendChild(typeTd);

				// 상태
				var statusTd = document.createElement('td');
				statusTd.textContent = inquiry.inqGenrecode || '알 수 없음';  // 이미 변환된 상태명 출력
				tr.appendChild(statusTd);


                // 작성일
                var dateTd = document.createElement('td');
                var date = new Date(inquiry.inqCrdate);
                dateTd.textContent = date.toLocaleString();
                tr.appendChild(dateTd);

                // 작성자
                var userTd = document.createElement('td');
                userTd.textContent = inquiry.userId || '알 수 없음';
                tr.appendChild(userTd);

                // 삭제 버튼 제거 (생성하지 않음)

                inquiryListBody.appendChild(tr);
            });
        })
        .catch(error => {
            console.error('문의 목록을 불러오는 중 오류 발생:', error);
        });
}
