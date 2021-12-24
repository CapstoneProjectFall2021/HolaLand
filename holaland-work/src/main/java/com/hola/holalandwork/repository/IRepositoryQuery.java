package com.hola.holalandwork.repository;

public interface IRepositoryQuery {

    String WORK_REQUEST_FIND_JOB_GET_ALL = "SELECT * FROM work_request_find_job WHERE work_request_find_job_deleted = 0";
    String WORK_REQUEST_FIND_JOB_GET_ALL_BY_TYPE = "SELECT * FROM work_request_find_job WHERE work_request_type_id = ? AND stt_work_code = ? AND work_request_find_job_deleted = 0";
    String WORK_REQUEST_FIND_JOB_GET_ONE = "SELECT * FROM work_request_find_job WHERE work_request_find_job_id = ? AND work_request_find_job_deleted = 0";
    String WORK_REQUEST_FIND_JOB_DELETED_ONE = "UPDATE work_request_find_job\n" +
            "SET work_request_find_job_deleted = 1\n" +
            "WHERE work_request_find_job_id = ?";
    String WORK_REQUEST_FIND_JOB_UPDATE_ONE = "UPDATE work_request_find_job\n" +
            "SET stt_work_code = ?, work_request_type_id = ?, work_salary_unit_id = ?, work_payment_method_id = ?, " +
            "work_time_id = ?, work_request_find_job_title = ?, work_request_find_job_end_date_time = ?, " +
            "work_request_find_job_last_update_date_time = ?, work_request_find_job_description = ?, " +
            "work_request_find_job_personal_experience = ?, work_request_find_job_expected_location = ?, " +
            "work_request_find_job_expected_salary = ?, work_request_find_job_note = ?\n" +
            "WHERE work_request_find_job_id = ?";
    String WORK_REQUEST_FIND_JOB_UPDATE_STT_ONE = "UPDATE work_request_find_job\n" +
            "SET stt_work_code = ?\n" +
            "WHERE work_request_find_job_id = ?";
    String WORK_REQUEST_FIND_JOB_SEARCH = "SELECT * FROM work_request_find_job WHERE work_request_find_job_title LIKE ? " +
            "AND stt_work_code = ? AND work_request_find_job_deleted = 0";

    String WORK_REQUEST_RECRUITMENT_GET_ALL = "SELECT * FROM work_request_recruitment WHERE work_request_recruitment_deleted = 0";
    String WORK_REQUEST_RECRUITMENT_GET_ALL_BY_TYPE = "SELECT * FROM work_request_recruitment WHERE work_request_type_id = ? AND stt_work_code = ? AND work_request_recruitment_deleted = 0";
    String WORK_REQUEST_RECRUITMENT_GET_ONE = "SELECT * FROM work_request_recruitment WHERE work_request_recruitment_id = ? AND work_request_recruitment_deleted = 0";
    String WORK_REQUEST_RECRUITMENT_DELETED_ONE = "UPDATE work_request_recruitment\n" +
            "SET work_request_recruitment_deleted = 1\n" +
            "WHERE work_request_recruitment_id = ?";
    String WORK_REQUEST_RECRUITMENT_UPDATE_ONE = "UPDATE work_request_recruitment\n" +
            "SET stt_work_code = ?, work_request_type_id = ?, work_salary_unit_id = ?, work_payment_method_id = ?, " +
            "work_request_recruitment_title = ?, work_request_recruitment_end_date_time = ?, " +
            "work_request_recruitment_last_update_date_time = ?, work_request_recruitment_description = ?, " +
            "work_request_recruitment_requirements = ?, work_request_recruitment_benefits = ?, " +
            "work_request_recruitment_salary = ?, work_request_recruitment_quantity = ?, " +
            "work_request_recruitment_experience_required = ?, work_request_recruitment_gender_required = ?, " +
            "work_request_recruitment_work_location = ?, work_request_recruitment_note = ?\n" +
            "WHERE work_request_recruitment_id = ?";
    String WORK_REQUEST_RECRUITMENT_UPDATE_STT_ONE = "UPDATE work_request_recruitment\n" +
            "SET stt_work_code = ?\n" +
            "WHERE work_request_recruitment_id = ?";
    String WORK_REQUEST_RECRUITMENT_SEARCH = "SELECT * FROM work_request_recruitment WHERE work_request_recruitment_title LIKE ? " +
            "AND stt_work_code = ? AND work_request_recruitment_deleted = 0";

