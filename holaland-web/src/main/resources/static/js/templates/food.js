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
 * Add to cart
 */
function addFoodToCart(e) {
    const foodId = e.target.firstElementChild.innerHTML;
    const storeId = e.target.lastElementChild.innerHTML;

    const request = new XMLHttpRequest();
    request.open("GET", "/food/order/add-to-cart?storeId=" + storeId + "&foodId=" + foodId, true);
    request.onload = function () {
        if (this.readyState === 4 && this.status === 200) {
            console.log("OK");
            console.log(this.responseText);
            showToast("toastAddToCartSuccess");
        } else {
            showToast("toastAddToCartError");
        }
    };
    request.send(null);
}

/*
 * Online store food item
 */
function getFoodItemDetail(e) {
    const arr = e.target.id.split("-");
    const foodItemId = arr[arr.length - 1];
    const foodItemImg = document.getElementById("food-item-img-" + foodItemId).src;
    const foodItemName = document.getElementById("food-item-name-" + foodItemId).innerHTML;
    const foodItemPrice = document.getElementById("food-item-price-" + foodItemId).innerHTML;

    document.getElementById("food-item-img-modal").src = foodItemImg;
    document.getElementById("food-item-name-modal").innerHTML = foodItemName;
    document.getElementById("food-item-price-modal").innerHTML = foodItemPrice;
    openModal("foodDetailModal");
}

/*
 * Order food
 */
function reportFoodOrder(e) {
    const foodOrderId = e.target.firstElementChild.innerHTML;
    const storeId = e.target.lastElementChild.innerHTML;

    document.getElementById("orderId").value = foodOrderId;
    document.getElementById("storeId").value = storeId;
    openModal("reportFoodOrderModal");
}

function rejectFoodOrder(e) {
    const foodOrderId = e.target.firstElementChild.innerHTML;

    document.getElementById("orderRejectId").value = foodOrderId;
    openModal("rejectOrderModal");
}

function getOrderReasonReject(e) {
    const reasonReject = e.target.firstElementChild.innerHTML;
    document.getElementById("orderReasonReject").innerHTML = reasonReject;
    openModal("reasonRejectionOrderModal");
}

function getOrderNote(e) {
    const note = e.target.firstElementChild.innerHTML;
    document.getElementById("orderNote").innerHTML = note;
    openModal("noteOrderModal");
}

/*
 * Store online manage
 */
function confirmDeleteFood(e) {
    const foodId = e.target.firstElementChild.innerHTML;
    document.getElementById("btn-delete-food").href = "/store/manage-food/delete?foodId=" + foodId;
    openModal("confirmDeleteFoodModal");
}

function confirmCancelOrder(e) {
    const orderId = e.target.firstElementChild.innerHTML;
    document.getElementById("btn-cancel-order").href = "/food/order/updateSttFood?orderId=" + orderId;
    openModal("confirmCancelOrderModal");
}

