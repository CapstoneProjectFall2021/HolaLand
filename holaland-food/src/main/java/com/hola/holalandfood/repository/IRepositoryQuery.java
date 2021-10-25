package com.hola.holalandfood.repository;

public interface IRepositoryQuery {

    String FOOD_STORE_ONLINE_TYPE_GET_ALL = "SELECT * FROM food_store_online_type";
    String FOOD_STORE_ONLINE_TYPE_GET_ONE = "SELECT * FROM food_store_online_type WHERE food_store_online_type_id = ?";

    String FOOD_STORE_TYPE_GET_ALL = "SELECT * FROM food_store_type";
    String FOOD_STORE_TYPE_GET_ONE = "SELECT * FROM food_store_type WHERE food_store_type_id = ?";

    String FOOD_TAG_GET_ALL = "SELECT * FROM food_tag";
    String FOOD_TAG_GET_ONE = "SELECT * FROM food_tag WHERE food_tag_id = ?";

    String FOOD_TYPE_GET_ALL = "SELECT * FROM food_type WHERE food_type_deleted = 0";
    String FOOD_TYPE_GET_ONE = "SELECT * FROM food_type WHERE food_type_id = ? AND food_type_deleted = 0";

    String STT_FOOD_GET_ALL = "SELECT * FROM stt_food";
    String STT_FOOD_GET_ONE = "SELECT * FROM stt_food WHERE stt_food_id = ?";
}
