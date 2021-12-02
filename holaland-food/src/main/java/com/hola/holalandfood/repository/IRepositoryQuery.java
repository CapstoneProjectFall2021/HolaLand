package com.hola.holalandfood.repository;

public interface IRepositoryQuery {

    String FOOD_ITEM_GET_ALL = "SELECT * FROM food_item";
    String FOOD_ITEM_GET_ONE = "SELECT * FROM food_item WHERE food_item_id = ?";
    String FOOD_ITEM_GET_ALL_BY_STORE_ONLINE_ID = "SELECT\n" +
            "       T1.food_item_id,\n" +
            "       T1.food_store_online_id,\n" +
            "       T1.food_tag_id,\n" +
            "       T1.food_type_id,\n" +
            "       T1.food_item_image,\n" +
            "       T1.food_item_name,\n" +
            "       T1.food_item_price,\n" +
            "       T1.food_item_sold_number,\n" +
            "       T1.food_item_is_active,\n" +
            "       T1.food_item_deleted\n" +
            "FROM food_item T1\n" +
            "LEFT JOIN food_store_online T2\n" +
            "ON T1.food_store_online_id = T2.food_store_online_id\n" +
            "WHERE T2.food_store_online_id = ?\n" +
            "AND T1.food_item_is_active = 1\n" +
            "AND T1.food_item_deleted = 0";
    String FOOD_ITEM_GET_ALL_BY_STORE_ONLINE_ID_AND_TAG_ID = "SELECT\n" +
            "       T1.food_item_id,\n" +
            "       T1.food_store_online_id,\n" +
            "       T1.food_tag_id,\n" +
            "       T1.food_type_id,\n" +
            "       T1.food_item_image,\n" +
            "       T1.food_item_name,\n" +
            "       T1.food_item_price,\n" +
            "       T1.food_item_sold_number,\n" +
            "       T1.food_item_is_active,\n" +
            "       T1.food_item_deleted\n" +
            "FROM food_item T1\n" +
            "LEFT JOIN food_store_online T2\n" +
            "ON T1.food_store_online_id = T2.food_store_online_id\n" +
            "WHERE T2.food_store_online_id = ?\n" +
            "AND T1.food_tag_id = ?\n" +
            "AND T1.food_item_is_active = 1\n" +
            "AND T1.food_item_deleted = 0";

    String FOOD_ITEM_GET_ALL_BY_USER_ID = "SELECT\n" +
            "       T1.food_item_id,\n" +
            "       T1.food_store_online_id,\n" +
            "       T1.food_tag_id,\n" +
            "       T1.food_type_id,\n" +
            "       T1.food_item_image,\n" +
            "       T1.food_item_name,\n" +
            "       T1.food_item_price,\n" +
            "       T1.food_item_sold_number,\n" +
            "       T1.food_item_is_active,\n" +
            "       T1.food_item_deleted\n" +
            "FROM food_item T1\n" +
            "LEFT OUTER JOIN food_store_online T2\n" +
            "ON T1.food_store_online_id = T2.food_store_online_id\n" +
            "WHERE T2.user_id = ?\n" +
            "AND T2.food_store_online_deleted = 0\n" +
            "AND T1.food_item_deleted = 0";

    String FOOD_ITEM_DELETED_ONE = "UPDATE food_item\n" +
            "SET food_item_deleted = ?\n" +
            "WHERE food_item_id = ?";

    String FOOD_ORDER_DETAIL_GET_ALL = "SELECT * FROM food_order_detail";
    String FOOD_ORDER_DETAIL_GET_ONE = "SELECT * FROM food_order_detail WHERE food_order_detail_id = ?";
    String FOOD_ORDER_DETAIL_GET_ALL_BY_ORDER_ID = "SELECT * FROM food_order_detail WHERE food_order_id = ?";

    String FOOD_ORDER_GET_ALL = "SELECT * FROM food_order";
    String FOOD_ORDER_GET_ONE = "SELECT * FROM food_order WHERE food_order_id = ?";

