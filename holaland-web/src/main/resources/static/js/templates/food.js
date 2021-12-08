/*
 *
 */
document.getElementById("food-cart").className = "navbar-btn dropdown";

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
    request.open("GET", "/food/cart/add?storeId=" + storeId + "&foodId=" + foodId, true);
    request.onload = function () {
        if (this.readyState === 4 && this.status === 200) {
            showToast("toastAddToCartSuccess");
        } else if (this.status === 400) {
            showToast("toastAddToCartErrorDifferentStore");
        } else {
            showToast("toastAddToCartError");
        }
    };
    request.send(null);
}

/*
 * Online store
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

function getUserRated(storeId) {
    const request = new XMLHttpRequest();
    request.open("GET", "/food/store/rated?storeId=" + storeId, true);
    request.onload = function () {
        if (this.readyState === 4 && this.status === 200) {
            const obj = JSON.parse(this.responseText);

            if (obj.foodStoreOnlineRatePoint === 1) {
                document.getElementById("one-star").checked = true;
            } else if (obj.foodStoreOnlineRatePoint === 2) {
                document.getElementById("two-star").checked = true;
            } else if (obj.foodStoreOnlineRatePoint === 3) {
                document.getElementById("three-star").checked = true;
            } else if (obj.foodStoreOnlineRatePoint === 4) {
                document.getElementById("four-star").checked = true;
            } else {
                document.getElementById("five-star").checked = true;
            }
            document.getElementById("rateId").value = obj.foodStoreOnlineRateId;
            document.getElementById("storeId").value = storeId;
            document.getElementById("rate-content").value = obj.foodStoreOnlineRateComment;
            openModal("onlineStoreRateModal");
        } else {
            showToast("toastRateStoreError");
        }
    };
    request.send(null);
}

function checkUserOrderInStore(e) {
    const storeId = e.target.firstElementChild.innerHTML;

    const request = new XMLHttpRequest();
    request.open("GET", "/food/store/exits?storeId=" + storeId, true);
    request.onload = function () {
        if (this.readyState === 4 && this.status === 200) {
            document.getElementById("storeId").value = storeId;
            openModal("onlineStoreRateModal");
        } else if (this.status === 409) {
            // rate lần 2
            getUserRated(storeId);
        } else if (this.status === 404) {
            showToast("toastRateStoreWarning");
        } else if (this.status === 401) {
            showToast("toastRateStoreByOwnerWarning")
        } else {
            showToast("toastRateStoreError");
        }
    };
    request.send(null);
}

/*
 * Order food
 */
function confirmDeleteReport(e) {
    const foodReportId = e.target.firstElementChild.innerHTML;
    document.getElementById("btn-delete-report").href = "/food/order/report/delete?reportId=" + foodReportId;
    openModal("confirmDeleteReportOrderModal");
}

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

function getOrderReportContent(e) {
    const reportContent = e.target.firstElementChild.innerHTML;
    document.getElementById("reportContent").innerHTML = reportContent;
    openModal("reportOrderContentModal");
}

function confirmCancelOrder(e) {
    const orderId = e.target.firstElementChild.innerHTML;
    document.getElementById("btn-cancel-order").href = "/food/order/cancel?orderId=" + orderId;
    openModal("confirmCancelOrderModal");
}

function confirmCompleteOrder(e) {
    const orderId = e.target.firstElementChild.innerHTML;
    document.getElementById("btn-confirm-complete-order").href = "/food/order/complete?orderId=" + orderId;
    openModal("confirmOrderModal");
}

/*
 * Store online manage
 */
function confirmDeleteFood(e) {
    const foodId = e.target.firstElementChild.innerHTML;
    document.getElementById("btn-delete-food").href = "/store/manage/food/delete?foodId=" + foodId;
    openModal("confirmDeleteFoodModal");
}


