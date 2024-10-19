function handleClickSubmit() {

    const password1 = document.getElementById('password1').value;
    const password2 = document.getElementById('password2').value;
    const phone = document.getElementById('phone').value;
    const detailAddress = document.getElementById('detailAddress').value;


    // 서버 부하를 줄이기 위해 모든 값이 기존과 같으면 완료처리 한다.
    if (password1 === "" && password2 === "" && phone === oldPhone && detailAddress === oldDetailAddress) {
        showSuccessModal();
        return;
    }

    if (password1 !== "" && password1 !== password2) {

        alert('비밀번호가 일치하지 않습니다');
        return;
    }


    let userDTO = {};
    if (password1 !== "") {
        userDTO.userPw = password1;
    }
    if (phone !== oldPhone) {
        userDTO.phone = phone;
    }
    if (detailAddress !== oldDetailAddress) {
        userDTO.detailAddress = detailAddress;
    }


    fetch('/api/user', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(userDTO)
    })
        .then(() => {
            showSuccessModal();
        })
        .catch(error => {

            console.error('Error: ', error);
            alert('정보 설정 중 오류가 발생했습니다.');
        })
}