    String FOOD_ORDER_UPDATE_STT_FOOD = "UPDATE food_order\n" +
            "SET stt_food_code = ?\n" +
            "WHERE food_order_id = ?";

    String FOOD_ORDER_REJECT_ONE = "UPDATE food_order\n" +
            "SET food_order_reason_reject = ?, stt_food_code = ?\n" +
            "WHERE food_order_id = ?";

    String FOOD_STORE_ONLINE_GET_ALL = "SELECT * FROM food_store_online WHERE food_store_online_deleted = 0";
    String FOOD_STORE_ONLINE_GET_ONE = "SELECT * FROM food_store_online WHERE food_store_online_id = ?";

    String FOOD_STORE_ONLINE_UPDATE_INFO_ONE = "UPDATE food_store_online\n" +
            "SET food_store_online_name = ?, food_store_online_description = ?\n" +
            "WHERE food_store_online_id = ?";

    String FOOD_STORE_ONLINE_GET_ONE_BY_USER_ID = "SELECT * FROM food_store_online WHERE user_id = ? AND food_store_online_deleted = 0";
    String FOOD_STORE_ONLINE_GET_ONE_BY_ORDER_ID = "SELECT\n" +
            "       T1.food_store_online_id,\n" +
            "       T1.user_id,\n" +
            "       T1.stt_food_code,\n" +
            "       T1.food_store_online_image,\n" +
            "       T1.food_store_online_name,\n" +
            "       T1.food_store_online_rate,\n" +
            "       T1.food_store_online_min_price,\n" +
            "       T1.food_store_online_max_price,\n" +
            "       T1.food_store_online_description,\n" +
            "       T1.food_store_online_count_food_item,\n" +
            "       T1.food_store_online_count_rate,\n" +
            "       T1.food_store_online_count_report,\n" +
            "       T1.food_store_online_pause_selling_flag,\n" +
            "       T1.food_store_online_stop_selling_flag,\n" +
            "       T1.food_store_online_deleted\n" +
            "FROM food_store_online T1\n" +
            "LEFT JOIN food_order T2\n" +
            "ON T1.food_store_online_id = T2.food_store_online_id\n" +
            "WHERE T2.food_order_id = ?";

    String FOOD_STORE_ONLINE_GET_ALL_BY_TYPE = "SELECT\n" +
            "       T1.food_store_online_id,\n" +
            "       T1.user_id,\n" +
            "       T1.stt_food_code,\n" +
            "       T1.food_store_online_image,\n" +
            "       T1.food_store_online_name,\n" +
            "       T1.food_store_online_rate,\n" +
            "       T1.food_store_online_min_price,\n" +
            "       T1.food_store_online_max_price,\n" +
            "       T1.food_store_online_description,\n" +
            "       T1.food_store_online_count_food_item,\n" +
            "       T1.food_store_online_count_rate,\n" +
            "       T1.food_store_online_count_report,\n" +
            "       T1.food_store_online_pause_selling_flag,\n" +
            "       T1.food_store_online_stop_selling_flag,\n" +
            "       T1.food_store_online_deleted\n" +
            "FROM food_store_online T1\n" +
            "LEFT JOIN food_store_online_type T2\n" +
            "ON T1.food_store_online_id = T2.food_store_online_id\n" +
            "WHERE T2.food_type_id = ?\n" +
            "AND T1.stt_food_code = ?\n" +
            "AND T1.food_store_online_deleted = 0";

    String FOOD_STORE_ONLINE_RATE_GET_ALL = "SELECT * FROM food_store_online_rate";
    String FOOD_STORE_ONLINE_RATE_GET_ONE = "SELECT * FROM food_store_online_rate WHERE food_store_online_rate_id = ?";
    String FOOD_STORE_ONLINE_RATE_GET_ALL_BY_STORE_ONLINE_ID = "SELECT * FROM food_store_online_rate WHERE food_store_online_id = ?";

