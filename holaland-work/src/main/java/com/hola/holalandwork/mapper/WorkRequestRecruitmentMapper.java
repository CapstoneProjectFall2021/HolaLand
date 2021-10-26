package com.hola.holalandwork.mapper;

import com.hola.holalandwork.entity.WorkRequestRecruitment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkRequestRecruitmentMapper implements RowMapper<WorkRequestRecruitment> {

    @Override
    public WorkRequestRecruitment mapRow(ResultSet resultSet, int i) throws SQLException {
        return WorkRequestRecruitment.builder()
                .workRequestRecruitmentId(resultSet.getInt("work_request_recruitment_id"))
                .userId(resultSet.getInt("user_id"))
                .workPaymentMethodId(resultSet.getInt("work_payment_method_id"))
                .workJobTypeId(resultSet.getInt("work_job_type_id"))
                .workSalaryUnitId(resultSet.getInt("work_salary_unit_id"))
                .workRequestRecruitmentTitle(resultSet.getString("work_request_recruitment_title"))
                .workRequestRecruitmentStartDateTime(resultSet.getLong("work_request_recruitment_start_date_time"))
                .workRequestRecruitmentEndDateTime(resultSet.getLong("work_request_recruitment_end_date_time"))
                .workRequestRecruitmentLastUpdateDateTime(resultSet.getLong("work_request_recruitment_last_update_date_time"))
                .workRequestRecruitmentDescription(resultSet.getString("work_request_recruitment_description"))
                .workRequestRecruitmentRequirement(resultSet.getString("work_request_recruitment_requirements"))
                .workRequestRecruitmentBenefit(resultSet.getString("work_request_recruitment_benefits"))
                .workRequestRecruitmentSalary(resultSet.getString("work_request_recruitment_salary"))
                .workRequestRecruitmentQuantity(resultSet.getInt("work_request_recruitment_quantity"))
                .workRequestRecruitmentExperienceRequirement(resultSet.getString("work_request_recruitment_experience_required"))
                .workRequestRecruitmentGenderRequirement(resultSet.getString("work_request_recruitment_gender_required"))
                .workRequestRecruitmentWorkLocation(resultSet.getString("work_request_recruitment_work_location"))
                .workRequestRecruitmentDeleted(resultSet.getBoolean("work_request_recruitment_deleted"))
                .build();
    }
}
