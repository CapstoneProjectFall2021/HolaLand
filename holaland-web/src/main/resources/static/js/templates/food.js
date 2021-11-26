/*
 *
 */
function openModal(modal) {
    const myModal = new bootstrap.Modal(document.getElementById(modal));
    myModal.show();
}

function showToast(id) {
    const toast = new bootstrap.Toast(document.getElementById(id));
    toast.show();
}
/*
 * store online manage
 */
function confirmDeleteFood(e) {
    const foodId = e.target.firstElementChild.innerHTML;
    openModal("confirmDeleteFoodModal");
    document.getElementById("btn-delete-food").href = "/store/manage-food/delete?foodId=" + foodId;
}