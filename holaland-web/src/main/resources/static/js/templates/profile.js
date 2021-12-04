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
 * Address
 */
function confirmDeleteUserAddress(e) {
    const addressId = e.target.firstElementChild.innerHTML;
    document.getElementById("btn-delete-address").href = "/address/delete?addressId="+ addressId;
    openModal("confirmDeleteAddressModal");
}