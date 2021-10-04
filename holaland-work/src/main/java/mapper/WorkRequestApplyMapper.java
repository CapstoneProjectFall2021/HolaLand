package mapper;

import com.hola.holalandwork.entity.WorkReport;
import com.hola.holalandwork.entity.WorkRequestApply;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkRequestApplyMapper implements RowMapper<WorkRequestApply> {
    @Override
    public WorkRequestApply mapRow(ResultSet resultSet, int i) throws SQLException {
        return WorkRequestApply.builder()
                .workRequestApplyId(resultSet.getInt("work_request_apply_id"))
                .accountId(resultSet.getInt("account_id"))
                .workRequestRecruitmentId(resultSet.getInt("work_request_recruitment_id"))
                .workRequestApplySttId(resultSet.getInt("work_request_apply_stt_id"))
                .workRequestApplyDeleted(resultSet.getBoolean("work_request_apply_deleted"))
                .build();
    }
}
