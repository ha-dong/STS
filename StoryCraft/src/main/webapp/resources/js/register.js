document.addEventListener('DOMContentLoaded', function () {
    const registerForm = document.getElementById('register-form');
    const checkButton = document.querySelector('.btn-check-userid');

      // 사용자 ID 중복 확인
    checkButton.addEventListener('click', async function () {
        const userId = document.getElementById('register-userid').value;
        if (!userId) {
            alert('ID를 입력하세요.');
            return;
        }

        try {
            const response = await fetch('/StoryCraft/api/validate-id', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ userid: userId })
            });

            const data = await response.json();
            if (data.valid) {
                alert('사용 가능한 ID입니다.');
            } else {
                alert('사용 중인 ID입니다.');
            }
        } catch (error) {
            console.error('중복 확인 오류:', error);
        }
    });

    // 회원가입 폼 제출
    registerForm.addEventListener('submit', async function (e) {
        e.preventDefault(); // 기본 폼 제출 방지

        const userId = document.getElementById('register-userid').value;
        const password = document.getElementById('register-password').value;
        const confirmPassword = document.getElementById('register-confirm-password').value;
        const username = document.getElementById('register-username').value;
        const nickname = document.getElementById('register-nickname').value;
        const birthday = document.getElementById('register-birthday').value;
        const gender = document.getElementById('register-gender').value;
        const phoneNumber = document.getElementById('register-phonenumber').value;
        const emailLocal = document.getElementById('register-email-local').value;
        const emailDomain = document.getElementById('register-email-domain').value;
        const address = document.getElementById('register-address').value;
        const email = `${emailLocal}@${emailDomain}`;

        // 모든 필드가 채워졌는지 확인
        if (!userId || !password || !confirmPassword || !username || !nickname || !birthday || !gender || !phoneNumber || !email || !address) {
            alert('모든 정보를 입력해 주세요.');
            return;
        }

        // 비밀번호 확인
        if (password !== confirmPassword) {
            alert('비밀번호가 일치하지 않습니다.');
            return;
        }

        try {
            const response = await fetch('/StoryCraft/register', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                body: new URLSearchParams({
                    userid: userId,
                    password,
                    confirmPassword,
                    username,
                    nickname,
                    birthday,
                    gender,
                    phonenumber: phoneNumber,
                    emailLocal,
                    emailDomain,
                    address
                })
            });

            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }

            // 회원가입 성공 후 로그인 페이지로 리다이렉트
            alert('회원가입이 성공적으로 완료되었습니다.');
            window.location.href = 'login'; 
        } catch (error) {
            console.error('회원가입 오류:', error);
            alert('회원가입 중 오류가 발생했습니다.');
        }
    });
});
