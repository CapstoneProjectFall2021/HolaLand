package mapper;

import com.hola.holalandwork.entity.WorkComment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkCommentMapper implements RowMapper<WorkComment> {
    @Override
    public WorkComment mapRow(ResultSet resultSet, int i) throws SQLException {
        return WorkComment.builder()
                .workCommentId(resultSet.getInt("work_comment_id"))
                .workRequestRecruitmentId(resultSet.getInt("work_request_recruitment_id"))
                .accountId(resultSet.getInt("account_id"))
                .workCommentContent(resultSet.getString("work_comment_content"))
                .workCommentDeleted(resultSet.getBoolean("work_comment_deleted"))
                .build();
    }
}
