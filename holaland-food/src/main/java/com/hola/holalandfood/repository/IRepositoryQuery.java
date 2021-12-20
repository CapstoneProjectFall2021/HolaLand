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
    String FOOD_ITEM_DELETED_ONE = "UPDATE food_item SET food_item_deleted = ? WHERE food_item_id = ?";
    String FOOD_ITEM_SEARCH = "SELECT * FROM food_item WHERE food_item_name LIKE ? AND food_item_deleted = 0";
    String FOOD_ITEM_UPDATE = "UPDATE food_item" +
            " SET food_item_image = COALESCE(?, food_item_image)," +
            " food_item_name = ?," +
            " food_item_price = ?," +
            " food_tag_id = ?" +
            " WHERE food_item_id = ?";
    String FOOD_ITEM_COUNT_ITEM_SOLD = "SELECT SUM(food_item_sold_number)" +
            " FROM food_item" +
            " WHERE food_store_online_id = ?" +
            " AND food_item_deleted = 0";

    String FOOD_ORDER_DETAIL_GET_ALL = "SELECT * FROM food_order_detail";
    String FOOD_ORDER_DETAIL_GET_ONE = "SELECT * FROM food_order_detail WHERE food_order_detail_id = ?";
    String FOOD_ORDER_DETAIL_GET_ALL_BY_ORDER_ID = "SELECT * FROM food_order_detail WHERE food_order_id = ?";
    String FOOD_ORDER_DETAIL_SAVE = "INSERT INTO food_order_detail(food_order_id, food_item_id, food_item_name, " +
            "food_item_price, food_item_quantity) VALUES (?, ?, ?, ?, ?)";

    String FOOD_ORDER_GET_ALL = "SELECT * FROM food_order";
    String FOOD_ORDER_GET_ONE = "SELECT * FROM food_order WHERE food_order_id = ?";
    String FOOD_ORDER_GET_ALL_BY_STORE_ONLINE_ID = "SELECT * FROM food_order WHERE food_store_online_id = ? AND food_order_deleted = 0";
    String FOOD_ORDER_CHECK_USER_ORDER = "SELECT EXISTS(SELECT * FROM food_order " +
            " WHERE user_id = ?" +
            " AND food_store_online_id = ?" +
            " AND stt_food_code = 4" +
            " AND food_order_deleted = 0)";
    String FOOD_ORDER_UPDATE_STT_FOOD = "UPDATE food_order SET stt_food_code = ? WHERE food_order_id = ?";
    String FOOD_ORDER_REJECT_ONE = "UPDATE food_order" +
            " SET food_order_reason_reject = ?," +
            " stt_food_code = ?" +
            " WHERE food_order_id = ?";
    String FOOD_ORDER_SAVE = "INSERT INTO food_order(user_id, food_store_online_id, stt_food_code, food_order_total_price, " +
            "food_order_created_date, food_order_note, food_order_reason_reject, food_order_deleted) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    String FOOD_STORE_ONLINE_GET_ALL = "SELECT * FROM food_store_online WHERE food_store_online_deleted = 0";
    String FOOD_STORE_ONLINE_GET_ONE = "SELECT * FROM food_store_online WHERE food_store_online_id = ?";
    String FOOD_STORE_ONLINE_UPDATE_INFO_ONE = "UPDATE food_store_online" +
            " SET food_store_online_image = COALESCE(?,food_store_online_image)," +
            " food_store_online_name = ?," +
            " food_store_online_description = ?" +
            " WHERE food_store_online_id = ?";
    String FOOD_STORE_ONLINE_GET_ONE_BY_USER_ID = "SELECT * FROM food_store_online WHERE user_id = ? AND food_store_online_deleted = 0";
    String FOOD_STORE_ONLINE_CHECK_USER_IS_OWNER = "SELECT EXISTS(SELECT * FROM food_store_online" +
            " WHERE user_id = ?" +
            " AND food_store_online_id = ?" +
            " AND food_store_online_deleted = 0)";
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
    String FOOD_STORE_ONLINE_STOP_SELLING = "UPDATE food_store_online\n" +
            "SET food_store_online_stop_selling_flag = ?\n" +
            "WHERE food_store_online_id = ?";

    String FOOD_STORE_ONLINE_PAUSE_SELLING = "UPDATE food_store_online\n" +
            "SET food_store_online_pause_selling_flag = ?\n" +
            "WHERE food_store_online_id = ?";

    String FOOD_STORE_ONLINE_RATE_GET_ALL = "SELECT * FROM food_store_online_rate";
    String FOOD_STORE_ONLINE_RATE_GET_ONE = "SELECT * FROM food_store_online_rate WHERE food_store_online_rate_id = ?";
    String FOOD_STORE_ONLINE_RATE_GET_ALL_BY_STORE_ONLINE_ID = "SELECT * FROM food_store_online_rate WHERE food_store_online_id = ? " +
            "ORDER BY food_store_online_rate_create_time DESC";
    String FOOD_STORE_ONLINE_RATE_CHECK_COMMENT_EXIST = "SELECT EXISTS(SELECT * FROM food_store_online_rate WHERE user_id = ? " +
            "AND food_store_online_id = ? AND food_store_online_rate_deleted = 0)";
    String FOOD_STORE_ONLINE_RATE_GET_COMMENT = "SELECT * FROM food_store_online_rate WHERE user_id = ? AND  food_store_online_id = ?\n" +
            "AND  food_store_online_rate_deleted = 0";
    String FOOD_STORE_ONLINE_RATE_INSERT = "INSERT INTO food_store_online_rate (" +
            " user_id," +
            " food_store_online_id," +
            " food_store_online_rate_point," +
            " food_store_online_rate_comment," +
            " food_store_online_rate_create_time," +
            " food_store_online_rate_update_time," +
            " food_store_online_rate_deleted)" +
            " VALUES (?, ?, ?, ?, ?, ?, ?);";
    String FOOD_STORE_ONLINE_RATE_UPDATE = "UPDATE food_store_online_rate" +
            " SET food_store_online_rate_point = ?," +
            " food_store_online_rate_comment = ?," +
            " food_store_online_rate_update_time = ?" +
            " WHERE food_store_online_rate_id = ?";

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
            "WHERE T2.food_store_online_id = ? AND T1.food_report_deleted = 0\n" +
            "ORDER BY T1.food_report_create_date DESC";
    String FOOD_REPORT_INSERT_ONE = "INSERT INTO food_report (user_id, food_store_online_id, food_order_id, " +
            "food_report_content, food_report_create_date, food_report_deleted) VALUES (?, ?, ?, ?, ?, ?)";
    String FOOD_REPORT_DELETE_ONE = "UPDATE food_report\n" +
            "SET food_report_deleted = 1\n" +
            "WHERE food_report_id = ?";

    String FOOD_USER_REPORT_CHECK_EXISTS = "SELECT * FROM food_report WHERE user_id = ? AND food_order_id = ? " +
            "AND food_report_deleted = 0";
    String FOOD_ORDER_REPORT_CHECK_EXISTS = "SELECT * FROM food_report WHERE food_order_id = ? AND food_report_deleted = 0";

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
    String FOOD_TAG_SEARCH = "SELECT * FROM food_tag WHERE food_tag_name LIKE ?";

    String FOOD_TYPE_GET_ALL = "SELECT * FROM food_type WHERE food_type_deleted = 0";
    String FOOD_TYPE_GET_ONE = "SELECT * FROM food_type WHERE food_type_id = ? AND food_type_deleted = 0";

    String STT_FOOD_GET_ALL = "SELECT * FROM stt_food";
    String STT_FOOD_GET_ONE = "SELECT * FROM stt_food WHERE stt_food_id = ?";
    String STT_FOOD_GET_ALL_HISTORY_ORDER = "SELECT * FROM stt_food WHERE stt_food_code BETWEEN 3 AND 5 AND stt_food_name = 'FOOD_ORDER'";

    //Delete all food tag by food store online id
    String DELETE_ALL_TAG_BY_FOOD_STORE_ONLINE_ID = "DELETE FROM food_store_online_tag WHERE food_store_online_id = ?";

    //Insert all tag for food_store_online_tag table
    String INSERT_ALL_TAG_BY_FOOD_STORE_ONLINE_ID = "INSERT INTO food_store_online_tag (food_store_online_id, food_tag_id) values(?, ?)";

    //Insert food item
    String INSERT_FOOD_ITEM = "INSERT INTO food_item (food_store_online_id, food_tag_id, food_type_id,\n" +
            "food_item_image, food_item_name, food_item_price, food_item_sold_number,\n" +
            "food_item_is_active, food_item_deleted) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    // Count stt food in history order
    String COUNT_STT_ORDER_SELLER = "SELECT IFNULL(T1.reject_order,0) reject_order, IFNULL(T2.completed,0) completed, IFNULL(T3.cancel,0) cancel\n" +
            "FROM\n" +
            "(SELECT food_store_online_id, COUNT(stt_food_code) reject_order\n" +
            "    FROM food_order\n" +
            "    WHERE stt_food_code = 3 AND food_store_online_id = ?) T1\n" +
            "LEFT OUTER JOIN (SELECT food_store_online_id, COUNT(stt_food_code) completed\n" +
            "    FROM food_order\n" +
            "    WHERE stt_food_code = 4 AND food_store_online_id = ?) T2\n" +
            "ON T1.food_store_online_id = T2.food_store_online_id\n" +
            "LEFT OUTER JOIN (SELECT food_store_online_id, COUNT(stt_food_code) cancel\n" +
            "    FROM food_order\n" +
            "    WHERE stt_food_code = 5 AND food_store_online_id = ?) T3\n" +
            "ON T1.food_store_online_id = T3.food_store_online_id";

    String COUNT_STT_ORDER_STUDENT = "SELECT IFNULL(T1.reject_order,0) reject_order, IFNULL(T2.completed,0) completed, IFNULL(T3.cancel,0) cancel\n" +
            "FROM\n" +
            "(SELECT user_id, COUNT(stt_food_code) reject_order\n" +
            "    FROM food_order\n" +
            "    WHERE stt_food_code = 3 AND user_id = ?) T1\n" +
            "LEFT OUTER JOIN (SELECT user_id, COUNT(stt_food_code) completed\n" +
            "    FROM food_order\n" +
            "    WHERE stt_food_code = 4 AND user_id = ?) T2\n" +
            "ON T1.user_id = T2.user_id\n" +
            "LEFT OUTER JOIN (SELECT user_id, COUNT(stt_food_code) cancel\n" +
            "    FROM food_order\n" +
            "    WHERE stt_food_code = 5 AND user_id = ?) T3\n" +
            "ON T1.user_id = T3.user_id";
}
