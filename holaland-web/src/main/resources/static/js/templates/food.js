/*
 * food.js
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
function addFoodToCart(foodId, storeId) {
    const request = new XMLHttpRequest();
    request.open("GET", "/food/cart/add?storeId=" + storeId + "&foodId=" + foodId, true);
    request.onload = function () {
        if (this.readyState === 4 && this.status === 200) {
            document.getElementById("numberFoodInCart").innerHTML = this.responseText;
            showToast("toastAddToCartSuccess");
        } else if (this.status === 401) {
            showToast("toastAddToCartByOwnerWarning");
        } else if (this.status === 400) {
            showToast("toastAddToCartDifferentStoreError");
        } else {
            showToast("toastAddToCartError");
        }
    };
    request.send(null);
}

function confirmDeleteItemInCart(foodId) {
    document.getElementById("btn-delete-item-in-cart").href = "/food/cart/delete/item?foodId=" + foodId;
    openModal("confirmDeleteItemInCartModal");
}

/*
 * Online store
 */
function getFoodItemDetail(foodItemId, storeId) {
    const foodItemImg = document.getElementById("food-item-img-" + foodItemId).src;
    const foodItemName = document.getElementById("food-item-name-" + foodItemId).innerHTML;
    const foodItemPrice = document.getElementById("food-item-price-" + foodItemId).innerHTML;

    document.getElementById("food-item-img-modal").src = foodItemImg;
    document.getElementById("food-item-name-modal").innerHTML = foodItemName;
    document.getElementById("food-item-price-modal").innerHTML = foodItemPrice;
    document.getElementById("food-item-add-to-cart").onclick = function () {
        addFoodToCart(foodItemId, storeId);
    };
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
            document.getElementById("rate-id").value = obj.foodStoreOnlineRateId;
            document.getElementById("store-id").value = storeId;
            document.getElementById("rate-content").value = obj.foodStoreOnlineRateComment;
            openModal("onlineStoreRateModal");
        } else {
            showToast("toastRateStoreError");
        }
    };
    request.send(null);
}

function checkUserOrderInStore(storeId) {
    const request = new XMLHttpRequest();
    request.open("GET", "/food/store/exits?storeId=" + storeId, true);
    request.onload = function () {
        if (this.readyState === 4 && this.status === 200) {
            document.getElementById("store-id").value = storeId;
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

function getOrderReasonReject(e) {
    const reasonReject = e.target.firstElementChild.innerHTML;
    document.getElementById("orderReasonReject").innerHTML = reasonReject;
    openModal("reasonRejectionOrderModal");
}

function getOrderReportContent(e) {
    const reportContent = e.target.firstElementChild.innerHTML;
    document.getElementById("reportContent").innerHTML = reportContent;
    openModal("reportOrderContentModal");
}

/* Seller order */

function rejectFoodOrder(foodOrderId) {
    document.getElementById("orderRejectId").value = foodOrderId;
    openModal("rejectOrderModal");
}

function getOrderNote(e) {
    const note = e.target.firstElementChild.innerHTML;
    document.getElementById("orderNote").innerHTML = note;
    openModal("noteOrderModal");
}

/* User order */
function confirmCancelOrder(orderId) {
    document.getElementById("btn-cancel-order").href = "/food/order/cancel?orderId=" + orderId;
    openModal("confirmCancelOrderModal");
}

function reportFoodOrder(orderId, storeId) {
    document.getElementById("order-id").value = orderId;
    document.getElementById("store-id").value = storeId;
    openModal("reportFoodOrderModal");
}

function confirmDeleteReport(foodReportId) {
    document.getElementById("btn-delete-report").href = "/food/order/report/delete?reportId=" + foodReportId;
    openModal("confirmDeleteReportOrderModal");
}

function confirmCompleteOrder(orderId) {
    document.getElementById("btn-confirm-complete-order").href = "/food/order/user/complete?orderId=" + orderId;
    openModal("confirmOrderModal");
}

/*
 * Store online manage
 */
function confirmDeleteFood(foodId) {
    document.getElementById("btn-delete-food").href = "/store/manage/food/delete?foodId=" + foodId;
    openModal("confirmDeleteFoodModal");
}

function updateFood(foodItemId, foodItemTagId) {
    const foodItemImg = document.getElementById("food-item-img-" + foodItemId).src;
    const foodItemName = document.getElementById("food-item-name-" + foodItemId).innerHTML;
    const foodItemPrice = document.getElementById("food-item-price-" + foodItemId).innerHTML;

    document.getElementById("food-item-id").value = foodItemId;
    document.getElementById("item-tag-id").value = foodItemTagId;
    document.getElementById('image').src = foodItemImg;
    document.getElementById("item-name").value = foodItemName;
    document.getElementById("item-price").value = parseInt(foodItemPrice.replace('.', ''));
    openModal("updateFoodItemModal");
}

function storePauseSelling(checkboxElem) {
    const arr = checkboxElem.id.split("-");
    const storeId = arr[arr.length - 1];
    const request = new XMLHttpRequest();
    request.open("POST", "/store/sell/pause?storeId="+storeId+"&isPause="+checkboxElem.checked, true);
    request.onload = function () {
        if (this.readyState === 4 && this.status === 200) {
            showToast("toastStoreChangeStatusSuccess");
        } else {
            showToast("toastError");
        }
    };
    request.send(null);
}

function storeStopSelling(checkboxElem) {
    const arr = checkboxElem.id.split("-");
    const storeId = arr[arr.length - 1];
    const request = new XMLHttpRequest();
    request.open("POST", "/store/sell/stop?storeId="+storeId+"&isStop="+checkboxElem.checked, true);
    request.onload = function () {
        if (this.readyState === 4 && this.status === 200) {
            showToast("toastStoreChangeStatusSuccess");
        } else {
            showToast("toastError");
        }
    };
    request.send(null);
}


