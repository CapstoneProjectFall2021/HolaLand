package mapper;

import com.hola.holalandwork.entity.WorkComment;
import com.hola.holalandwork.entity.WorkJobType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkJobTypeMapper implements RowMapper<WorkJobType> {
    @Override
    public WorkJobType mapRow(ResultSet resultSet, int i) throws SQLException {
        return WorkJobType.builder()
                .workJobTypeId(resultSet.getInt("work_job_type_id"))
                .workJobTypeName(resultSet.getString("work_job_type_name"))
                .workJobTypeIcon(resultSet.getString("work_job_type_icon"))
                .workJobTypeCount(resultSet.getInt("work_job_type_count"))
                .workJobTypeDeleted(resultSet.getBoolean("work_job_type_deleted"))
                .build();
    }
}
