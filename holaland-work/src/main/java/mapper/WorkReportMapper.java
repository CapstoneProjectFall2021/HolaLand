package mapper;

import com.hola.holalandwork.entity.WorkJobType;
import com.hola.holalandwork.entity.WorkReport;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkReportMapper implements RowMapper<WorkReport> {
    @Override
    public WorkReport mapRow(ResultSet resultSet, int i) throws SQLException {
        return WorkReport.builder()
                .workReportId(resultSet.getInt("work_report_id"))
                .workReportReason(resultSet.getString("work_report_reason"))
                .workReportDescription(resultSet.getString("work_report_description"))
                .workReportStatus(resultSet.getString("work_report_status"))
                .build();
    }
}