    String FOOD_REPORT_GET_ALL = "SELECT * FROM food_report";
    String FOOD_REPORT_GET_ONE = "SELECT * FROM food_report WHERE food_report_id = ?";
    String FOOD_REPORT_GET_ALL_BY_ORDER_ID = "SELECT T1.food_report_id,\n" +
            "       T1.user_id,\n" +
            "       T1.food_store_online_id,\n" +
            "       T1.food_order_id,\n" +
            "       T1.food_report_content,\n" +
            "       T1.food_report_create_date,\n" +
            "       T1.food_report_update_date,\n" +
            "       T1.food_report_deleted\n" +
            "FROM food_report T1\n" +
            "INNER JOIN food_order T2\n" +
            "ON T1.food_order_id = T2.food_order_id\n" +
            "WHERE T2.food_store_online_id = ?";
    String FOOD_REPORT_INSERT_ONE = "INSERT INTO food_report (user_id, food_store_online_id, food_order_id, " +
            "food_report_content, food_report_create_date, food_report_deleted) VALUES (?, ?, ?, ?, ?, ?)";
    String FOOD_REPORT_DELETE_ONE = "UPDATE food_report\n" +
            "SET food_report_deleted = 1\n" +
            "WHERE food_report_id = ?";
    String FOOD_REPORT_CHECK_EXISTS = "SELECT * FROM food_report WHERE user_id = ? AND food_order_id = ? " +
            "AND food_report_deleted = 0";

    String FOOD_STORE_ONLINE_TAG_GET_ALL = "SELECT * FROM food_store_online_tag";
    String FOOD_STORE_ONLINE_TAG_GET_ONE = "SELECT * FROM food_store_online_tag WHERE food_store_online_tag_id = ?";
    String FOOD_TAG_GET_ALL_BY_USER_ID = "SELECT\n" +
            "       T1.food_tag_id,\n" +
            "       T1.food_tag_name\n" +
            "FROM food_tag T1\n" +
            "LEFT OUTER JOIN food_store_online_tag T2\n" +
            "ON T1.food_tag_id = T2.food_tag_id\n" +
            "LEFT OUTER JOIN food_store_online T3\n" +
            "ON T2.food_store_online_id = T3.food_store_online_id\n" +
            "WHERE T3.user_id = ?\n" +
            "AND T3.food_store_online_deleted = 0";

    String FOOD_STORE_ONLINE_TYPE_GET_ALL = "SELECT * FROM food_store_online_type";
    String FOOD_STORE_ONLINE_TYPE_GET_ONE = "SELECT * FROM food_store_online_type WHERE food_store_online_type_id = ?";

    String FOOD_TAG_GET_ALL = "SELECT * FROM food_tag";
    String FOOD_TAG_GET_ONE = "SELECT * FROM food_tag WHERE food_tag_id = ?";
    String FOOD_TAG_GET_ALL_BY_STORE_ONLINE_ID = "SELECT\n" +
            "       T1.food_tag_id,\n" +
            "       T1.food_tag_name\n" +
            "FROM food_tag T1\n" +
            "LEFT JOIN food_store_online_tag T2\n" +
            "ON T1.food_tag_id = T2.food_tag_id\n" +
            "WHERE T2.food_store_online_id = ?";

    String FOOD_TYPE_GET_ALL = "SELECT * FROM food_type WHERE food_type_deleted = 0";
    String FOOD_TYPE_GET_ONE = "SELECT * FROM food_type WHERE food_type_id = ? AND food_type_deleted = 0";

    String STT_FOOD_GET_ALL = "SELECT * FROM stt_food";
    String STT_FOOD_GET_ONE = "SELECT * FROM stt_food WHERE stt_food_id = ?";
    String STT_Food_GET_ALL_BY_HISTORY_ORDER = "SELECT * FROM stt_food WHERE stt_food_code BETWEEN 3 AND 5";

    //Delete all food tag by food store online id
    String DELETE_ALL_TAG_BY_FOOD_STORE_ONLINE_ID = "DELETE FROM food_store_online_tag WHERE food_store_online_id = ?";

    //Insert all tag for food_store_online_tag table
    String INSERT_ALL_TAG_BY_FOOD_STORE_ONLINE_ID = "INSERT INTO food_store_online_tag (food_store_online_id, food_tag_id) values(?,?)";

}
