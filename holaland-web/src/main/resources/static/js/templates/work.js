/*
 *
 */

function openModal(modal) {
  const myModal = new bootstrap.Modal(document.getElementById(modal));
  myModal.show();
}

function confirmDeleteRequestApply(e) {
  console.log(e);
  const requestId = e.target.lastElementChild.innerHTML;
  openModal("confirmCancelApplyJobModal");
  document.getElementById("btn-delete-request-apply").href = "/works/jobs-apply/delete?requestId=" + requestId;
}