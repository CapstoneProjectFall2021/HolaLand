package com.hola.holalandtraffic.repository;

public interface IRepositoryQuery {
//
    String MEMBER_GET_ALL = "SELECT * FROM member";
    String MEMBER_GET_ONE = "SELECT * FROM member WHERE member_id = ?";
    String MEMBER_ADD_ONE = "INSERT INTO member (member_name, member_gender, member_dob, member_mobile, member_email, member_rank_id, member_status_id)\n" +
                            "VALUES (?, ?, ?, ?, ?, ?, ?)";
    String MEMBER_UPDATE_ONE = "UPDATE member\n" +
                               "SET member_name = ?, member_gender = ?, member_dob = ?, member_mobile = ?, member_email = ?, member_rank_id = ?, member_status_id = ?\n" +
                               "WHERE member_id = ?";
    String MEMBER_DELETE_ONE = "DELETE FROM member WHERE member_id = ?";

    String BUS_GET_ALL = "SELECT * FROM tf_bus WHERE tf_bus_deleted = 0";
    String BUS_GET_ONE = "SELECT * FROM tf_bus WHERE tf_bus_id = ? AND tf_bus_deleted = 0";

    String MOTORBIKE_TAXI_DRIVERS_GET_ALL = "SELECT * FROM tf_motorbike_taxi_drivers WHERE tf_motorbike_taxi_drivers_deleted = 0";
    String MOTORBIKE_TAXI_DRIVERS_GET_ONE = "SELECT * FROM tf_motorbike_taxi_drivers WHERE tf_motorbike_taxi_drivers_id = ? AND tf_motorbike_taxi_drivers_deleted = 0";




    
}
