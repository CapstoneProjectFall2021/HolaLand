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

function confirmCandelOrder(e) {
    const orderId = e.target.firstElementChild.innerHTML;
    openModal("confirmCancelOrderModal");
    document.getElementById("btn-cancel-order").href = "/food/user-order/updateSttFood?orderId=" + orderId;
}