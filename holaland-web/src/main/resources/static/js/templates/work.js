/*
 *
 */
function openModal(modal) {
  const myModal = new bootstrap.Modal(document.getElementById(modal));
  myModal.show();
}

function closeModal(modal) {
  const myModal = new bootstrap.Modal(document.getElementById(modal));
  myModal.dispose();
}

function showToast(id) {
  const toast = new bootstrap.Toast(document.getElementById(id));
  toast.show();
}

/*
 * Recruitment
 */
function confirmDeleteRequestApply(requestId) {
  document.getElementById("btn-delete-request-apply").href = "/works/jobs/applied/delete?requestId=" + requestId;
  openModal("confirmCancelApplyJobModal");
}

function getIdOfRequestBooked(bookedId) {
  document.getElementById("btn-show-list-booked" + bookedId).href = "/works/booked/show?bookedId=" + bookedId;
}

function getIdOfRequestApplied(appliedId) {
  document.getElementById("btn-show-list-user-applied" + appliedId).href = "/works/apply/show?appliedId=" + appliedId;
}

function confirmRemoveRecruitmentRequest(requestId) {
  let url = new URL(window.location.href);
  let sttWorkCode = 1;
  if (url.searchParams.has("code")) {
    sttWorkCode = url.searchParams.get("code");
  }

  document.getElementById("btn-delete-recruitment-request").href =
      "/works/jobs/recruitment/manage/delete?requestId=" + requestId + "&code=" + sttWorkCode;
  openModal("confirmRemoveRecruitmentRequestModal");

}

function confirmRemoveFindJobRequest(requestId) {
  let url = new URL(window.location.href);
  let sttWorkCode = 1;
  if (url.searchParams.has("code")) {
    sttWorkCode = url.searchParams.get("code");
  }

  document.getElementById("btn-delete-find-request").href =
      "/works/jobs/find/manage/delete?requestId=" + requestId + "&code=" + sttWorkCode;
  openModal("confirmRemoveFindJobRequestModal");

}

/*
 * User
 */
function confirmDeleteRequestSave(requestId) {
  document.getElementById("btn-delete-request-save").href = "/works/jobs/saved/delete?requestId=" + requestId;
  openModal("confirmRemoveJobSaveModal");
}

function saveRequestRecruitment(requestId) {
  const request = new XMLHttpRequest();
  request.open("GET", "/works/jobs/save?requestId=" + requestId, true);
  request.onload = function () {
    if (this.readyState === 4 && this.status === 200) {
      setStateBtnSaveJob();
      showToast('toastRequestRecruitmentSaveSuccess');
    } else {
      showToast('toastRequestRecruitmentSaveError');
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

function confirmApplyRequestRecruitment(requestId) {
  document.getElementById("btn-apply-now").onclick = function() {
    applyRequestRecruitment(requestId);
  }
  document.getElementById("btn-apply-now").setAttribute("data-bs-dismiss","modal");
  openModal("confirmApplyRequestModal");
}

function applyRequestRecruitment(requestId) {
  const request = new XMLHttpRequest();
  request.open("GET", "/works/jobs/apply?requestId=" + requestId, true);
  request.onload = function () {
    if (this.readyState === 4 && this.status === 200) {
      setStateBtnApplyJob();
      showToast('toastRequestRecruitmentApplySuccess');
    } else {
      showToast('toastRequestRecruitmentApplyError');
    }
  };
  request.send();
}

function setStateBtnApplyJob() {
  document.getElementById("label-apply-request").innerHTML = "ĐÃ ỨNG TUYỂN";
  document.getElementById("btn-apply-request").onclick = function (e) {
    e.defaultPrevented();
  }
}

/*
 * Recruiter
 */

function confirmHireRequestFindJob(requestId) {
  document.getElementById("btn-book-now").onclick = function() {
    bookRequestFindJob(requestId);
  }
  document.getElementById("btn-book-now").setAttribute("data-bs-dismiss","modal");
  openModal("confirmHireModal");
}

function bookRequestFindJob(requestId) {
  const request = new XMLHttpRequest();
  request.open("GET", "/works/jobs/book?requestId=" + requestId, true);
  request.onload = function () {
    if (this.readyState === 4 && this.status === 200) {
      setStateBtnBookUser();
      showToast('toastRequestFindJobBookSuccess');
    } else {
      showToast('toastRequestFindJobBookError');
    }
  };
  request.send();
}

function setStateBtnBookUser() {
  document.getElementById("label-book-request").innerHTML = "ĐÃ THUÊ";
  document.getElementById("btn-book-request").onclick = function (e) {
    e.defaultPrevented();
  }
}