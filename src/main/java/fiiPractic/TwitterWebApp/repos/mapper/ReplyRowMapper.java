package fiiPractic.TwitterWebApp.repos.mapper;

import fiiPractic.TwitterWebApp.model.entity.PostEntity;
import fiiPractic.TwitterWebApp.model.entity.ReplyEntity;
import fiiPractic.TwitterWebApp.model.entity.UserEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReplyRowMapper implements RowMapper<ReplyEntity> {
    @Override
    public ReplyEntity mapRow(final ResultSet rs, final int rowNum) throws SQLException {

        //serEntity user = userRepository.getUserById(rs.getInt("USER_ID"));
        return new ReplyEntity(
                rs.getInt("ID"),
                new UserEntity(
                        rs.getInt("USER_ID"),
                        rs.getString("USER_NAME"),
                        rs.getString("FIRST_NAME"),
                        rs.getString("LAST_NAME"),
                        rs.getString("EMAIL"),
                        rs.getString("PASSWORD")),
                rs.getString("MESSAGE"),
                rs.getTimestamp("TIME"));

    }
}