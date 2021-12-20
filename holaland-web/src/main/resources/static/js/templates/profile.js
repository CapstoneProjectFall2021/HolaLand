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
 * Profile update
 */

function checkUserPassword() {
    const updatePassForm = document.getElementById('updateUserPasswordForm');
    const oldPass = updatePassForm.elements['oldPass'].value;
    const newPass = updatePassForm.elements['newPass'].value;
    const confirmNewPass = updatePassForm.elements['confirmNewPass'].value;
    const params = 'oldPass='+oldPass+'&newPass='+newPass;
    const request = new XMLHttpRequest();
    request.open("POST", "/profile/password/update?"+params, true);
    request.onload = function () {
        if(newPass != confirmNewPass) {
            showToast("toastCheckConfirmPassFailed");
        } else {
            if (this.readyState === 4 && this.status === 200) {
                showToast("toastUpdatePassSuccess");
            } else if (this.status === 417){
                showToast("toastCheckOldPassFailed");
            } else {
                showToast("toastError");
            }
        }
    };
    updatePassForm.reset();
    request.send(null);
}

/*
 * Address
 */
function confirmDeleteUserAddress(e) {
    const addressId = e.target.firstElementChild.innerHTML;
    document.getElementById("btn-delete-address").href = "/profile/address/delete?addressId="+ addressId;
    openModal("confirmDeleteAddressModal");
}