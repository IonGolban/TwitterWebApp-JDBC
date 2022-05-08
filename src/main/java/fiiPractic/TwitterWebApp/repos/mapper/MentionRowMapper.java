package fiiPractic.TwitterWebApp.repos.mapper;

import fiiPractic.TwitterWebApp.model.entity.MentionEntity;
import fiiPractic.TwitterWebApp.model.entity.PostEntity;
import fiiPractic.TwitterWebApp.model.entity.UserEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MentionRowMapper implements RowMapper<MentionEntity> {
    @Override
    public MentionEntity mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        return MentionEntity.builder()
                .id(rs.getInt("ID"))
                .userEntity(new UserEntity(
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7)))
                .postEntity(new PostEntity(
                        rs.getInt("POST_ID"),
                        new UserEntity(
                                rs.getInt(11),
                                rs.getString(12),
                                rs.getString(13),
                                rs.getString(14),
                                rs.getString(15),
                                rs.getString(16)),
                        rs.getString("MESSAGE"),
                        rs.getTimestamp("TIME")))
                .build();
    }
}
