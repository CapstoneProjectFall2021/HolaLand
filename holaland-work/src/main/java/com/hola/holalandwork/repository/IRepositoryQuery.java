package com.hola.holalandwork.repository;

public interface IRepositoryQuery {

    String WORK_REQUEST_FIND_JOB_GET_ALL = "SELECT * FROM work_request_find_job WHERE work_request_find_job_deleted = 0";
    String WORK_REQUEST_FIND_JOB_GET_ALL_BY_TYPE = "SELECT * FROM work_request_find_job WHERE work_job_type_id = ? AND work_request_find_job_deleted = 0";
    String WORK_REQUEST_FIND_JOB_GET_ONE = "SELECT * FROM work_request_find_job WHERE work_request_find_job_id = ? AND work_request_find_job_deleted = 0";

    String WORK_REQUEST_RECRUITMENT_GET_ALL = "SELECT * FROM work_request_recruitment WHERE work_request_recruitment_deleted = 0";
    String WORK_REQUEST_RECRUITMENT_GET_ALL_BY_TYPE = "SELECT * FROM work_request_recruitment WHERE work_job_type_id = ? AND work_request_recruitment_deleted = 0";
    String WORK_REQUEST_RECRUITMENT_GET_ONE = "SELECT * FROM work_request_recruitment WHERE work_request_recruitment_id = ? AND work_request_recruitment_deleted = 0";

    String WORK_REQUEST_APPLY_GET_ALL = "SELECT * FROM work_request_apply WHERE work_request_apply_deleted = 0";
    String WORK_REQUEST_APPLY_GET_ONE = "SELECT * FROM work_request_apply WHERE work_request_apply_id = ? AND work_request_apply_deleted = 0";

    String WORK_JOBS_SAVE_GET_ALL = "SELECT * FROM work_jobs_save WHERE work_jobs_save_deleted = 0";
    String WORK_JOBS_SAVE_GET_ONE = "SELECT * FROM work_jobs_save WHERE work_jobs_save_id = ? AND work_jobs_save_deleted = 0";

    String WORK_REQUEST_BOOK_GET_ALL = "SELECT * FROM work_request_book WHERE work_request_book_deleted = 0";
    String WORK_REQUEST_BOOK_GET_ONE = "SELECT * FROM work_request_book WHERE work_request_book_id = ? AND work_request_book_deleted = 0";

    String WORK_SALARY_TYPE_GET_ALL = "SELECT * FROM work_salary_type WHERE work_salary_type_deleted = 0";
    String WORK_SALARY_TYPE_GET_ONE = "SELECT * FROM work_salary_type WHERE work_salary_type_id = ? AND work_salary_type_deleted = 0";

    String WORK_TIME_GET_ALL = "SELECT * FROM work_time WHERE work_time_deleted = 0";
    String WORK_TIME_GET_ONE = "SELECT * FROM work_time WHERE work_time_id = ? AND work_time_deleted = 0";

    String WORK_REPORT_GET_ALL = "SELECT * FROM work_report";
    String WORK_REPORT_GET_ONE = "SELECT * FROM work_report WHERE work_report_id = ?";

    String WORK_JOB_TYPE_GET_ALL = "SELECT * FROM work_job_type WHERE work_job_type_deleted = 0";
    String WORK_JOB_TYPE_GET_ONE = "SELECT * FROM work_job_type WHERE work_job_type_id = ? AND work_job_type_deleted = 0";

    String WORK_COMMENT_GET_ALL = "SELECT * FROM work_comment WHERE work_comment_deleted = 0";
    String WORK_COMMENT_GET_ONE = "SELECT * FROM work_comment WHERE work_comment_id = ? AND work_comment_deleted = 0";

    String STT_WORK_REQUEST_FIND_JOB_GET_ALL = "SELECT * FROM stt_work_request_find_job";
    String STT_WORK_REQUEST_FIND_JOB_GET_ONE = "SELECT * FROM stt_work_request_find_job WHERE stt_work_request_find_job_id = ?";

    String STT_WORK_REQUEST_RECRUITMENT_GET_ALL = "SELECT * FROM stt_work_request_recruitment";
    String STT_WORK_REQUEST_RECRUITMENT_GET_ONE = "SELECT * FROM stt_work_request_recruitment WHERE stt_work_request_recruitment_id = ?";

    String STT_WORK_REPORT_GET_ALL = "SELECT * FROM stt_work_report";
    String STT_WORK_REPORT_GET_ONE = "SELECT * FROM stt_work_report WHERE stt_work_report_id = ?";

    String STT_WORK_REQUEST_APPLY_GET_ALL = "SELECT * FROM stt_work_request_apply";
    String STT_WORK_REQUEST_APPLY_GET_ONE = "SELECT * FROM stt_work_request_apply WHERE stt_work_request_apply_id = ?";

    String STT_WORK_REQUEST_BOOK_GET_ALL = "SELECT * FROM stt_work_request_book";
    String STT_WORK_REQUEST_BOOK_GET_ONE = "SELECT * FROM stt_work_request_book WHERE stt_work_request_book_id = ?";

    String WORK_REPORT_REASON_GET_ALL = "SELECT * FROM work_report_reason";
    String WORK_REPORT_REASON_GET_ONE = "SELECT * FROM work_report_reason WHERE work_report_reason_id = ?";
}
