package com.hola.holalandtraffic.mapper;

import com.hola.holalandtraffic.entity.MotorbikeTaxiDrivers;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MotorbikeTaxiDriversMapper implements RowMapper<MotorbikeTaxiDrivers> {

    @Override
    public MotorbikeTaxiDrivers mapRow(ResultSet resultSet, int i) throws SQLException {

        MotorbikeTaxiDrivers m = MotorbikeTaxiDrivers.builder()
                .tfMotorbikeTaxiDriversId(resultSet.getInt("tf_motorbike_taxi_drivers_id"))
                .tfMotorbikeTaxiDriversName(resultSet.getString("tf_motorbike_taxi_drivers_name"))
                .tfMotorbikeTaxiDriversPhone(resultSet.getString("tf_motorbike_taxi_drivers_phone"))
                .tfMotorbikeTaxiDriversImage(resultSet.getString("tf_motorbike_taxi_drivers_image"))
                .tfMotorbikeTaxiDriversLicensePlates(resultSet.getString("tf_motorbike_taxi_drivers_license_plates"))
                .tfMotorbikeTaxiDriversVehicleType(resultSet.getString("tf_motorbike_taxi_drivers_vehicle_type"))
                .tfMotorbikeTaxiDriversStartTime(resultSet.getString("tf_motorbike_taxi_drivers_start_time"))
                .tfMotorbikeTaxiDriversEndTime(resultSet.getString("tf_motorbike_taxi_drivers_end_time"))
                .tfMotorbikeTaxiDriversRating(resultSet.getDouble("tf_motorbike_taxi_drivers_rating"))
                .tfMotorbikeTaxiDriversStatus(resultSet.getInt("tf_motorbike_taxi_drivers_status"))
                .tfMotorbikeTaxiDriversDeleted(resultSet.getBoolean("tf_motorbike_taxi_drivers_deleted"))
                .build();
        return m;
    }
}
