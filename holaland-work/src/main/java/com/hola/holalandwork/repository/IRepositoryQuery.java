package com.hola.holalandwork.repository;

public interface IRepositoryQuery {

    String WORK_REQUEST_FIND_JOB_GET_ALL = "SELECT * FROM work_request_find_job WHERE work_request_find_job_deleted = 0";
    String WORK_REQUEST_FIND_JOB_GET_ALL_BY_TYPE = "SELECT * FROM work_request_find_job WHERE work_job_type_id = ? AND work_request_find_job_deleted = 0";
    String WORK_REQUEST_FIND_JOB_GET_ONE = "SELECT * FROM work_request_find_job WHERE work_request_find_job_id = ? AND work_request_find_job_deleted = 0";

    String WORK_REQUEST_RECRUITMENT_GET_ALL = "SELECT * FROM work_request_recruitment WHERE work_request_recruitment_deleted = 0";
    String WORK_REQUEST_RECRUITMENT_GET_ALL_BY_TYPE = "SELECT * FROM work_request_recruitment WHERE work_job_type_id = ? AND stt_work_code = ? AND work_request_recruitment_deleted = 0";
    String WORK_REQUEST_RECRUITMENT_GET_ONE = "SELECT * FROM work_request_recruitment WHERE work_request_recruitment_id = ? AND work_request_recruitment_deleted = 0";

    String WORK_REQUEST_APPLY_GET_ALL = "SELECT * FROM work_request_apply WHERE work_request_apply_deleted = 0";
    String WORK_REQUEST_APPLY_GET_ONE = "SELECT * FROM work_request_apply WHERE work_request_apply_id = ? AND work_request_apply_deleted = 0";
    String WORK_REQUEST_APPLY_GET_ALL_BY_ACCOUNT_ID = "SELECT\n" +
            "       T1.work_request_recruitment_id,\n" +
            "       T1.user_id,\n" +
            "       T1.work_payment_method_id,\n" +
            "       T1.work_job_type_id,\n" +
            "       T1.stt_work_code,\n" +
            "       T1.work_salary_unit_id,\n" +
            "       T1.work_request_recruitment_title,\n" +
            "       T1.work_request_recruitment_start_date_time,\n" +
            "       T1.work_request_recruitment_end_date_time,\n" +
            "       T1.work_request_recruitment_last_update_date_time,\n" +
            "       T1.work_request_recruitment_description,\n" +
            "       T1.work_request_recruitment_requirements,\n" +
            "       T1.work_request_recruitment_benefits,\n" +
            "       T1.work_request_recruitment_salary,\n" +
            "       T1.work_request_recruitment_quantity,\n" +
            "       T1.work_request_recruitment_experience_required,\n" +
            "       T1.work_request_recruitment_gender_required,\n" +
            "       T1.work_request_recruitment_work_location,\n" +
            "       T1.work_request_recruitment_deleted\n" +
            "FROM work_request_recruitment T1\n" +
            "LEFT OUTER JOIN work_request_apply T2\n" +
            "ON T1.work_request_recruitment_id = T2.work_request_recruitment_id\n" +
            "WHERE T2.user_id = ?\n" +
            "AND T1.work_request_recruitment_deleted = 0";

    String WORK_JOB_SAVE_GET_ALL = "SELECT * FROM work_job_save WHERE work_job_save_deleted = 0";
    String WORK_JOB_SAVE_GET_ONE = "SELECT * FROM work_job_save WHERE work_job_save_id = ? AND work_job_save_deleted = 0";
    String WORK_JOB_SAVE_GET_ALL_BY_ACCOUNT_ID = "SELECT\n" +
            "       T1.work_request_recruitment_id,\n" +
            "       T1.user_id,\n" +
            "       T1.work_payment_method_id,\n" +
            "       T1.work_job_type_id,\n" +
            "       T1.stt_work_code,\n" +
            "       T1.work_salary_unit_id,\n" +
            "       T1.work_request_recruitment_title,\n" +
            "       T1.work_request_recruitment_start_date_time,\n" +
            "       T1.work_request_recruitment_end_date_time,\n" +
            "       T1.work_request_recruitment_last_update_date_time,\n" +
            "       T1.work_request_recruitment_description,\n" +
            "       T1.work_request_recruitment_requirements,\n" +
            "       T1.work_request_recruitment_benefits,\n" +
            "       T1.work_request_recruitment_salary,\n" +
            "       T1.work_request_recruitment_quantity,\n" +
            "       T1.work_request_recruitment_experience_required,\n" +
            "       T1.work_request_recruitment_gender_required,\n" +
            "       T1.work_request_recruitment_work_location,\n" +
            "       T1.work_request_recruitment_deleted\n" +
            "FROM work_request_recruitment T1\n" +
            "LEFT OUTER JOIN work_job_save T2\n" +
            "ON T1.work_request_recruitment_id = T2.work_request_recruitment_id\n" +
            "WHERE T2.user_id = ?\n" +
            "AND T1.work_request_recruitment_deleted = 0";

    String WORK_REQUEST_BOOK_GET_ALL = "SELECT * FROM work_request_book WHERE work_request_book_deleted = 0";
    String WORK_REQUEST_BOOK_GET_ONE = "SELECT * FROM work_request_book WHERE work_request_book_id = ? AND work_request_book_deleted = 0";

    String WORK_PAYMENT_METHOD_GET_ALL = "SELECT * FROM work_payment_method WHERE work_payment_method_deleted = 0";
    String WORK_PAYMENT_METHOD_GET_ONE = "SELECT * FROM work_payment_method WHERE work_payment_method_id = ? AND work_payment_method_deleted = 0";

    String WORK_TIME_GET_ALL = "SELECT * FROM work_time WHERE work_time_deleted = 0";
    String WORK_TIME_GET_ONE = "SELECT * FROM work_time WHERE work_time_id = ? AND work_time_deleted = 0";

    String WORK_REPORT_GET_ALL = "SELECT * FROM work_report";
    String WORK_REPORT_GET_ONE = "SELECT * FROM work_report WHERE work_report_id = ?";

    String WORK_JOB_TYPE_GET_ALL = "SELECT * FROM work_job_type WHERE work_job_type_deleted = 0";
    String WORK_JOB_TYPE_GET_ONE = "SELECT * FROM work_job_type WHERE work_job_type_id = ? AND work_job_type_deleted = 0";

    String WORK_COMMENT_GET_ALL = "SELECT * FROM work_comment WHERE work_comment_deleted = 0";
    String WORK_COMMENT_GET_ONE = "SELECT * FROM work_comment WHERE work_comment_id = ? AND work_comment_deleted = 0";

    String STT_WORK_GET_ALL = "SELECT * FROM stt_work";
    String STT_WORK_GET_ONE = "SELECT * FROM stt_work WHERE stt_work_id = ?";

    String WORK_REPORT_REASON_GET_ALL = "SELECT * FROM work_report_reason";
    String WORK_REPORT_REASON_GET_ONE = "SELECT * FROM work_report_reason WHERE work_report_reason_id = ?";

    String WORK_SALARY_UNIT_GET_ALL = "SELECT * FROM work_salary_unit";
    String WORK_SALARY_UNIT_GET_ONE = "SELECT * FROM work_salary_unit WHERE work_salary_unit_id = ?";
}
