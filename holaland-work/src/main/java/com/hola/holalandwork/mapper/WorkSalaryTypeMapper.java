package com.hola.holalandwork.mapper;

import com.hola.holalandwork.entity.WorkRequestRecruitment;
import com.hola.holalandwork.entity.WorkSalaryType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkSalaryTypeMapper implements RowMapper<WorkSalaryType> {
    @Override
    public WorkSalaryType mapRow(ResultSet resultSet, int i) throws SQLException {
        return WorkSalaryType.builder()
                .workSalaryTypeId(resultSet.getInt("work_salary_type_id"))
                .workSalaryTypeName(resultSet.getString("work_salary_type_name"))
                .workSalaryTypeDeleted(resultSet.getBoolean("work_salary_type_deleted"))
                .build();
    }
}
