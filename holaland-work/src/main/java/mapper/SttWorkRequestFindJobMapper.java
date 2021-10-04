package mapper;

import com.hola.holalandwork.entity.SttWorkReport;
import com.hola.holalandwork.entity.SttWorkRequestFindJob;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SttWorkRequestFindJobMapper implements RowMapper<SttWorkRequestFindJob> {

    @Override
    public SttWorkRequestFindJob mapRow(ResultSet resultSet, int i) throws SQLException {
        return SttWorkRequestFindJob.builder()
                .sttWorkRequestFindJobId(resultSet.getInt("stt_work_request_find_job_id"))
                .sttWorkRequestFindJobName(resultSet.getString("stt_work_request_find_job_status"))
                .build();
    }
}
