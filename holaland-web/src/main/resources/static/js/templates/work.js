/*
 *
 */

function openModal(modal) {
  const myModal = new bootstrap.Modal(document.getElementById(modal));
  myModal.show();
}

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