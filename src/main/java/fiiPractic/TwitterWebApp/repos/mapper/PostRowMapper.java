package fiiPractic.TwitterWebApp.repos.mapper;

import fiiPractic.TwitterWebApp.model.entity.PostEntity;
import fiiPractic.TwitterWebApp.model.entity.UserEntity;
import fiiPractic.TwitterWebApp.repos.Dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PostRowMapper implements RowMapper<PostEntity> {
    @Autowired
    private UserDAO userRepository ;
    @Override
    public PostEntity mapRow(final ResultSet rs, final int rowNum) throws SQLException {

        //serEntity user = userRepository.getUserById(rs.getInt("USER_ID"));
        return new PostEntity(
                rs.getInt(1),
                new UserEntity(
                        rs.getInt(5),
                        rs.getString("USER_NAME"),
                        rs.getString("FIRST_NAME"),
                        rs.getString("LAST_NAME"),
                        rs.getString("EMAIL"),
                        rs.getString("PASSWORD")),
                rs.getString("MESSAGE"),
                rs.getTimestamp("TIME"));
    }

}
