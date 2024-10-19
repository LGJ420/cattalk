function showSuccessModal() {

    const modalContainer = document.getElementById('modalContainer');
    const successModal = document.getElementById('successModal');

    setTimeout(() => {
        modalContainer.classList.remove('hidden');
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
        window.location.reload();
    }, 1500);
}