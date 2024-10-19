function login() {
    let userId = document.getElementById("userId").value;
    let userPw = document.getElementById("userPw").value;

    let formData = new FormData();
    formData.append('userId', userId);
    formData.append('userPw', userPw);
    
    // fetch를 통한 방법에서 csrf는 이렇게 보낼수 있다.
    const csrfToken = document.querySelector('meta[name="_csrf"]').content;

    // /login의 POST맵핑은 시큐리티에서 이미 준비되있다.
    fetch('/login', {
        method: 'POST',
        headers: {
            'X-CSRF-TOKEN': csrfToken
        },
        body: formData
    })
    .then(response => {
        if (response.ok) {
            window.location.href = '/friends';
        } else {
            throw new Error('Login failed');
        }
    })
    .catch(error => {
        console.error('로그인에 실패하였습니다. : ', error);
        document.getElementById("errorMessage").style.display = 'block';
    });
}