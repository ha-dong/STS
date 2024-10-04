//find.js

document.addEventListener('DOMContentLoaded', function () {
// 아이디 찾기 폼 처리
    document.getElementById('find-id-form').addEventListener('submit', async function (e) {
        e.preventDefault();
        const username = document.getElementById('find-id-username').value.trim();
        const email = document.getElementById('find-id-email').value.trim();

        console.log("Sending trimmed username:", username);
        console.log("Sending trimmed email:", email);

        if (!username || !email) {
            alert('이름과 이메일을 입력해 주세요.');
            return;
        }

        try {
            const response = await fetch('/StoryCraft/api/find-id', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ username, email })
            });

            if (response.ok) {
                const data = await response.json();
                console.log("Response data:", data);
                if (data.userId) {
                    alert(`아이디 찾기 성공: ${data.userId}`);
                } else {
                    alert(data.message || '아이디를 찾을 수 없습니다.');
                }
            } else {
                alert('서버 응답이 정상적이지 않습니다.');
            }
        } catch (error) {
            console.error('아이디 찾기 오류:', error);
            alert('아이디 찾기 중 오류가 발생했습니다.');
        }
    });
    
    // 비밀번호 찾기(인증 이메일 전송) 폼 처리
    document.getElementById('find-password-form').addEventListener('submit', async function (e) {
        e.preventDefault(); // 기본 폼 제출 방지

        const userid = document.getElementById('find-password-userid').value.trim();
        const email = document.getElementById('find-password-email').value.trim();

        if (!userid || !email) {
            alert('아이디와 이메일을 입력해 주세요.');
            return;
        }

        try {
            const response = await fetch('/StoryCraft/api/send-auth-email', {  // URL을 직접 지정합니다.
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ userid, email })
            });

            if (response.ok) {
                const data = await response.json();
                alert(data.message);
                if (data.message.includes('전송')) {
                    window.location.href = '/StoryCraft/resetpw?email=' + encodeURIComponent(email); // URL을 직접 지정합니다.
                }
            } else {
                const data = await response.json();
                alert(data.message || '서버 응답이 정상적이지 않습니다.');
            }
        } catch (error) {
            console.error('비밀번호 찾기 오류:', error);
            alert('비밀번호 찾기 중 오류가 발생했습니다.');
        }
    });
});
