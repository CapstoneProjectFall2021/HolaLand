package com.hola.holalandwork.mapper;

import com.hola.holalandwork.entity.WorkSalaryUnit;
import com.hola.holalandwork.entity.WorkTime;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkSalaryUnitMapper implements RowMapper<WorkSalaryUnit> {

    @Override
    public WorkSalaryUnit mapRow(ResultSet resultSet, int i) throws SQLException {
        return WorkSalaryUnit.builder()
                .workSalaryUnitId(resultSet.getInt("work_salary_unit_id"))
                .workSalaryUnitName(resultSet.getString("work_salary_unit_name"))
                .build();
    }
}