    String WORK_REQUEST_APPLY_GET_ALL = "SELECT * FROM work_request_apply WHERE work_request_apply_deleted = 0";
    String WORK_REQUEST_APPLY_GET_ALL_BY_REQUEST_ID = "SELECT * FROM work_request_apply WHERE work_request_recruitment_id = ?\n" +
            "AND work_request_apply_deleted = 0";
    String WORK_REQUEST_APPLY_GET_ONE = "SELECT * FROM work_request_apply WHERE work_request_apply_id = ? AND work_request_apply_deleted = 0";
    String WORK_REQUEST_APPLY_GET_ALL_BY_ACCOUNT_ID = "SELECT\n" +
            "       T1.work_request_recruitment_id,\n" +
            "       T1.user_id,\n" +
            "       T1.work_payment_method_id,\n" +
            "       T1.work_request_type_id,\n" +
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
            "       T1.work_request_recruitment_note,\n" +
            "       T1.work_request_recruitment_deleted\n" +
            "FROM work_request_recruitment T1\n" +
            "LEFT OUTER JOIN work_request_apply T2\n" +
            "ON T1.work_request_recruitment_id = T2.work_request_recruitment_id\n" +
            "WHERE T2.user_id = ?\n" +
            "AND T1.work_request_recruitment_deleted = 0\n"+
            "AND T2.work_request_apply_deleted = 0";
    String INSERT_WORK_REQUEST_APPLY = "INSERT INTO work_request_apply (user_id, work_request_recruitment_id," +
            " stt_work_code, work_request_apply_deleted) VALUES(?, ?, ?, ?)";
    String WORK_REQUEST_APPLY_UPDATE_STT_ONE = "UPDATE work_request_apply\n" +
            "SET stt_work_code = ?\n" +
            "WHERE user_id = ? AND work_request_recruitment_id = ?";
    String WORK_REQUEST_APPLY_REJECT_STT_ALL = "UPDATE work_request_apply\n" +
            "SET stt_work_code = ?\n" +
            "WHERE work_request_recruitment_id = ?";
    String WORK_REQUEST_APPLY_CHECK_EXIST = "SELECT EXISTS(\n" +
            "    SELECT * FROM work_request_apply\n" +
            "    WHERE user_id = ?\n" +
            "      AND work_request_recruitment_id = ?\n" +
            "      AND stt_work_code = 1\n" +
            "      AND work_request_apply_deleted = 0)";

    String WORK_REQUEST_RECRUITMENT_SAVED_GET_ALL = "SELECT * FROM work_request_recruitment_saved WHERE work_request_recruitment_saved_deleted = 0";
    String WORK_REQUEST_RECRUITMENT_SAVED_GET_ONE = "SELECT * FROM work_request_recruitment_saved WHERE work_request_recruitment_saved_id = ? AND work_request_recruitment_saved_deleted = 0";
    String WORK_REQUEST_RECRUITMENT_SAVED_GET_ALL_BY_ACCOUNT_ID = "SELECT\n" +
            "       T1.work_request_recruitment_id,\n" +
            "       T1.user_id,\n" +
            "       T1.work_payment_method_id,\n" +
            "       T1.work_request_type_id,\n" +
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
            "       T1.work_request_recruitment_note,\n" +
            "       T1.work_request_recruitment_deleted\n" +
            "FROM work_request_recruitment T1\n" +
            "LEFT OUTER JOIN work_request_recruitment_saved T2\n" +
            "ON T1.work_request_recruitment_id = T2.work_request_recruitment_id\n" +
            "WHERE T2.user_id = ?\n" +
            "AND T1.work_request_recruitment_deleted = 0\n" +
            "AND T2.work_request_recruitment_saved_deleted = 0";
    String INSERT_WORK_REQUEST_SAVED = "INSERT INTO work_request_recruitment_saved (user_id," +
            " work_request_recruitment_id, work_request_recruitment_saved_deleted) VALUES(?, ?, ?)";
    String WORK_REQUEST_SAVED_CHECK_EXIST = "SELECT EXISTS(\n" +
            "    SELECT * FROM work_request_recruitment_saved\n" +
            "    WHERE user_id = ?\n" +
            "      AND work_request_recruitment_id = ?\n" +
            "      AND work_request_recruitment_saved_deleted = 0)";

