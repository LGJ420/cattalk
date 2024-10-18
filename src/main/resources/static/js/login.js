function login() {
    let userId = document.getElementById("userId").value;
    let userPw = document.getElementById("userPw").value;

    let formData = new FormData();
    formData.append('userId', userId);
    formData.append('userPw', userPw);

    // /login의 POST맵핑은 시큐리티에서 이미 준비되있다.
    fetch('/login', {
        method: 'POST',
        body: formData
    })
    .then(() => {

        // friends로 리다이렉트
        window.location.href = '/friends';
    })
    .catch(error => {
        console.error('로그인에 실패하였습니다.', error);
        document.getElementById("errorMessage").style.display = 'block';
    });
}