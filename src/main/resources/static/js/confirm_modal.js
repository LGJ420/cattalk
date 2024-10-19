// 선택 모달 열기
function openConfirmModal() {
    const modalContainer = document.getElementById('modalContainer');
    const confirmModal = document.getElementById('confirmModal');

    setTimeout(() => {
        modalContainer.classList.remove('hidden');
        confirmModal.classList.remove('hidden');
    }, 10);

    setTimeout(() => {
        confirmModal.classList.remove('opacity-0', 'translate-y-4');
        confirmModal.classList.add('opacity-100', 'translate-y-0');
    }, 20);
}


// 선택 모달 닫기
function closeConfirmModal() {
    const confirmModal = document.getElementById('confirmModal');

    setTimeout(() => {
        confirmModal.classList.remove('opacity-100', 'translate-y-0');
        confirmModal.classList.add('opacity-0', '-translate-y-4');
    }, 10);

    setTimeout(() => {
        confirmModal.classList.add('hidden');
    }, 20);
}


// 완료 모달
function showSuccessModal() {
    const modalContainer = document.getElementById('modalContainer');
    const successModal = document.getElementById('successModal');

    setTimeout(() => {
        successModal.classList.remove('hidden');
    }, 10);

    setTimeout(() => {
        successModal.classList.remove('opacity-0', 'translate-y-0');
        successModal.classList.add('opacity-100', '-translate-y-4');
    }, 20);

    setTimeout(() => {
        successModal.classList.remove('opacity-100', '-translate-y-4');
        successModal.classList.add('opacity-0', '-translate-y-8');
    }, 1000);

    setTimeout(() => {
        successModal.classList.add('hidden');
        modalContainer.classList.add('hidden');
    }, 1500);
}


// 선택 모달 확인 버튼
/* 그곳에서 
document.getElementById('confirmButton').addEventListener('click', function () {
    closeConfirmModal();
    함수(selectedNickname);
});
이렇게 지정해야함, 아직 미숙한 부분, 해결요망*/


// 선택 모달 취소 버튼
document.getElementById('cancelButton').addEventListener('click', function () {
    closeConfirmModal();
    const modalContainer = document.getElementById('modalContainer');
    modalContainer.classList.add('hidden');
});


// 애니메이션 끝난 모달창 위치초기화
function initModalPosition() {
    const confirmModal = document.getElementById('confirmModal');
    const successModal = document.getElementById('successModal');

    confirmModal.classList.remove('-translate-y-4');
    successModal.classList.remove('-translate-y-8');
    confirmModal.classList.add('translate-y-4');
    successModal.classList.add('translate-y-0');
}