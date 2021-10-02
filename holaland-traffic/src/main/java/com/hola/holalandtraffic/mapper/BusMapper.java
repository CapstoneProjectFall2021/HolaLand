package com.hola.holalandtraffic.mapper;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hola.holalandtraffic.entity.Bus;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BusMapper implements RowMapper<Bus> {

    @Override
    public Bus mapRow(ResultSet resultSet, int i) throws SQLException {

        String temp = resultSet.getString("tf_bus_stops");

        Gson gson = new Gson();
        ArrayList<String> stops = gson.fromJson(temp, new TypeToken<ArrayList<String>>() {
        }.getType());

        return Bus.builder()
                .tfBusId(resultSet.getInt("tf_bus_id"))
                .tfBusName(resultSet.getString("tf_bus_name"))
                .tfBusStartTime(resultSet.getString("tf_bus_start_time"))
                .tfBusEndTime(resultSet.getString("tf_bus_end_time"))
                .tfBusInfo(resultSet.getString("tf_bus_info"))
                .tfBusStops(stops)
                .tfBusPrice(resultSet.getInt("tf_bus_price"))
                .tfBusStatus(resultSet.getInt("tf_bus_status"))
                .tfBusDeleted(resultSet.getBoolean("tf_bus_deleted"))
                .build();
    }
}