    String WORK_REQUEST_BOOK_GET_ALL = "SELECT * FROM work_request_book WHERE work_request_book_deleted = 0";
    String WORK_REQUEST_BOOK_GET_ALL_BY_REQUEST_ID = "SELECT * FROM work_request_book WHERE work_request_find_job_id = ? " +
            "AND work_request_book_deleted = 0";
    String WORK_REQUEST_BOOK_GET_ONE = "SELECT * FROM work_request_book WHERE work_request_book_id = ? AND work_request_book_deleted = 0";
    String INSERT_WORK_REQUEST_BOOK = "INSERT INTO work_request_book (user_id, work_request_find_job_id," +
            " stt_work_code, work_request_book_deleted) VALUES(?, ?, ?, ?)";
    String WORK_REQUEST_BOOK_UPDATE_STT_ONE = "UPDATE work_request_book\n" +
            "SET stt_work_code = ?\n" +
            "WHERE user_id = ? AND work_request_find_job_id = ?";
    String WORK_REQUEST_BOOK_REJECT_STT_ALL = "UPDATE work_request_book\n" +
            "SET stt_work_code = ?\n" +
            "WHERE work_request_find_job_id = ?";
    String WORK_REQUEST_BOOK_CHECK_EXIST = "SELECT EXISTS(\n" +
            "    SELECT * FROM work_request_book\n" +
            "    WHERE user_id = ?\n" +
            "      AND work_request_find_job_id = ?\n" +
            "      AND stt_work_code = 1\n" +
            "      AND work_request_book_deleted = 0)";

    String WORK_PAYMENT_METHOD_GET_ALL = "SELECT * FROM work_payment_method WHERE work_payment_method_deleted = 0";
    String WORK_PAYMENT_METHOD_GET_ONE = "SELECT * FROM work_payment_method WHERE work_payment_method_id = ? AND work_payment_method_deleted = 0";

    String WORK_TIME_GET_ALL = "SELECT * FROM work_time WHERE work_time_deleted = 0";
    String WORK_TIME_GET_ONE = "SELECT * FROM work_time WHERE work_time_id = ? AND work_time_deleted = 0";

    String WORK_REPORT_GET_ALL = "SELECT * FROM work_report";
    String WORK_REPORT_GET_ONE = "SELECT * FROM work_report WHERE work_report_id = ?";

    String WORK_REQUEST_TYPE_GET_ALL = "SELECT * FROM work_request_type WHERE work_request_type_deleted = 0";
    String WORK_REQUEST_TYPE_GET_ONE = "SELECT * FROM work_request_type WHERE work_request_type_id = ? AND work_request_type_deleted = 0";

    String WORK_COMMENT_GET_ALL = "SELECT * FROM work_comment WHERE work_comment_deleted = 0";
    String WORK_COMMENT_GET_ONE = "SELECT * FROM work_comment WHERE work_comment_id = ? AND work_comment_deleted = 0";

    String STT_WORK_GET_ALL = "SELECT * FROM stt_work";
    String STT_WORK_GET_ALL_BY_NAME = "SELECT * FROM stt_work WHERE stt_work_name = ?";
    String STT_WORK_GET_ONE = "SELECT * FROM stt_work WHERE stt_work_id = ?";

    String WORK_REPORT_REASON_GET_ALL = "SELECT * FROM work_report_reason";
    String WORK_REPORT_REASON_GET_ONE = "SELECT * FROM work_report_reason WHERE work_report_reason_id = ?";

    String WORK_SALARY_UNIT_GET_ALL = "SELECT * FROM work_salary_unit";
    String WORK_SALARY_UNIT_GET_ONE = "SELECT * FROM work_salary_unit WHERE work_salary_unit_id = ?";

