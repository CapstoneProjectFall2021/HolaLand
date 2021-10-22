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
}
