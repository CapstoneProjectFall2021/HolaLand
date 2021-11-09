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
 * Recruitment manage
 */
function confirmDeleteRequestApply(e) {
  const requestId = e.target.firstElementChild.innerHTML;
  openModal("confirmCancelApplyJobModal");
  document.getElementById("btn-delete-request-apply").href = "/works/jobs-apply/delete?requestId=" + requestId;
}

function confirmDeleteRequestSave(e) {
  const requestId = e.target.firstElementChild.innerHTML;
  openModal("confirmRemoveJobSaveModal");
  document.getElementById("btn-delete-request-save").href = "/works/jobs-save/delete?requestId=" + requestId;
}

function getIdOfRequestBooked(e) {
  const bookedId = e.target.firstElementChild.innerHTML;
  openModal("listBookedModal");
  document.getElementById("btn-show-list-booked").href = "/works/booked/show?bookedId=" + bookedId;
}

function getIdOfRequestApplied(e) {
  const appliedId = e.target.firstElementChild.innerHTML;
  openModal("listAppliedModal");
  document.getElementById("btn-show-list-user-applied").href = "/works/applied/show?appliedId=" + appliedId;
}

function confirmRemoveRecruitmentRequest(e) {
  const requestId = e.target.firstElementChild.innerHTML;
  let url = new URL(window.location.href);
  let sttWorkCode = 1;
  if (url.searchParams.has("code")) {
    sttWorkCode = url.searchParams.get("code");
  }

  openModal("confirmRemoveRecruitmentRequestModal");
  document.getElementById("btn-delete-recruitment-request").href =
    "/works/request-recruitment-manage/delete?requestId=" + requestId + "&code=" + sttWorkCode;
}

function confirmRemoveFindJobRequest(e) {
  const requestId = e.target.firstElementChild.innerHTML;
  let url = new URL(window.location.href);
  let sttWorkCode = 1;
  if (url.searchParams.has("code")) {
    sttWorkCode = url.searchParams.get("code");
  }

  openModal("confirmRemoveFindJobRequestModal");
  document.getElementById("btn-delete-find-request").href =
    "/works/request-find-job-manage/delete?requestId=" + requestId + "&code=" + sttWorkCode;
}

/*
 * Recruitment manage
 */
function saveRequestRecruitment() {
  const request = new XMLHttpRequest();
  request.open("GET", "https://anest-hl-backend.herokuapp.com/course/1", false);
  request.onreadystatechange = function () {
    if (this.readyState === 4 && this.status === 200) {
      // document.getElementById("result").innerHTML = this.responseText;
      setStateBtnSaveJob();
      showToast('toast-success');
    } else {
      alert("ERROR");
    }
  };
  request.send();
}

function setStateBtnSaveJob() {
  document.getElementById("label-save-request").innerHTML = "ĐÃ LƯU";
  document.getElementById("icon-save-request").classList.remove('far');
  document.getElementById("icon-save-request").classList.add('fas');
  document.getElementById("btn-save-request").onclick = function (e) {
    e.defaultPrevented();
  }
}