    String INSERT_REQUEST_FIND_JOB = "INSERT INTO work_request_find_job (user_id, stt_work_code, work_request_type_id, work_salary_unit_id, " +
            "work_payment_method_id, work_time_id, work_request_find_job_expected_salary, work_request_find_job_expected_location, work_request_find_job_title, " +
            "work_request_find_job_start_date_time, work_request_find_job_end_date_time, work_request_find_job_last_update_date_time, work_request_find_job_description, " +
            "work_request_find_job_personal_experience, work_request_find_job_deleted) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    String INSERT_REQUEST_RECRUITMENT = "INSERT INTO work_request_recruitment (user_id, stt_work_code, work_request_type_id," +
            " work_salary_unit_id, work_payment_method_id, work_request_recruitment_title," +
            " work_request_recruitment_start_date_time, work_request_recruitment_end_date_time," +
            " work_request_recruitment_last_update_date_time, work_request_recruitment_description, work_request_recruitment_requirements," +
            " work_request_recruitment_benefits, work_request_recruitment_salary, work_request_recruitment_quantity," +
            " work_request_recruitment_experience_required, work_request_recruitment_gender_required, work_request_recruitment_work_location," +
            " work_request_recruitment_deleted)" +
            "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    String STT_WORK_REQUEST_RECRUITMENT_FIND_JOB_COUNT_GET_ONE_BY_USER_ID = "SELECT * FROM stt_work_request_recruitment_find_job_count WHERE user_id = ?";

    String WORK_LIST_APPLIED_GET_ALL_BY_USER_ID = "SELECT\n" +
            "T1.work_request_recruitment_id,\n" +
            "T1.user_id,\n" +
            "T1.work_payment_method_id,\n" +
            "T1.work_request_type_id,\n" +
            "T1.stt_work_code,\n" +
            "T1.work_salary_unit_id,\n" +
            "T1.work_request_recruitment_title,\n" +
            "T1.work_request_recruitment_start_date_time,\n" +
            "T1.work_request_recruitment_end_date_time,\n" +
            "T1.work_request_recruitment_last_update_date_time,\n" +
            "T1.work_request_recruitment_description,\n" +
            "T1.work_request_recruitment_requirements,\n" +
            "T1.work_request_recruitment_benefits,\n" +
            "T1.work_request_recruitment_salary,\n" +
            "T1.work_request_recruitment_quantity,\n" +
            "T1.work_request_recruitment_experience_required,\n" +
            "T1.work_request_recruitment_gender_required,\n" +
            "T1.work_request_recruitment_work_location,\n" +
            "T1.work_request_recruitment_note,\n" +
            "T1.work_request_recruitment_deleted\n" +
            "FROM work_request_recruitment T1\n" +
            "INNER JOIN work_request_apply T2\n" +
            "ON T1.work_request_recruitment_id = T2.work_request_recruitment_id\n" +
            "WHERE T1.user_id = ?\n" +
            "AND T2.stt_work_code = ?\n" +
            "AND T1.work_request_recruitment_deleted = 0\n" +
            "AND T2.work_request_apply_deleted = 0\n" +
            "GROUP BY T1.work_request_recruitment_id";

    String WORK_LIST_BOOKED_GET_ALL_BY_USER_ID = "SELECT\n" +
            "T1.work_request_find_job_id,\n" +
            "T1.user_id,\n" +
            "T1.stt_work_code,\n" +
            "T1.work_request_type_id,\n" +
            "T1.work_salary_unit_id,\n" +
            "T1.work_payment_method_id,\n" +
            "T1.work_time_id,\n" +
            "T1.work_request_find_job_title,\n" +
            "T1.work_request_find_job_start_date_time,\n" +
            "T1.work_request_find_job_end_date_time,\n" +
            "T1.work_request_find_job_last_update_date_time,\n" +
            "T1.work_request_find_job_description,\n" +
            "T1.work_request_find_job_personal_experience,\n" +
            "T1.work_request_find_job_expected_location,\n" +
            "T1.work_request_find_job_expected_salary,\n" +
            "T1.work_request_find_job_note,\n" +
            "T1.work_request_find_job_deleted\n" +
            "FROM work_request_find_job T1\n" +
            "INNER JOIN work_request_book T2\n" +
            "ON T1.work_request_find_job_id = T2.work_request_find_job_id\n" +
            "WHERE T1.user_id = ?\n" +
            "AND T2.stt_work_code = ?\n" +
            "AND T1.work_request_find_job_deleted = 0\n" +
            "GROUP BY T1.work_request_find_job_id\n";

    String DELETE_WORK_REQUEST_APPLY = "UPDATE work_request_apply\n" +
            "SET work_request_apply_deleted = 1\n" +
            "WHERE work_request_recruitment_id = ?";

    String DELETE_WORK_REQUEST_SAVE = "UPDATE work_request_recruitment_saved\n" +
            "SET work_request_recruitment_saved_deleted = 1\n" +
            "WHERE work_request_recruitment_id = ?";

}