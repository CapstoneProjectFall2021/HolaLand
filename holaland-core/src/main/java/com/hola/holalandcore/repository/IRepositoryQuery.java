package com.hola.holalandcore.repository;

public interface IRepositoryQuery {

    String GET_USER_BY_USER_EMAIL = "SELECT * FROM user WHERE user_email = ? AND user_status_id = 1 AND user_deleted = 0";

    String GET_ROLES_BY_USER_EMAIL = "SELECT T3.role_id, T3.role_name\n" +
            "FROM user T1\n" +
            "LEFT OUTER JOIN user_role T2\n" +
            "ON T1.user_id = T2.user_id\n" +
            "LEFT OUTER JOIN role T3\n" +
            "ON T3.role_id = T2.role_id\n" +
            "WHERE T1.user_email = ?\n" +
            "AND T1.user_status_id = 1\n" +
            "AND T1.user_deleted = 0";

    String USER_DETAIL_GET_ALL = "SELECT * FROM user_detail";
    String USER_DETAIL_GET_ONE_BY_USER_ID = "SELECT * FROM user_detail WHERE user_id = ?";

    String GET_LIST_USER_BOOKED_BY_USER_ID = "SELECT\n" +
            "T2.user_detail_id,\n" +
            "T2.user_id,\n" +
            "T2.user_name,\n" +
            "T2.user_dob,\n" +
            "T2.user_phone,\n" +
            "T2.user_gender,\n" +
            "T2.user_email\n" +
            "FROM work_request_book T1\n" +
            "INNER JOIN user_detail T2\n" +
            "ON T1.user_id = T2.user_id\n" +
            "WHERE T1.work_request_find_job_id = ?";

    String GET_LIST_USER_APPLIED_BY_USER_ID = "SELECT\n" +
            "T2.user_detail_id,\n" +
            "T2.user_id,\n" +
            "T2.user_name,\n" +
            "T2.user_dob,\n" +
            "T2.user_phone,\n" +
            "T2.user_gender,\n" +
            "T2.user_email\n" +
            "FROM work_request_apply T1\n" +
            "INNER JOIN user_detail T2\n" +
            "ON T1.user_id = T2.user_id\n" +
            "WHERE T1.work_request_recruitment_id = ?";

    String GET_USER_DETAIL_BY_USER_ID = "SELECT * FROM user_detail WHERE user_id = ?";

    String GET_USER_ADDRESS_BY_USER_DETAIL_ID = "SELECT * FROM user_address WHERE user_detail_id = ? " +
            "AND user_address_deleted = 0";
    String GET_USER_ADDRESS_BY_USER_ID = "SELECT\n" +
            "       T1.user_address_id,\n" +
            "       T1.user_detail_id,\n" +
            "       T1.user_name,\n" +
            "       T1.user_phone,\n" +
            "       T1.user_address,\n" +
            "       T1.user_address_default,\n" +
            "       T1.user_address_deleted\n" +
            "FROM user_address T1\n" +
            "INNER JOIN user_detail T2\n" +
            "ON T1.user_detail_id = T2.user_detail_id\n" +
            "WHERE T2.user_id = ?\n" +
            "AND T1.user_address_deleted = 0";

    String INSERT_USER_ADDRESS_ONE = "INSERT INTO user_address(user_detail_id, user_name, user_phone, user_address, " +
            "user_address_default, user_address_deleted) VALUES (?, ?, ?, ?, ?, ?)";

    String UPDATE_USER_ADDRESS_ONE = "UPDATE user_address\n" +
            "SET user_name = ?, user_phone = ?, user_address = ?\n" +
            "WHERE user_address_id = ?";

    String GET_CURRENT_USER_ADDRESS_DEFAULT_BY_USER_ID = "SELECT\n" +
            "      T1.user_address_id,\n" +
            "      T1.user_detail_id,\n" +
            "      T1.user_name,\n" +
            "      T1.user_phone,\n" +
            "      T1.user_address,\n" +
            "      T1.user_address_default,\n" +
            "      T1.user_address_deleted\n" +
            "FROM user_address T1\n" +
            "INNER JOIN user_detail T2\n" +
            "ON T1.user_detail_id = T2.user_detail_id\n" +
            "WHERE T2.user_id = ?\n" +
            "AND T1.user_address_default = 1\n" +
            "AND T1.user_address_deleted = 0";

    String UPDATE_USER_ADDRESS_DEFAULT = "UPDATE user_address\n" +
            "SET user_address_default = ?\n" +
            "WHERE user_address_id = ?";

    String DELETE_USER_ADDRESS_ONE = "UPDATE user_address\n" +
            "SET user_address_deleted = 1\n" +
            "WHERE user_address_id = ?";

    String USER_DETAIL_UPDATE_ONE = "UPDATE user_detail\n" +
            "SET user_name = ?, user_dob = ?,  user_gender = ?, user_phone = ?, user_email = ?\n" +
            "WHERE user_id = ?";

    String UPDATE_USER_PASSWORD = "UPDATE user\n" +
            "SET user_password = ?\n" +
            "WHERE user_id = ?";

}
