/*
 *
 */

function openModal(modal) {
  const myModal = new bootstrap.Modal(document.getElementById(modal));
  myModal.show();
}

//Recruitment manage
function confirmDeleteRequestRecruitmentPendingApproval(e) {
  const requestId = e.target.firstElementChild.innerHTML;
  openModal("confirmRemoveRequestRecruitmentPendingApprovalModal");
  document.getElementById("btn-delete-request-recruitment-pending-approval")
      .href = "/works/request-recruitment-manage/delete?requestId=" + requestId + "&sttWorkCode=" + sttWorkCode;
}

function confirmUndoRequestRecruitmentPendingApproval(e) {
  const requestId = e.target.firstElementChild.innerHTML;
  openModal("confirmRemoveJobSaveModal");
  document.getElementById("btn-undo-request-recruitment_pending_approval")
      .href = "/works/request-recruitment-manage/Undo?requestId=" + requestId + "&sttWorkCode=" + sttWorkCode;
}

function confirmReasonRequestRecruitmentReject(e) {
  const requestId = e.target.firstElementChild.innerHTML;
  openModal("confirmRemoveJobSaveModal");
  document.getElementById("btn-reason-request-recruitment-reject")
      .href = "/works/request-recruitment-manage/Reason?requestId=" + requestId + "&sttWorkCode=" + sttWorkCode;
}

function confirmEditRequestRecruitmentReject(e) {
  const requestId = e.target.firstElementChild.innerHTML;
  openModal("confirmRemoveJobSaveModal");
  document.getElementById("btn-edit-request-recruitment-reject")
      .href = "/works/request-recruitment-manage/edit?requestId=" + requestId + "&sttWorkCode=" + sttWorkCode;
}

function confirmDeleteRequestRecruitmentReject(e) {
  const requestId = e.target.firstElementChild.innerHTML;
  openModal("confirmRemoveRequestRecruitmentRejectModal");
  document.getElementById("btn-delete-request-recruitment-reject")
      .href = "/works/request-recruitment-manage/delete?requestId=" + requestId + "&sttWorkCode=" + sttWorkCode;
}

function confirmDeleteRequestRecruitmentApproved(e) {
  const requestId = e.target.firstElementChild.innerHTML;
  openModal("confirmRemoveRequestRecruitmentApprovesModal");
  document.getElementById("btn-delete-request-recruitment-approved")
      .href = "/works/request-recruitment-manage/delete?requestId=" + requestId + "&sttWorkCode=" + sttWorkCode;
}

function confirmUndoRequestRecruitmentComplete(e) {
  const requestId = e.target.firstElementChild.innerHTML;
  openModal("confirmRemoveJobSaveModal");
  document.getElementById("btn-undo-request-recruitment-complete")
      .href = "/works/request-recruitment-manage/undo?requestId=" + requestId + "&sttWorkCode=" + sttWorkCode;
}

function confirmDeleteRequestRecruitmentComplete(e) {
  const requestId = e.target.firstElementChild.innerHTML;
  openModal("confirmRemoveRequestRecruitmentCompleteModal");
  document.getElementById("btn-delete-request-recruitment-complete")
      .href = "/works/request-recruitment-manage/delete?requestId=" + requestId + "&sttWorkCode=" + sttWorkCode;
}

function confirmUndoRequestRecruitmentExpired(e) {
  const requestId = e.target.firstElementChild.innerHTML;
  openModal("confirmRemoveJobSaveModal");
  document.getElementById("btn-undo-request-recruitment-expired")
      .href = "/works/request-recruitment-manage/undo?requestId=" + requestId + "&sttWorkCode=" + sttWorkCode;
}

function confirmDeleteRequestRecruitmentExpired(e) {
  const requestId = e.target.firstElementChild.innerHTML;
  openModal("confirmRemoveRequestRecruitmentExpiredModal");
  document.getElementById("btn-delete-request-recruitment-expired")
      .href = "/works/request-recruitment-manage/delete?requestId=" + requestId + "&sttWorkCode=" + sttWorkCode;
}

function confirmEditRequestRecruitmentSaveDraft(e) {
  const requestId = e.target.firstElementChild.innerHTML;
  openModal("confirmRemoveJobSaveModal");
  document.getElementById("btn-edit-request-recruitment-save-draft")
      .href = "/works/request-recruitment-manage/edit?requestId=" + requestId + "&sttWorkCode=" + sttWorkCode;
}

function confirmDeleteRequestRecruitmentSaveDraft(e) {
  const requestId = e.target.firstElementChild.innerHTML;
  openModal("confirmRemoveRequestRecruitmentSaveDraftModal");
  document.getElementById("btn-delete-request-recruitment-save-draft")
      .href = "/works/request-recruitment-manage/delete?requestId=" + requestId + "&sttWorkCode=" + sttWorkCode;
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

function x(e) {
  //const requestId = e.target.firstElementChild.innerHTML;

  //
  var url = new URL(window.location.href);
  const sttWorkCode = url.searchParams.get("code");

  //openModal("confirmCancelApplyJobModal");

  //document.getElementById("btn-delete-request-apply").href = "/works/jobs-apply/delete?requestId=" + requestId + "&sttWorkCode=" + sttWorkCode;
}