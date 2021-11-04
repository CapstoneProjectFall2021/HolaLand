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

function x(e) {
  //const requestId = e.target.firstElementChild.innerHTML;

  //
  var url = new URL(window.location.href);
  const sttWorkCode = url.searchParams.get("code");

  //openModal("confirmCancelApplyJobModal");

  //document.getElementById("btn-delete-request-apply").href = "/works/jobs-apply/delete?requestId=" + requestId + "&sttWorkCode=" + sttWorkCode;
}