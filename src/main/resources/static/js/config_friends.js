// 선택된 친구의 닉네임
let selectedNickname = '';

function setNickname(nickname) {
    selectedNickname = nickname;
}


// 첫 화면 삭제 버튼 클릭시
function handleClickDelete(element) {
    initModalPosition();
    const nickname = element.getAttribute('data-friend-nickname');
    setNickname(nickname);
    openConfirmModal();
}


// ★ 현재는 모달을 완벽히 분리하지 못함 해결 요망
document.getElementById('confirmButton').addEventListener('click', function () {
    closeConfirmModal();
    deleteFriend(selectedNickname);
});


// 친구 삭제 API 호출
function deleteFriend(friendNickname) {

    // csrf처리
    const csrfToken = document.querySelector('meta[name="_csrf"]').content;

    fetch(`/api/friend/${friendNickname}`, {
        method: 'DELETE',
        headers: {
            'X-CSRF-TOKEN': csrfToken
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('친구 삭제에 실패했습니다.');
            }
            // 완료 모달 창 표시
            showSuccessModal();
            setTimeout(() => {
                window.location.reload();
            }, 1500);
        })
        .catch(error => {
            console.error('Error: ', error);
            alert('친구 삭제 중 오류가 발생했습니다.');
            const modalContainer = document.getElementById('modalContainer');
            modalContainer.classList.add('hidden');
        });
}