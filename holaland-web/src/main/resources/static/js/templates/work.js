/*
 *
 */

function openModal(modal) {
  const myModal = new bootstrap.Modal(document.getElementById(modal));
  myModal.show();
}

//Recruitment manage


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

function confirmRemoveRecruitmentRequest(e) {
  const requestId = e.target.firstElementChild.innerHTML;
  let url = new URL(window.location.href);
  let sttWorkCode = 1;
  if(url.searchParams.has("code")) {
    sttWorkCode = url.searchParams.get("code");
  }
  openModal("confirmRemoveRecruitmentRequestModal");

  document.getElementById("btn-delete-recruitment-request").href =
      "/works/request-recruitment-manage/delete?requestId=" + requestId + "&code=" + sttWorkCode;
}

function confirmRemoveFindRequest(e) {
  const requestId = e.target.firstElementChild.innerHTML;
  let url = new URL(window.location.href);
  let sttWorkCode = 1;
  if(url.searchParams.has("code")) {
    sttWorkCode = url.searchParams.get("code");
  }
  openModal("confirmRemoveFindRequestModal");

  document.getElementById("btn-delete-find-request").href =
      "/works/request-find-job-manage/delete?requestId=" + requestId + "&code=" + sttWorkCode;
}