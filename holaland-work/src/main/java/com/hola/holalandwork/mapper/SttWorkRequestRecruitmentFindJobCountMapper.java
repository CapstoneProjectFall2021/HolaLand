package com.hola.holalandwork.mapper;

import com.hola.holalandwork.entity.SttWorkRequestRecruitmentFindJobCount;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SttWorkRequestRecruitmentFindJobCountMapper implements RowMapper<SttWorkRequestRecruitmentFindJobCount> {
    @Override
    public SttWorkRequestRecruitmentFindJobCount mapRow(ResultSet resultSet, int i) throws SQLException {
        return SttWorkRequestRecruitmentFindJobCount.builder()
                .sttWorkRequestRecruitmentFindJobCountId(resultSet.getInt("stt_work_request_recruitment_find_job_count_id"))
                .userId(resultSet.getInt("user_id"))
                .sttWorkRequestRecruitmentFindJobCountPending(resultSet.getInt("stt_work_request_recruitment_find_job_count_pending"))
                .sttWorkRequestRecruitmentFindJobCountReject(resultSet.getInt("stt_work_request_recruitment_find_job_count_reject"))
                .sttWorkRequestRecruitmentFindJobCountApproved(resultSet.getInt("stt_work_request_recruitment_find_job_count_approved"))
                .sttWorkRequestRecruitmentFindJobCountComplete(resultSet.getInt("stt_work_request_recruitment_find_job_count_complete"))
                .sttWorkRequestRecruitmentFindJobCountExpired(resultSet.getInt("stt_work_request_recruitment_find_job_count_expired"))
                .sttWorkRequestRecruitmentFindJobCountSaveDraft(resultSet.getInt("stt_work_request_recruitment_find_job_count_save_draft"))
                .build();
    }
}
