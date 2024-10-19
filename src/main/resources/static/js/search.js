// 멤버 목록 html 틀
const getList = (nickname, profileImage) => {
    return `<li>
                    <div class="bg-amber-700 bg-opacity-0 hover:bg-opacity-30 cursor-pointer friend-item" data-friend-nickname="${nickname}">
                        <div class="flex items-center p-5">
                            <img src="/images/profile_default.png" alt="프로필 이미지"
                                class="rounded-full w-16 h-16 object-contain" />
                            <div class="text-2xl px-5">${nickname}</div>
                        </div>
                    </div>
                </li>`;
}

// 검색 버튼 클릭시
document.getElementById('searchButton').addEventListener('click', function () {
    searchNickname();
});

// 단축키 설정 (Enter 키)
document.getElementById("findNickname").addEventListener("keydown", (e) => {
    if (e.key === 'Enter') {
        searchNickname();
    }
});

// 닉네임으로 사용자 검색
function searchNickname() {
    const nickname = document.getElementById('findNickname').value.trim();

    if (nickname) {
        fetch(`/api/user?nickname=${encodeURIComponent(nickname)}`)
            .then(response => response.json())
            .then(data => {
                renderResults(data);
                // 검색 후 입력 필드 초기화
                document.getElementById('findNickname').value = '';
            })
            .catch(error => console.error('Error:', error));
    }
}

// 검색 결과 렌더링
function renderResults(users) {
    const resultsContainer = document.getElementById('searchResults');
    resultsContainer.innerHTML = ''; // 이전 결과 삭제

    if (users.length === 0) {
        resultsContainer.innerHTML =
            `<li>
                    <div class="flex items-center justify-center h-32">
                        검색 결과가 없습니다.
                    </div>
                </li>`;
        return;
    }

    users.forEach(user => {
        const listItemHTML = getList(user.nickname, user.profileImage);
        resultsContainer.insertAdjacentHTML('beforeend', listItemHTML);
    });

    // 친구 항목에 클릭 이벤트 리스너 추가
    const friendItems = document.querySelectorAll('.friend-item');
    friendItems.forEach(item => {
        item.addEventListener('click', function () {
            initModalPosition();
            const selectedNickname = this.getAttribute('data-friend-nickname');
            setNickname(selectedNickname);
            openConfirmModal();
        });
    });
}


// 선택된 친구의 닉네임
let selectedNickname = '';

function setNickname(nickname) {
    selectedNickname = nickname;
}

// ★ 현재는 모달을 완벽히 분리하지 못함 해결 요망
document.getElementById('confirmButton').addEventListener('click', function () {
    closeConfirmModal();
    addFriend(selectedNickname);
});

// 친구 추가 API 호출
function addFriend(friendNickname) {

    let formData = new FormData();
    formData.append('friendNickname', friendNickname);

    fetch('/api/friend', {
        method: 'POST',
        body: formData
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('친구 추가에 실패했습니다.');
            }
            // 완료 모달 창 표시
            showSuccessModal();
        })
        .catch(error => {
            console.error('Error: ', error);
            alert('친구 추가 중 오류가 발생했습니다.');
            const modalContainer = document.getElementById('modalContainer');
            modalContainer.classList.add('hidden');
        });
}
