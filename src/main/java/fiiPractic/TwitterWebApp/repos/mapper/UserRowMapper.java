package fiiPractic.TwitterWebApp.repos.mapper;


import fiiPractic.TwitterWebApp.model.entity.UserEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<UserEntity> {

    @Override
    public UserEntity mapRow(final ResultSet rs, final int rowNum) throws SQLException {

        return UserEntity.builder()
                .id(rs.getInt("ID"))
                .userName(rs.getString("USER_NAME"))
                .firstName(rs.getString("FIRST_NAME"))
                .lastName(rs.getString("LAST_NAME"))
                .email(rs.getString("EMAIL"))
                .password(rs.getString("PASSWORD"))
                .build();
    }
}
